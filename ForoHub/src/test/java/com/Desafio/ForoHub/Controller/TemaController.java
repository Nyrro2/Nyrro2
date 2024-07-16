package com.Desafio.ForoHub.Controller;


import com.Desafio.ForoHub.Tema.DatosReplyTema;
import com.Desafio.ForoHub.Tema.Tema;
import com.Desafio.ForoHub.Tema.TemaRepository;
import com.Desafio.ForoHub.Tema.UpdateTemaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tema")
public class TemaController {

    @GetMapping
    @Transactional
    @Operation(
            summary = "Consultar todos los registros de topico",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<Page<TopicoDTO>> listadoTopico(@PageableDefault(size = 10) Pageable paginacion){

        Page<Topico> topicos = repository.findByStatusTrue(paginacion);
        if(tema.isEmpty()){
            return ResponseEntity.ok(Page.empty(paginacion));
        }else {
            Page<TopicoDTO> topicoDTOS = topicos.map(TopicoDTO::new);
            return ResponseEntity.ok(topicoDTOS);
        }

    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Consultar registro por identificador unico ID",
            tags = {"Topico Controller", "GET"}
    )
    public ResponseEntity<DatosReplyTema> retornarRegistroUnico(@PathVariable Long id){
        JpaRepository<T, Long> repository;
        Tema tema = repository.getReferenceById(id);
        var datosTema = new DatosReplyTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getPublicacion(),
                tema.getFechaDeCreacion(),
                tema.getActivo(),
                tema.getUsername(),
                tema.getCurso());
        return ResponseEntity.ok(datosTema);
    }

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registrar un topico en base de datos",
            tags = {"Topico Controller", "POST"}
    )
    public ResponseEntity<DatosReplyTema> registroTema(@RequestBody DatosReplyTema datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        Tema tema = repository.save(new Tema(datosRegistroTopico));
        DatosReplyTema datos = new DatosReplyTema(
                tema.getId(),
                tema.getTitulo(),
                tema.getPublicacion(),
                tema.getFechaDeCreacion(),
                tema.getActivo(),
                tema.getUsername(),
                tema.getCurso());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(url).body(datos);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualizar un topico",
            tags = {"Topico Controller", "PUT"}
    )
    public ResponseEntity<DatosReplyTema> actualizarTopico(
            @RequestBody UpdateTemaDTO actualizarTopicoDTO){

        Tema tema = TemaRepository.findById(UpdateTemaDTO.id())
                .orElseThrow(() -> new EntityNotFoundException("No se puede encontrar com.forohub.api.modelo.topico.Topico con id: -> " + actualizarTopicoDTO.id()));

        if(actualizarTopicoDTO.id() == null) {
            throw new IllegalArgumentException("ID topico es requerido");
        }

        Tema.actualizarDatos(actualizarTopicoDTO);
        TemaRepository.save(tema);

        return ResponseEntity.ok()
                .body(new DatosReplyTema(
                        tema.getId(),
                        tema.getTitulo(),
                        tema.getPublicacion(),
                        tema.getFechaDeCreacion(),
                        tema.getActivo(),
                        tema.getUsername(),
                        tema.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Eliminar de forma logica un registro topico",
            tags = {"Topico Controller", "DELETE"}
    )
    public ResponseEntity<Tema> eliminarTopico(@PathVariable Long id){
        Tema tema = TemaRepository.getReferenceById(id);
        tema.desactivarTopico();
        return ResponseEntity.noContent().build();
    }




}
