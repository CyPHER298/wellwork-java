package br.com.wellwork.gs.getaways.repository;

import br.com.wellwork.gs.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
}
