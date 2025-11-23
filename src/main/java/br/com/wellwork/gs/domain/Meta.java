package br.com.wellwork.gs.domain;

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
@Table(name = "meta")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
