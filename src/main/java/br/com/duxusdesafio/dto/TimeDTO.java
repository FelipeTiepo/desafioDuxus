package br.com.duxusdesafio.dto;


import br.com.duxusdesafio.models.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TimeDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private LocalDate data;

    public TimeDTO(Time entity) {
        id = entity.getId();
        data = entity.getData();
    }

}
