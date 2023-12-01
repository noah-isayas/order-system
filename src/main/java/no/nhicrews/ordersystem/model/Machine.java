package no.nhicrews.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(mappedBy = "machine")
    private List<Subassembly> subassemblies;
}
