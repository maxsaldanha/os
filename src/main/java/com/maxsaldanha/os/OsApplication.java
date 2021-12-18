package com.maxsaldanha.os;

import com.maxsaldanha.os.domain.Cliente;
import com.maxsaldanha.os.domain.OS;
import com.maxsaldanha.os.domain.Tecnico;
import com.maxsaldanha.os.domain.enums.Prioridade;
import com.maxsaldanha.os.domain.enums.Status;
import com.maxsaldanha.os.repositories.ClienteRepository;
import com.maxsaldanha.os.repositories.OSRepository;
import com.maxsaldanha.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@SpringBootApplication
public class OsApplication  {
	public static void main(String[] args) {
		SpringApplication.run(OsApplication.class, args);
	}
	@RequestMapping("/")
	String index() {
		return "index";
	}
}
