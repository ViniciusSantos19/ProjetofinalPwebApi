package exemplo.Consultorio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import exemplo.Consultorio.Dtos.MedicoDto;
import exemplo.Consultorio.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService service;
	
	@GetMapping
	public List<MedicoDto> getAll(){
		return service.listarMedicos();
	}
	
	@PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid MedicoDto medicoDto){
        return service.updateMedico(medicoDto, id);
    }
	
	@DeleteMapping("/{id}")
	@Transactional
    public ResponseEntity<MedicoDto> apagar(@PathVariable(value="id")  Long id){
	        return service.deleteMedico(id);	    
	}
	
	@PostMapping
	public ResponseEntity<MedicoDto> inserir(@RequestBody @Valid MedicoDto medicoDto, UriComponentsBuilder uriBuilder){
		return service.insertIntoMedico(medicoDto, uriBuilder);
	}
	
}
