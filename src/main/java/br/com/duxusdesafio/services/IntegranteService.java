package br.com.duxusdesafio.services;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IntegranteService {

    @Autowired
    private IntegranteRepository repository;

    @Autowired
    private TimeRepository timeRepository;

    @Transactional(readOnly = true)
    public List<IntegranteDTO> findAll() {
        List<Integrante> list = repository.findAll();
        return list.stream().map(IntegranteDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public IntegranteDTO insert(IntegranteDTO dto) {
        Integrante entity = new Integrante();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new IntegranteDTO(entity);
    }

    @Transactional(readOnly = true)
    public IntegranteDTO findById(Long id) {

        Integrante integrante = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new IntegranteDTO(integrante);
    }

    @Transactional
    public IntegranteDTO update(Long id, IntegranteDTO dto) {
        try {
            Integrante integrante = repository.getReferenceById(id);
            copyDtoToEntity(dto, integrante);
            integrante = repository.save(integrante);
            return new IntegranteDTO(integrante);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Integrante inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Integrante inválido - id: " + id);
        }
    }

    private void copyDtoToEntity(IntegranteDTO dto, Integrante entity) {
        entity.setFranquia(dto.getFranquia());
        entity.setNome(dto.getNome());
        entity.setFuncao(dto.getFuncao());
        entity.setComposicaoTime(dto.getComposicaoTime());

        entity.getComposicaoTime().clear();
        for(ComposicaoTime item: dto.getComposicaoTime()){
            ComposicaoTime composicaoTime = composicaoTimeRepository.getReferenceById(item.getId());
            entity.getComposicaoTime().add(composicaoTime);
        }
    }

}
