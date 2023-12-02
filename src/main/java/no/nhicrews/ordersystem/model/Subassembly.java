package no.nhicrews.ordersystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Subassembly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please fill out a name")
    @Size(max = 50, message = "Name cant be longer than 50 characters")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @OneToMany(mappedBy = "subassembly", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Part> parts;
}
