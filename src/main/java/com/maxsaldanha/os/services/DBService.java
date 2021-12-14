package com.maxsaldanha.os.services;

import com.maxsaldanha.os.domain.Cliente;
import com.maxsaldanha.os.domain.OS;
import com.maxsaldanha.os.domain.Tecnico;
import com.maxsaldanha.os.domain.enums.Prioridade;
import com.maxsaldanha.os.domain.enums.Status;
import com.maxsaldanha.os.repositories.ClienteRepository;
import com.maxsaldanha.os.repositories.OSRepository;
import com.maxsaldanha.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OSRepository osRepository;

    public void instanciaDB(){

        Tecnico t1 = new Tecnico(null, "Maxuel Saldanha da Silva","076.886.717-79","(21) 980776367");
        Tecnico t2 = new Tecnico(null, "Lucas Carvalho Lima", "143.987.950-81","(21) 986578454");
        Cliente c1 = new Cliente (null,"Adilson Barbosa", "912.244.140-98","(21) 982775459");
        OS os1 = new OS(null, Prioridade.ALTA,"TEsdet", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));

    }
}
