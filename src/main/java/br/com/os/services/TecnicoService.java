package br.com.os.services;

import br.com.os.dtos.TecnicoDTO;
import br.com.os.exceptions.DataIntegrityViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Pessoa;
import br.com.os.model.Tecnico;
import br.com.os.repositories.PessoaRepository;
import br.com.os.repositories.TecnicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Tecnico findTecnicoById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id
                        + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAllTecnicos() {
        return tecnicoRepository.findAll();
    }

    public Tecnico saveTecnico(@Valid TecnicoDTO tecnicoDTO) {
        if (findByCPF(tecnicoDTO) != null) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        return tecnicoRepository.save(modelMapper.map(tecnicoDTO, Tecnico.class));
    }

    public Tecnico upDateTecnico(Integer id, TecnicoDTO tecnicoDTO) {
        findTecnicoById(id);

        if (findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        tecnicoDTO.setId(id);
        return tecnicoRepository.save(modelMapper.map(tecnicoDTO, Tecnico.class));
    }

    public void deleteTecnico(Integer id) {
        Tecnico tecnico = findTecnicoById(id);

        if (tecnico.getList().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    private Pessoa findByCPF(TecnicoDTO tecnicoDTO) {
        Pessoa obj = pessoaRepository.findByCPF(tecnicoDTO.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }
}
