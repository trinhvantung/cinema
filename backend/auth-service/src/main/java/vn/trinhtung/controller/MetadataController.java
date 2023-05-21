package vn.trinhtung.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.jwk.JWKSet;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.MetadataResponse;

@RequiredArgsConstructor
@RequestMapping("/issuer")
@RestController
public class MetadataController {
	private final JWKSet jwkSet;
	private final MetadataResponse metadata;

	@Value("${oauth2.issuer.metadata.tokenEndpoint}")
	private String message;

	@GetMapping("/.well-known/jwks.json")
	public Map<String, Object> jwkKeys() {
		return jwkSet.toJSONObject();
	}

	@GetMapping("/.well-known/openid-configuration")
	public MetadataResponse openIdDiscovery() {
		return metadata;
	}
}
