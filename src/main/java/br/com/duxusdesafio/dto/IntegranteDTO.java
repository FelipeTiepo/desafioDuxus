package br.com.duxusdesafio.dto;


import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.models.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


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

    private List<ComposicaoTime> composicaoTimes = new ArrayList<>();

    public IntegranteDTO(Integrante entity) {
        id = entity.getId();
        franquia = entity.getFranquia();
        nome = entity.getNome();
        funcao = entity.getFuncao();

        for(ComposicaoTime composicaoTime : entity.getComposicaoTime()){
            composicaoTimes.add(composicaoTime);
        }
    }

    public IntegranteDTO(){

    }

    public IntegranteDTO(Long id, String franquia, String nome, String funcao, Time time) {
        this.id = id;
        this.franquia = franquia;
        this.nome = nome;
        this.funcao = funcao;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFranquia() {
        return franquia;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
