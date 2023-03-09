package br.com.os.services;

import br.com.os.dtos.ClienteDTO;
import br.com.os.exceptions.DataIntegrityViolationException;
import br.com.os.exceptions.ObjectNotFoundException;
import br.com.os.model.Cliente;
import br.com.os.model.Pessoa;
import br.com.os.repositories.ClienteRepository;
import br.com.os.repositories.PessoaRepository;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;


    public Cliente findClienteById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id
                    + ", Tipo " + Cliente.class.getName()));
    }

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(ClienteDTO clienteDTO) {
        if (findPessoaByCPF(clienteDTO) != null) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!!!");
        }

        return clienteRepository.save(modelMapper.map(clienteDTO, Cliente.class));
    }

    public Cliente upDateCliente(Integer id, ClienteDTO clienteDTO) {
        findClienteById(id);

        if (findPessoaByCPF(clienteDTO) != null && findPessoaByCPF(clienteDTO).getId() != id) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        clienteDTO.setId(id);
        return clienteRepository.save(modelMapper.map(clienteDTO, Cliente.class));
    }

    public void deleteCliente(Integer id) {
        Cliente obj = findClienteById(id);

        if (obj.getList().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço, não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    public Pessoa findPessoaByCPF(ClienteDTO clienteDTO) {
        Pessoa obj = pessoaRepository.findByCPF(clienteDTO.getCpf());

        if (obj != null) {
            return obj;
        }
        return null;
    }

}
