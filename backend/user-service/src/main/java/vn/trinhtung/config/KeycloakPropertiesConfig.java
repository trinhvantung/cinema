package vn.trinhtung.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(value = "keycloak")
public class KeycloakPropertiesConfig {
    private String clientId;
    private String clientSecret;
    private String serverUrl;
    private String realm;
    private String username;
    private String password;
}
