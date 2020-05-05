package pe.afmancilla.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.afmancilla.webflux.handler.TemperaturaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(TemperaturaHandler handler) {

        return route(GET("/api/metricas/horas/{fecha}"), handler::obtenerListadoMetricasxHora)
                .andRoute(GET("/api/metricas/dias/{fecha}"), handler::obtenerMetricaXDia)
                .andRoute(POST("/api/metricas"), handler::registrarMetrica);
    }
}
