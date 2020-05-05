package pe.afmancilla.webflux.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "time",
        "min",
        "max",
        "average"
})
public class ResultadoXHora implements Serializable {


    private static final long serialVersionUID = 8243006319063868340L;
    @JsonProperty("time")
    public String time;
    @JsonProperty("min")
    public Integer min;
    @JsonProperty("max")
    public Integer max;
    @JsonProperty("average")
    public Double average;


    public ResultadoXHora(String time, Integer min, Integer max, Double average) {
        super();
        this.time = time;
        this.min = min;
        this.max = max;
        this.average = average;
    }


    public ResultadoXHora() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoXHora that = (ResultadoXHora) o;
        return Objects.equals(time, that.time) &&
                Objects.equals(min, that.min) &&
                Objects.equals(max, that.max) &&
                Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, min, max, average);
    }

	/**
	 * GET AND SET
	 **/

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public Integer getMin() {
        return min;
    }


    public void setMin(Integer min) {
        this.min = min;
    }


    public Integer getMax() {
        return max;
    }


    public void setMax(Integer max) {
        this.max = max;
    }


    public Double getAverage() {
        return average;
    }


    public void setAverage(Double average) {
        this.average = average;
    }


}
