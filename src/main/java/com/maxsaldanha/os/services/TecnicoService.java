package com.maxsaldanha.os.services;

import com.maxsaldanha.os.domain.Tecnico;
import com.maxsaldanha.os.dtos.TecnicoDTO;
import com.maxsaldanha.os.repositories.TecnicoRepository;
import com.maxsaldanha.os.services.exceptions.DataIntegratyViolationException;
import com.maxsaldanha.os.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException("Técnico não encontrado! Id: "+ id + ", Tipo: "+ Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO){
        if(findByCPF(objDTO) != null)
            throw  new DataIntegratyViolationException("CPF já cadastrado!");
        return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }
    /*A implementação do código, realiza a validação do obj antes da transação com o banco de dados */
    private Tecnico findByCPF (TecnicoDTO objDTO){
        Tecnico obj = repository.findByCPF(objDTO.getCpf());
        if(obj != null) {
            return obj;
        }
        return null;
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        Tecnico oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw  new DataIntegratyViolationException("CPF já cadastrado!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnido possui ordens de serviços, não pode ser deletado");
        }
        repository.deleteById(id);
    }
}
