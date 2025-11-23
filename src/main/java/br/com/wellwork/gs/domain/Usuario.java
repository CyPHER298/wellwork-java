package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@With
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private EnumCargoUsuario cargo;
    private String acessibilidade;

}
