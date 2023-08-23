package com.jntcruz.twitter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Setter
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "seguidores_id")
    private Seguidores seguidores;

    @OneToMany(mappedBy = "usuario")
    private List<Twitter> twitters;


}
