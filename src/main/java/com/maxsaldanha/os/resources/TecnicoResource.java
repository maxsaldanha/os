package com.maxsaldanha.os.resources;

import com.maxsaldanha.os.domain.Tecnico;
import com.maxsaldanha.os.dtos.TecnicoDTO;
import com.maxsaldanha.os.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/*A classe Resource, ou recursos, é responsável pelo CRUD */
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    /*Busca de técnico por id */
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }
    /*CRUD */

    /*Realiza a busca da lista de técnicos */
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<TecnicoDTO> listDTO = service.findAll()
                .stream().map(obj -> new TecnicoDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    /*Realizar a criação de um técnio */
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
        Tecnico newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id").buildAndExpand(newObj
                        .getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    /*Realizar a alteração de um técnio */
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update (@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
        TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }
    /*Realizar a exclusão de um técnio */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        findById(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
