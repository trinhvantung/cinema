package vn.trinhtung.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import vn.trinhtung.security.AppUser;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;

@EnableAuthorizationServer
@RequiredArgsConstructor
@Configuration
@SuppressWarnings("deprecation")
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder).allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client").secret(passwordEncoder.encode("12345"))
                .scopes("client").authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(100000).refreshTokenValiditySeconds(10000).and()

                .withClient("server").secret(passwordEncoder.encode("12345")).scopes("server")
                .authorizedGrantTypes("client_credentials").accessTokenValiditySeconds(100000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();

        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(accessTokenConverter());

        enhancerChain.setTokenEnhancers(tokenEnhancers);



        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService).accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain).tokenStore(new JwtTokenStore(accessTokenConverter()));
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    @Bean
    KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("trinhvantung", "123456".toCharArray());
    }

    @Bean
    TokenEnhancer tokenEnhancer() {
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            Map<String, Object> map = new HashMap<>();
            try {
                AppUser user = (AppUser) oAuth2Authentication.getPrincipal();
                map.put("id", user.getUser().getId());
                map.put("full_name", user.getUser().getFullName());
                map.put("roles", user.getAuthorities().stream().map(GrantedAuthority::toString).collect(Collectors.toList()));
            } catch (Exception e) {
            }
            map.put("iss", "http://localhost:8080/api/auth-service/issuer");
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
            return oAuth2AccessToken;
        };
    }

    @Bean
    JWKSet jwkSet(KeyPair keyPair) {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .keyUse(KeyUse.SIGNATURE).algorithm(JWSAlgorithm.RS256)
                .keyID(UUID.randomUUID().toString());

        return new JWKSet(builder.build());
    }
}