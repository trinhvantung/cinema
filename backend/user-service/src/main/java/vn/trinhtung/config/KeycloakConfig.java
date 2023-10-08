package vn.trinhtung.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeycloakConfig {
    private final KeycloakPropertiesConfig properties;

    @Bean
    public Keycloak keycloak() {
        System.out.println(properties.toString());
        return KeycloakBuilder.builder()
                .grantType(OAuth2Constants.PASSWORD)
                .serverUrl(properties.getServerUrl())
                .realm(properties.getRealm())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .build();
    }
}
