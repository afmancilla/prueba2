package pe.afmancilla.webflux.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.afmancilla.webflux.model.Metrica;
import pe.afmancilla.webflux.service.TemperaturaService;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class TemperaturaHandler {

	private static final Logger log = LoggerFactory.getLogger(TemperaturaHandler.class);

    @Autowired
    private TemperaturaService service;


    public Mono<ServerResponse> obtenerListadoMetricasxHora(ServerRequest request) {

        String fecha = request.pathVariable("fecha");

        LocalDate localDate = LocalDate.parse(fecha);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.obtenerListadoMetricasxHora(localDate), Metrica.class);

    }


    public Mono<ServerResponse> obtenerMetricaXDia(ServerRequest request) {

        String fecha = request.pathVariable("fecha");

        LocalDate localDate = LocalDate.parse(fecha);

        return service.obtenerMetricaXDia(localDate).flatMap(p -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromObject(p)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> registrarMetrica(ServerRequest request) {

        Mono<Metrica> metrica = request.bodyToMono(Metrica.class);

        return metrica.flatMap(p -> service.registrarMetrica(p).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());

    }


}
