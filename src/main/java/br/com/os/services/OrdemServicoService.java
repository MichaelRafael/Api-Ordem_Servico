package br.com.os.services;

import br.com.os.dtos.OrdemServicoDTO;
import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import br.com.os.exceptions.DataIntegrityViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Cliente;
import br.com.os.model.OrdemServico;
import br.com.os.model.Tecnico;
import br.com.os.repositories.OrdemServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OrdemServico findById(Integer id) {
        Optional<OrdemServico> obj = ordemServicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de serviço não encontrada! ID " + id + ", Tipo: " + OrdemServico.class.getName()));
    }

    public List<OrdemServico> findAllOrdemServico() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico seveOrdemServico(@Valid OrdemServicoDTO osDTO) {
        return fromDTO(osDTO);
    }

    public OrdemServico upDateOrdemServico(@Valid OrdemServicoDTO osDTO) {
        findById(osDTO.getId());
        return fromDTO(osDTO);

    }

    private OrdemServico fromDTO(OrdemServicoDTO osDTO) {
        OrdemServico os = new OrdemServico();
        os.setId(osDTO.getId());
        os.setDataAbertura(LocalDateTime.now());
        os.setObservacoes(osDTO.getObservacoes());
        os.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
        os.setStatus(Status.toEnum(osDTO.getStatus()));

        if (os.getStatus().getCod().equals(2)) {
            os.setDataFechamento(LocalDateTime.now());
        }

        Tecnico tec = tecnicoService.findTecnicoById(osDTO.getTecnico());
        Cliente cli = clienteService.findClienteById(osDTO.getCliente());

        os.setTecnico(tec);
        os.setCliente(cli);

        return ordemServicoRepository.save(os);

    }
}
