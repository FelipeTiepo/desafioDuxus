package br.com.duxusdesafio.dto;


import br.com.duxusdesafio.models.Integrante;
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

public class IntegranteDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, message = "A franquia deve ter no mínimo 3 caracteres")
    private String franquia;

    @NotBlank
    @Size(min = 3, message = "A franquia deve ter no mínimo 3 caracteres")
    private String nome;

    @NotBlank
    @Size(min = 3, message = "A franquia deve ter no mínimo 3 caracteres")
    private String funcao;

    private Time time;


    public IntegranteDTO(Integrante entity) {
        id = entity.getId();
        franquia = entity.getFranquia();
        nome = entity.getNome();
        funcao = entity.getFuncao();

//        for(Time time : entity.getTimes()){
//            times.add(time);
//        }
    }

}
