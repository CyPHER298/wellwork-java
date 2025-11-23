package br.com.wellwork.gs.getaways.repository;

import br.com.wellwork.gs.domain.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaRepository extends JpaRepository<Gestor, Integer> {
}
