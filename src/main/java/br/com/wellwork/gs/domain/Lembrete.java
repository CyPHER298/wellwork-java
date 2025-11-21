package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumAtivoLembrete;
import jakarta.persistence.*;
import lombok.*;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo;
    private String sequencia;
    @Enumerated(EnumType.STRING)
    private EnumAtivoLembrete ativo;
}
