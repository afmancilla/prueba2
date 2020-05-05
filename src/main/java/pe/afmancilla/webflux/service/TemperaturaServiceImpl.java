package pe.afmancilla.webflux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.afmancilla.webflux.model.Metrica;
import pe.afmancilla.webflux.model.ResultadoXDia;
import pe.afmancilla.webflux.model.ResultadoXHora;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TemperaturaServiceImpl implements TemperaturaService {

    private static final Logger log = LoggerFactory.getLogger(TemperaturaServiceImpl.class);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static List<Metrica> metricas = new ArrayList<>();

    @Override
    public Flux<ResultadoXHora> obtenerListadoMetricasxHora(LocalDate fecha) {


        List<ResultadoXHora> horas = new ArrayList<>();

        List<Metrica> tHoras = metricas.stream()
                .filter(metrica -> metrica.getFechaHora().toLocalDate().isEqual(fecha))
                .collect(Collectors.toList());

        Map<Integer, List<Metrica>> mapHoras = tHoras.stream().collect(Collectors.groupingBy(e -> e.getFechaHora().getHour()));
        mapHoras.forEach((k, v) -> {

            ResultadoXHora rHora = new ResultadoXHora();


            String output = String.format("[%02d:00 - %02d:00>", k, ++k);
            log.info(output);
            rHora.setTime(output);

            List<Integer> temperaturas = v.stream()
                    .map(Metrica::getTemperatura)
                    .collect(Collectors.toList());

            Optional<Integer> min = temperaturas.stream()
                    .min((i, j) -> i.compareTo(j));

            Optional<Integer> max = temperaturas.stream()
                    .max((i, j) -> i.compareTo(j));


            Double average = v.stream()
                    .mapToLong(Metrica::getTemperatura)
                    .average()
                    .orElse(Double.NaN);

            rHora.setMin(min.get());
            rHora.setMax(max.get());
            rHora.setAverage(average);

            horas.add(rHora);
        });


        return Flux.fromIterable(horas);
    }

    @Override
    public Mono<ResultadoXDia> obtenerMetricaXDia(LocalDate fecha) {

        List<Integer> temperaturas = metricas.stream()
                .filter(metrica -> metrica.getFechaHora().toLocalDate().isEqual(fecha))
                .map(Metrica::getTemperatura)
                .collect(Collectors.toList());

        Optional<Integer> min = temperaturas.stream()
                .min((i, j) -> i.compareTo(j));

        Optional<Integer> max = temperaturas.stream()
                .max((i, j) -> i.compareTo(j));


        Double average = metricas.stream()
                .filter(metrica -> metrica.getFechaHora().toLocalDate().isEqual(fecha))
                .mapToLong(Metrica::getTemperatura)
                .average()
                .orElse(Double.NaN);


        ResultadoXDia result = new ResultadoXDia(fecha.format(formatter), min.get(), max.get(), average);


        return Mono.just(result);
    }


    @Override
    public Mono<Void> registrarMetrica(Metrica metrica) {
        metricas.add(metrica);
        return Mono.empty();
    }


}
