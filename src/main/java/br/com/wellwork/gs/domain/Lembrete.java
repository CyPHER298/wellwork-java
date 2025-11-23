package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumAtivoLembrete;
import jakarta.persistence.*;
import lombok.*;

@With
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "lembrete")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String sequencia;

    @Enumerated(EnumType.STRING)
    private EnumAtivoLembrete ativo;
}
