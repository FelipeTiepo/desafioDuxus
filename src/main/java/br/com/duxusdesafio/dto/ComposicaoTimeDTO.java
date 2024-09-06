package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.models.ComposicaoTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ComposicaoTimeDTO {

    private Long id;

    private Long time;

    private Long integrante;

    public ComposicaoTimeDTO(ComposicaoTime entity) {
        id = entity.getId();
        time = entity.getTime().getId();
        integrante = entity.getIntegrante().getId();
    }

}