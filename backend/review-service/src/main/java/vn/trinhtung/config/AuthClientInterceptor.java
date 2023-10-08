package vn.trinhtung.config;

import io.grpc.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.client.security.CallCredentialsHelper;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

@RequiredArgsConstructor
@GrpcGlobalClientInterceptor
public class AuthClientInterceptor implements ClientInterceptor {


    private final OAuth2AuthorizedClientManager authorizedClientManager;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions, Channel channel) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("service");
        String clientRegistrationId = clientRegistration.getRegistrationId();
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(clientRegistrationId)
                .principal("principal").build();
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        callOptions = callOptions.withCallCredentials(CallCredentialsHelper.bearerAuth(accessToken.getTokenValue()));

        return channel.newCall(methodDescriptor, callOptions);
    }
}
