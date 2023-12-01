package no.nhicrews.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partNumber;

    @ManyToOne
    @JoinColumn(name = "subassembly_id")
    private Subassembly subassembly;
}
