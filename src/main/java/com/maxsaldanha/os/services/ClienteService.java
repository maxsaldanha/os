package com.maxsaldanha.os.services;

import com.maxsaldanha.os.domain.Pessoa;
import com.maxsaldanha.os.domain.Cliente;
import com.maxsaldanha.os.dtos.ClienteDTO;
import com.maxsaldanha.os.repositories.PessoaRepository;
import com.maxsaldanha.os.repositories.ClienteRepository;
import com.maxsaldanha.os.services.exceptions.DataIntegratyViolationException;
import com.maxsaldanha.os.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotfoundException("Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO){
        if(findByCPF(objDTO) != null){
            throw  new DataIntegratyViolationException("CPF já cadastrado!");
        }
        return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
    }
    /*A implementação do código, realiza a validação do obj antes da transação com o banco de dados */
    private Pessoa findByCPF (ClienteDTO objDTO){
        Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());

        if (obj != null){
            return obj;
        }
        return null;
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        Cliente oldObj = findById(id);

        if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id){
            throw  new DataIntegratyViolationException("CPF já cadastrado!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);

        if (obj.getList().size() > 0) {
            throw new DataIntegratyViolationException("Técnido possui ordens de serviços, não pode ser deletado");
        }
        clienteRepository.deleteById(id);
    }
}
