package no.nhicrews.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@Entity
@Data
@NoArgsConstructor
public class Subassembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @OneToMany(mappedBy = "subassembly")
    private List<Part> parts;
}
