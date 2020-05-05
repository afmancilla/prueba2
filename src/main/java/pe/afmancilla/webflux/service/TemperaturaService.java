package pe.afmancilla.webflux.service;

import pe.afmancilla.webflux.model.Metrica;
import pe.afmancilla.webflux.model.ResultadoXDia;
import pe.afmancilla.webflux.model.ResultadoXHora;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TemperaturaService {

    Flux<ResultadoXHora> obtenerListadoMetricasxHora(LocalDate fecha);


    Mono<ResultadoXDia> obtenerMetricaXDia(LocalDate fecha);


    Mono<Void> registrarMetrica(Metrica metrica);

}
