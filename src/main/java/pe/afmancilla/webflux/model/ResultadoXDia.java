package pe.afmancilla.webflux.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "min",
        "max",
        "average"
})
public class ResultadoXDia implements Serializable {


    private static final long serialVersionUID = 1041450715535489197L;
    @JsonProperty("date")
    public String date;
    @JsonProperty("min")
    public Integer min;
    @JsonProperty("max")
    public Integer max;
    @JsonProperty("average")
    public Double average;


    public ResultadoXDia() {
        super();
    }


    public ResultadoXDia(String date, Integer min, Integer max, Double average) {
        super();
        this.date = date;
        this.min = min;
        this.max = max;
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoXDia that = (ResultadoXDia) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(min, that.min) &&
                Objects.equals(max, that.max) &&
                Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, min, max, average);
    }

    /**
     * GET AND SET
     **/

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
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
