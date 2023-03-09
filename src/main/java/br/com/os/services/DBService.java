package br.com.os.services;

import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import br.com.os.model.Cliente;
import br.com.os.model.OrdemServico;
import br.com.os.model.Tecnico;
import br.com.os.repositories.ClienteRepository;
import br.com.os.repositories.OrdemServicoRepository;
import br.com.os.repositories.TecnicoRepository;
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
    private OrdemServicoRepository ordemServicoRepository;

    public void instanciaDB() {

        Tecnico t1 = new Tecnico(null, "Michael Rafael", "041.316.384-95", "81 98701-2423");
        Tecnico t2 = new Tecnico(null, "Hugo Chaves", "712.723.400-07", "81 98701-8888");
        Cliente c1 = new Cliente(null, "Rosangela Conceição", "044.480.044-14", "81 98645-5885");
        OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        ordemServicoRepository.saveAll(Arrays.asList(os1));

    }
}
