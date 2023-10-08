package vn.trinhtung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class Oauth2ClientConfig {

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder
                .builder().clientCredentials().build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, oAuth2AuthorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // cấu hình thông tin client registration
        return new InMemoryClientRegistrationRepository(clientRegistration());
    }

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("service").clientId("cinema-be")
                .clientSecret("6wyk3V6vxiOWsULP9obKKRAN0OYjJqQ8").clientName("server")
                .tokenUri("http://localhost:8080/realms/master/protocol/openid-connect/token")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS).scope("server")
                .build();
    }
}
