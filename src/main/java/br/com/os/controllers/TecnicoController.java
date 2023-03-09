package br.com.os.controllers;

import br.com.os.dtos.TecnicoDTO;
import br.com.os.model.Tecnico;
import br.com.os.services.TecnicoService;
import org.modelmapper.ModelMapper;
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
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    private static final String ID = "/{id}";

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(ID)
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(modelMapper.map(tecnicoService.findTecnicoById(id), TecnicoDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
         return ResponseEntity.ok().body(tecnicoService.findAllTecnicos().stream().map(obj -> modelMapper.map(
                 obj, TecnicoDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> saveTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(tecnicoService
                .saveTecnico(tecnicoDTO).getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PutMapping(ID)
    public ResponseEntity<TecnicoDTO> upDateTecnico(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico newTecnico = tecnicoService.upDateTecnico(id, tecnicoDTO);

        return ResponseEntity.ok().body(modelMapper.map(tecnicoService.findTecnicoById(id), TecnicoDTO.class));
    }

    /*
    Deletar um técnico
     */
    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteTecnico(@PathVariable Integer id) {
        tecnicoService.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }

}
