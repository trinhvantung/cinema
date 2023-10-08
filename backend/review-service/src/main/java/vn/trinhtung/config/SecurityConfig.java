package vn.trinhtung.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests().anyRequest().permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }

    JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new AuthorityConverter());

        return jwtAuthenticationConverter;
    }


    public class AuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(final Jwt jwt) {
            Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
            if (Objects.isNull(realmAccess)) {
                return new ArrayList<>();
            }

            List<String> roleStrings = (List<String>) realmAccess.get("roles");
            if (Objects.isNull(roleStrings)) {
                return new ArrayList<>();
            }
            List<SimpleGrantedAuthority> authorities = roleStrings.stream()
                    .map(s -> new SimpleGrantedAuthority(s))
                    .collect(Collectors.toCollection(ArrayList::new));


            String scopeString = jwt.getClaimAsString("scope");
            if(Objects.nonNull(scopeString) && !scopeString.isBlank()) {
                List<String> scopes = Arrays.asList(scopeString.split(" "));
                if (Objects.nonNull(scopes)) {
                    scopes.forEach(
                            scope -> authorities.add(new SimpleGrantedAuthority("SCOPE_" + scope)));
                }
            }

            return new ArrayList<>(authorities);
        }
    }

}
