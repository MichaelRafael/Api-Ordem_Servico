package br.com.os.controllers;

import br.com.os.dtos.OrdemServicoDTO;
import br.com.os.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    private static final String ID = "/{id}";

    @GetMapping(ID)
    public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id) {
        OrdemServicoDTO objDTO = new OrdemServicoDTO(ordemServicoService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoDTO>> findAllOrdemServico() {
        List<OrdemServicoDTO> listDTO = ordemServicoService.findAllOrdemServico().stream()
                .map(obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoDTO> sevaOrdemSevico(@Valid @RequestBody OrdemServicoDTO osDTO) {
        osDTO = new OrdemServicoDTO(ordemServicoService.seveOrdemServico(osDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(osDTO).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OrdemServicoDTO> upDateOrdemServico(@Valid @RequestBody OrdemServicoDTO osDTO) {
        osDTO = new OrdemServicoDTO(ordemServicoService.upDateOrdemServico(osDTO));
        return  ResponseEntity.ok().body(osDTO);
    }
}
