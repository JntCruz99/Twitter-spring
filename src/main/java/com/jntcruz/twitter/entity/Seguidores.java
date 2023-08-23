package com.jntcruz.twitter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_seguidores")
@Setter
@Getter
public class Seguidores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "seguidores")
    private List<Usuario> usuarios;

}
