package vn.trinhtung.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "vnp")
@ConfigurationPropertiesScan
public class VnPayConfig {
    private String tmnCode;
    private String hashSecret;
    private String url;
    private String version;
    private String returnUrl;
    private String locale;
    private String refundUrl;
}
