package vn.trinhtung.dto;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "oauth2.issuer.metadata")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MetadataResponse {
	private String issuer;
	private String tokenEndpoint;
	private String tokenKeyEndpoint;
	private String authorizationEndpoint;
	private String checkTokenEndpoint;
	private String userinfoEndpoint;
	private String introspectionEndpoint;
	private String jwksUri;
	private String revocationEndpoint;
	private Set<String> userinfoSigningAlgSupported;
	private Set<String> idTokenSigningAlgValuesSupported;
	private Set<String> tokenEndpointAuthSigningAlgValuesSupported;
	private Set<String> scopesSupported;
	private Set<String> subjectTypesSupported;
	private Set<String> responseTypesSupported;
	private Set<String> claimsSupported;
	private Set<String> grantTypesSupported;
	private Set<String> tokenEndpointAuthMethodsSupported;
}
