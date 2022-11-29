package hungryfood.eats.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Grupo {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "tb_gps_permissoes",
            joinColumns = @JoinColumn (name = "gp_id"),
            inverseJoinColumns = @JoinColumn(name = "permissoes_id"))
    private List<Permissao> permissoes = new ArrayList<>();


    @JsonIgnore
    @ManyToMany(mappedBy = "gps")
    private List<Usuario> usuarios = new ArrayList<>();
}
