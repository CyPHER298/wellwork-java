package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumStatusTarefa;
import br.com.wellwork.gs.domain.enums.EnumStatusTimer;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Timer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo;
    private Duration duracao;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private EnumStatusTimer status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;
}
