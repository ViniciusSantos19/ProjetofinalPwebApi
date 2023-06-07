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

import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pacientes")
public class PacienteController {
	
	@Autowired
	private PacienteService service;
	
	@GetMapping
	public List<PacienteDto> getAll(){
		return service.listarPacientes();
	}
	
	@PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid PacienteDto pacienteDto){
        return service.updatePaciente(pacienteDto, id);
    }
	
	@DeleteMapping("/{id}")
	@Transactional
    public ResponseEntity<PacienteDto> apagar(@PathVariable(value="id")  Long id){
	        return service.deletePaciente(id);	    
	}
	
	@PostMapping
	public ResponseEntity<PacienteDto> inserir(@RequestBody @Valid PacienteDto pacienteDto, UriComponentsBuilder uriBuilder){
		return service.insertIntoPaciente(pacienteDto, uriBuilder);
	}
	
}
