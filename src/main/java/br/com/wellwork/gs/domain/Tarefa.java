package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumStatusTarefa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;
    @Enumerated(EnumType.STRING)
    private EnumStatusTarefa status;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Usuario> usuarios;
}
