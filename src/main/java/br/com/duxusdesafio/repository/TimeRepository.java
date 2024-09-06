package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.models.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
