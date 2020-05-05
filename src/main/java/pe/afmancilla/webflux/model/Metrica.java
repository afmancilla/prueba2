package pe.afmancilla.webflux.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fecha-hora",
        "temperatura"
})
public class Metrica implements Serializable {


    private static final long serialVersionUID = 1735099454751334996L;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("temperatura")
    private int temperatura;

    public Metrica(String fecha, int temperatura) {
        super();
        this.fecha = fecha;
        this.temperatura = temperatura;
    }

    public LocalDateTime getFechaHora() {

        LocalDateTime dateTime = LocalDateTime.parse(getFecha(), formatter);

        return dateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrica metrica = (Metrica) o;
        return temperatura == metrica.temperatura &&
                Objects.equals(fecha, metrica.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, temperatura);
    }

    /**
     * GET AND SET
     **/

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }


}
