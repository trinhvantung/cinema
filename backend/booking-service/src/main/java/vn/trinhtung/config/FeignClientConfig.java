package vn.trinhtung.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@RequiredArgsConstructor
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(
            OAuth2AuthorizedClientManager authorizedClientManager,
            ClientRegistrationRepository clientRegistrationRepository) {

        return template -> {
            // Lấy tên client từ annotation @FeignClient
            String clientName = template.feignTarget().name();
            ClientRegistration clientRegistration =
                    clientRegistrationRepository.findByRegistrationId(clientName);
            String clientRegistrationId = clientRegistration.getRegistrationId();

            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId)
                    .principal("principal").build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);

            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.getTokenValue());
        };
    }

}

