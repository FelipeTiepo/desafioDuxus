package br.com.duxusdesafio.services;

import br.com.duxusdesafio.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComposicaoTimeService {

    @Autowired
    private ComposicaoTimeRepository composicaoTimeRepository;

    @Transactional(readOnly = true)
    public List<ComposicaoTimeDTO> findAll(){
        List<ComposicaoTime> composicaoTimes = composicaoTimeRepository.findAll();
        return composicaoTimes.stream().map(ComposicaoTimeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ComposicaoTimeDTO findById(Long id){
        ComposicaoTime entity = composicaoTimeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado")
        );
        return new ComposicaoTimeDTO(entity);
    }

    @Transactional
    public ComposicaoTimeDTO insert(ComposicaoTimeDTO dto){
        ComposicaoTime entity = new ComposicaoTime();
        copyDtoToEntity(dto, entity);
        entity = composicaoTimeRepository.save(entity);
        return new ComposicaoTimeDTO(entity);
    }

    @Transactional
    public ComposicaoTimeDTO update(Long id, ComposicaoTimeDTO dto){
        try{
            ComposicaoTime composicaoTime = composicaoTimeRepository.getById(id);
            copyDtoToEntity(dto, composicaoTime);
            composicaoTime = composicaoTimeRepository.save(composicaoTime);
            return new ComposicaoTimeDTO(composicaoTime);
        } catch (EntityNotFoundException e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){

        if (!composicaoTimeRepository.existsById(id)) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
        try{
            composicaoTimeRepository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(ComposicaoTimeDTO dto, ComposicaoTime entity) {

        entity.setTime(dto.getTime());
        entity.setIntegrante(dto.getIntegrante());
    }


}
