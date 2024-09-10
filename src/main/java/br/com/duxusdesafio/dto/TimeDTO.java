package br.com.duxusdesafio.dto;


import br.com.duxusdesafio.models.Time;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class TimeDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private LocalDate data;

    public TimeDTO(Time entity) {
        id = entity.getId();
        data = entity.getData();
    }

    public TimeDTO() {

    }

    public TimeDTO(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
