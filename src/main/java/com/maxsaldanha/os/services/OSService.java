package com.maxsaldanha.os.services;

import com.maxsaldanha.os.domain.Cliente;
import com.maxsaldanha.os.domain.OS;
import com.maxsaldanha.os.domain.Tecnico;
import com.maxsaldanha.os.domain.enums.Prioridade;
import com.maxsaldanha.os.domain.enums.Status;
import com.maxsaldanha.os.dtos.OSDTO;
import com.maxsaldanha.os.repositories.OSRepository;
import com.maxsaldanha.os.repositories.TecnicoRepository;
import com.maxsaldanha.os.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OSService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id){
        Optional<OS> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException(
                "Ordem de serviço não encontrada! ID:"
                        + id + ", Tipo: "
                        + OS.class.getName()));
    }

    public List<OS> findAll (){
        return osRepository.findAll();
    }

    public OS create(@Valid OSDTO obj) {
        return fromDTO(obj);
    }

    public OS update(OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    private OS fromDTO(OSDTO obj){
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setDescricao(obj.getDescricao());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cliente);

        if (newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return osRepository.save(newObj);
    }


}
