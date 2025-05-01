package org.jong.webflux.configuration;

import org.jong.webflux.filter.JongHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class RouteConfiguration {

    private final JongHandlerFilter jongHandlerFilter;

    @Bean
    public RouterFunction<ServerResponse> jongRouteLocator() {
        return RouterFunctions.route()
                              .GET("/api/router/{id}", this::handler)
                              .filter(jongHandlerFilter)
                              .GET("/api/router-sub/{id}", this::handler)
                              .build();
    }

    @Bean
    public RouterFunction<ServerResponse> jongRouteLocator2() {
        return RouterFunctions.route()
                              .GET("/api/router2/{id}", this::handler)
                              .build();
    }


    private Mono<ServerResponse> handler(ServerRequest serverRequest){
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue(id.toString());
    }

}
