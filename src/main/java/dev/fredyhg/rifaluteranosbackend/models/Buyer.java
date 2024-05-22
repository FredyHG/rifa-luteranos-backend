package dev.fredyhg.rifaluteranosbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Table(name = "tb_buyer")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

}
