package br.com.os;

import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import br.com.os.model.Cliente;
import br.com.os.model.OrdemServico;
import br.com.os.model.Tecnico;
import br.com.os.repositories.ClienteRepository;
import br.com.os.repositories.OrdemServicoRepository;
import br.com.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsApplication.class, args);
    }


}
