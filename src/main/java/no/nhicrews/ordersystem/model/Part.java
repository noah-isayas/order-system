package no.nhicrews.ordersystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Partnumber cant be blank")
    @Size(max = 50, message = "Part number cannot be longer than 50 characters")
    private String partNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subassembly_id")
    private Subassembly subassembly;
}
