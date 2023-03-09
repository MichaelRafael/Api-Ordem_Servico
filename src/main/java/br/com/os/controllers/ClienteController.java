package br.com.os.controllers;

import br.com.os.dtos.ClienteDTO;
import br.com.os.model.Cliente;
import br.com.os.services.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    private static final String ID = "/{id}";

    @GetMapping(ID)
    public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findClienteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        List<Cliente> listClientes = clienteService.findAllClientes();
        return ResponseEntity.ok().body(listClientes.stream().map(obj -> modelMapper.map(obj, ClienteDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.saveCliente(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<ClienteDTO> upDateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.upDateCliente(id, clienteDTO);

        return ResponseEntity.ok().body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
