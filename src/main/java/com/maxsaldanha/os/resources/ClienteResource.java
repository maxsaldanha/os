package com.maxsaldanha.os.resources;

import com.maxsaldanha.os.domain.Cliente;
import com.maxsaldanha.os.dtos.ClienteDTO;
import com.maxsaldanha.os.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/*A classe Resource, ou recursos, é responsável pelo CRUD */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    /*Busca de técnico por id */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        ClienteDTO objDTO = new ClienteDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }
    /*CRUD */

    /*Realiza a busca da lista de técnicos */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> listDTO = service.findAll()
                .stream().map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    /*Realizar a criação de um técnio */
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id").buildAndExpand(newObj
                        .getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    /*Realizar a alteração de um técnio */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update (@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
        ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
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
