package com.jntcruz.twitter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Getter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String userName;

    @ManyToMany
    @JoinTable(name = "seguidores_seguidos",
            joinColumns = @JoinColumn(name = "seguidor_id"),
            inverseJoinColumns = @JoinColumn(name = "seguido_id"))
    private List<Usuario> seguidos;

    @ManyToMany(mappedBy = "seguidos")
    private List<Usuario> seguidores;

    @OneToMany(mappedBy = "usuario")
    private List<Twitter> twitters;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getSeguindo() {
        return seguidos.stream()
                .map(Usuario::getUserName)
                .toList();
    }

    public List<String> getSeguidor() {
        return seguidores.stream()
                .map(Usuario::getUserName)
                .toList();
    }

    @JsonIgnore
    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    @JsonIgnore
    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public List<Twitter> getTwitters() {
        return twitters;
    }
}
