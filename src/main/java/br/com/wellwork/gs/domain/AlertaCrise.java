package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumStatusAlerta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlertaCrise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Gestor gestor;
    private LocalDateTime dataHora;
    @Enumerated(EnumType.STRING)
    private EnumStatusAlerta status;
}
