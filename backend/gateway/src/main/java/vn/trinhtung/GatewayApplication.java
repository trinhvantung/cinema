package vn.trinhtung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public WebFilter corsFilter() {

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            List<String> allowedOrigins = Arrays.asList("http://localhost:5173", "http://localhost:5174",
                    "http://192.168.1.104:5173", "http://192.168.1.104:5174",
                    "https://soa-ptit-cinema-trinhvantung.loca.lt");

            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                String origin = request.getHeaders().getOrigin();

                if (!allowedOrigins.contains(origin)) {
                    return Mono.empty();
                }

                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();

                headers.setAccessControlAllowOrigin(origin);
                headers.setAccessControlAllowCredentials(true);
                headers.setAccessControlAllowHeaders(Arrays.asList("Access-Control-Allow-Origin",
                        "Content-Type", "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Methods", "Authorization",
                        "Access-Control-Max-Age", "Access-Control-Request-Headers",
                        "Access-Control-Request-Method"));
                headers.setAccessControlExposeHeaders(Arrays.asList("Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials", "Qr-Expire"));
                headers.setAccessControlMaxAge(3600L);
                headers.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.POST,
                        HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS));

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(ctx);
        };
    }

}
