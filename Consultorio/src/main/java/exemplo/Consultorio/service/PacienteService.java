package exemplo.Consultorio.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.entidades.Paciente;
import exemplo.Consultorio.repositorios.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	private List<PacienteDto> converteEmPacienteDto(List<Paciente> lista){
		return lista.stream().map(a -> new PacienteDto(a.getNome(),
				a.getTelefone(),
				a.getEmail(),
				a.getCpf())).collect(Collectors.toList());
	}
	
	public List<PacienteDto> listarPacientes() {
        return this.converteEmPacienteDto(repository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }
	
	   public ResponseEntity<PacienteDto> updatePaciente(PacienteDto pacienteDto, Long id){
	        Optional<Paciente> pacienteOptional = repository.findById(id);
	        if(pacienteOptional.isPresent()){
	            Paciente paciente = pacienteOptional.get();
	            paciente.setId(id);
	            paciente.setNome(pacienteDto.nome());
	            paciente.setTelefone(pacienteDto.telefone());
	            repository.save(paciente);
	            return new ResponseEntity<PacienteDto>( new PacienteDto(
	            		paciente.getNome(),
	    				paciente.getTelefone(),
	    				paciente.getEmail(),
	    				paciente.getCpf()),HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<PacienteDto> deletePaciente(Long id){
	        Optional<Paciente> pacienteOptional = repository.findById(id);
	        if(pacienteOptional.isPresent()){
	            Paciente paciente = pacienteOptional.get();
	            paciente.setId(id);
	            paciente.setAtividade(false);
	            
	            ResponseEntity<PacienteDto> ent = new ResponseEntity<PacienteDto>(new PacienteDto(
	            		paciente.getNome(),
	    				paciente.getTelefone(),
	    				paciente.getEmail(),
	    				paciente.getCpf()),HttpStatus.OK);
	            
	            repository.save(paciente);
	            
	            return ent;
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<PacienteDto> insertIntoPaciente(PacienteDto PacienteDto, UriComponentsBuilder uriBuilder){
		   Paciente paciente = new Paciente(PacienteDto);
		   repository.save(paciente);
		   URI url = uriBuilder.path("/Pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		   return ResponseEntity.created(url).body(new PacienteDto(
				paciente.getNome(),
   				paciente.getTelefone(),
   				paciente.getEmail(),
   				paciente.getCpf()
				   ));
	   }
	   
	
}
