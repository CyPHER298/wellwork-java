package br.com.wellwork.gs.getaways.repository;

import br.com.wellwork.gs.domain.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<Timer,Integer> {
}
