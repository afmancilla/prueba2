package pe.afmancilla.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.afmancilla.webflux.model.Metrica;
import pe.afmancilla.webflux.model.ResultadoXDia;
import pe.afmancilla.webflux.model.ResultadoXHora;
import pe.afmancilla.webflux.service.TemperaturaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/metricas")
public class TemperaturaController {

    private static final Logger log = LoggerFactory.getLogger(TemperaturaController.class);
    @Autowired
    private TemperaturaService service;

    @PostMapping
    public Mono<ResponseEntity<Void>> registrarMetrica(@Valid @RequestBody Mono<Metrica> metrica) {


        return metrica.flatMap(p -> {
            return service.registrarMetrica(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }


    @GetMapping("/horas/{fecha}")
    public Mono<ResponseEntity<Flux<ResultadoXHora>>> obtenerListadoMetricasxHora(
            @PathVariable("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {


        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.obtenerListadoMetricasxHora(fecha))
        );

    }


    @GetMapping("/dias/{fecha}")
    public Mono<ResponseEntity<ResultadoXDia>> obtenerMetricaXDia(
            @PathVariable("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {


        return service.obtenerMetricaXDia(fecha).map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
