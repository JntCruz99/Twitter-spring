package com.jntcruz.twitter.controller;

import com.jntcruz.twitter.entity.Twitter;
import com.jntcruz.twitter.entity.Usuario;
import com.jntcruz.twitter.repository.TwiterRepository;
import com.jntcruz.twitter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/twitter")
public class Controller {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TwiterRepository twiterRepository;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PostMapping("/{iduser}")
    public ResponseEntity<?> criarTwitter(@PathVariable Long iduser, @RequestBody Twitter twitter){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(iduser);
        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            twitter.setUsuario(usuario);
            twitter.setData(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(twiterRepository.save(twitter));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id de usuario não existe");
        }
    }

    @GetMapping
    public ResponseEntity<?> exibirTwitter(){
        return ResponseEntity.status(HttpStatus.OK).body(twiterRepository.findAll());
    }

    @GetMapping("/perfil/{idUser}")
    public ResponseEntity<?> exibirPerfil(@PathVariable Long idUser){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUser);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(idUser));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");
        }
    }


}
