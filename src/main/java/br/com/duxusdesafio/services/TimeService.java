package br.com.duxusdesafio.services;

import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Transactional(readOnly = true)
    public List<TimeDTO> findAll() {
        List<Time> list = timeRepository.findAll();
        return list.stream().map(TimeDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TimeDTO insert(TimeDTO dto) {
        Time entity = new Time();
        //copyDtoToEntity(dto, entity);
        entity = timeRepository.save(entity);
        return new TimeDTO(entity);
    }

    @Transactional(readOnly = true)
    public TimeDTO findById(Long id) {

        Time time = timeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso inválido - " + id)
        );
        return new TimeDTO(time);
    }

    @Transactional
    public TimeDTO update(Long id, TimeDTO dto) {

        try {
            Time entity = timeRepository.getReferenceById(id);
            //copyDtoToEntity(dto, entity);
            entity = timeRepository.save(entity);
            return new TimeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {

        if (!timeRepository.existsById(id)) {
            throw new IllegalArgumentException("Recurso inválido - id: " + id);
        }
        try {
            timeRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }

    //private void copyDtoToEntity(TimeDTO dto, Time entity) {entity.setNome(dto.getNome());}


}
