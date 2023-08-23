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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/twitter")
public class Controller {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TwiterRepository twiterRepository;


    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUserName(usuario.getUserName());
        if (usuarioOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO: UserName já esta em uso");
        }

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
    @PostMapping("/seguir/{seguidorId}/{seguidoId}")
    public ResponseEntity<String> seguirUsuario(@PathVariable Long seguidorId, @PathVariable Long seguidoId) {
        Optional<Usuario> seguidorOptional = usuarioRepository.findById(seguidorId);
        Optional<Usuario> seguidoOptional = usuarioRepository.findById(seguidoId);

        if (seguidorOptional.isPresent() && seguidoOptional.isPresent()) {
            Usuario seguidor = seguidorOptional.get();
            Usuario seguido = seguidoOptional.get();

            seguidor.getSeguidos().add(seguido);
            usuarioRepository.save(seguidor);

            return ResponseEntity.ok("Usuário seguido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/feed/{usuarioId}")
    public ResponseEntity<List<Twitter>> getFeedForUsuario(@PathVariable Long usuarioId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<Twitter> feed = usuario.getSeguidos().stream()
                    .flatMap(u -> u.getTwitters().stream())
                    .sorted(Comparator.comparing(Twitter::getData).reversed())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(feed);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}




