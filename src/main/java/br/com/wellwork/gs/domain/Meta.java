package br.com.wellwork.gs.domain;

import jakarta.persistence.*;
import lombok.*;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
