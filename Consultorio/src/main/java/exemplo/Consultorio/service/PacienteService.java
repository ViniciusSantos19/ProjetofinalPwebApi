package exemplo.Consultorio.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.Dtos.PacienteListagemDto;
import exemplo.Consultorio.entidades.Endereco;
import exemplo.Consultorio.entidades.Paciente;
import exemplo.Consultorio.repositorios.PacienteRepository;
import exemplo.Consultorio.utils.EnderecoUtils;
import exemplo.Consultorio.utils.PacienteUtils;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	private List<PacienteListagemDto> converteEmPacienteDto(List<Paciente> lista){
		return lista.stream().map(a -> PacienteUtils.convertePacienteListagemDto(a)).collect(Collectors.toList());
	}
	
	public List<PacienteListagemDto> listarPacientes(int pagina) {
		int registrosPorPagina = 10;
	    PageRequest pageRequest = PageRequest.of(pagina - 1, registrosPorPagina, Sort.by("nome").ascending());
	    Page<Paciente> pacientesPage = repository.findByAtivoTrue(pageRequest);
	    List<Paciente> pacientes = pacientesPage.getContent();
        return this.converteEmPacienteDto(pacientes);
    }
	
	   public ResponseEntity<PacienteDto> updatePaciente(PacienteDto pacienteDto, String cpf){
	        Optional<Paciente> pacienteOptional = repository.findByCpf(cpf);
	        if(pacienteOptional.isPresent()){
	            Paciente paciente = pacienteOptional.get();
	            PacienteUtils.checaSePacienteMudou(paciente, pacienteDto);
	            Endereco endereco = EnderecoUtils.checaSeEnderecoMudou(paciente.getEndereco(), pacienteDto.endereco());
	            paciente.setEndereco(endereco);
	            repository.save(paciente);
	            return new ResponseEntity<PacienteDto>(PacienteUtils.convertePacienteDto(paciente),HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<PacienteDto> deletePaciente(String cpf){
	        Optional<Paciente> pacienteOptional = repository.findByCpf(cpf);
	        if(pacienteOptional.isPresent()){
	            Paciente paciente = pacienteOptional.get();
	            paciente.setCpf(cpf);
	            paciente.setAtividade(false);
	            
	            ResponseEntity<PacienteDto> ent = new ResponseEntity<PacienteDto>(PacienteUtils.convertePacienteDto(paciente),HttpStatus.OK);
	            
	            repository.save(paciente);
	            
	            return ent;
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<PacienteDto> insertIntoPaciente(PacienteDto PacienteDto, UriComponentsBuilder uriBuilder){
		   Paciente paciente = new Paciente(PacienteDto);
		   repository.save(paciente);
		   URI url = uriBuilder.path("/Pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		   return ResponseEntity.created(url).body(PacienteUtils.convertePacienteDto(paciente));
	   }
	   
	
}
