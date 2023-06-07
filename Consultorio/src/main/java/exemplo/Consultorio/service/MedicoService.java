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


import exemplo.Consultorio.Dtos.MedicoDto;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.repositorios.MedicoRepository;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	private List<MedicoDto> converteEmMedicoDto(List<Medico> lista){
		return lista.stream().map(a -> new MedicoDto(a.getNome(),
				a.getTelefone(),
				a.getEmail(),
				a.getCrm(),
				a.getEspecialidade())).collect(Collectors.toList());
	}
	
	public List<MedicoDto> listarMedicos() {
     
        return this.converteEmMedicoDto(repository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }
	
	   public ResponseEntity<MedicoDto> updateMedico(MedicoDto medicoDto, Long id){
	        Optional<Medico> medicoOptional = repository.findById(id);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            medico.setId(id);
	            medico.setNome(medicoDto.nome());
	            medico.setTelefone(medicoDto.telefone());
	            repository.save(medico);
	            return new ResponseEntity<MedicoDto>(new MedicoDto(medico.getNome(),
	    				medico.getTelefone(),
	    				medico.getEmail(),
	    				medico.getCrm(),
	    				medico.getEspecialidade()),HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<MedicoDto> deleteMedico(Long id){
	        Optional<Medico> medicoOptional = repository.findById(id);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            medico.setId(id);
	            medico.setAtividade(false);
	            
	            
	            ResponseEntity<MedicoDto> ent = new ResponseEntity<MedicoDto>(new MedicoDto(medico.getNome(),
	    				medico.getTelefone(),
	    				medico.getEmail(),
	    				medico.getCrm(),
	    				medico.getEspecialidade()),HttpStatus.OK);
	            
	            repository.save(medico);
	            
	            return ent;
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<MedicoDto> insertIntoMedico(MedicoDto medicoDto, UriComponentsBuilder uriBuilder){
		   Medico medico = new Medico(medicoDto);
		   repository.save(medico);
		   URI url = uriBuilder.path("/Medicos/{id}").buildAndExpand(medico.getId()).toUri();
		   return ResponseEntity.created(url).body(new MedicoDto(
				   medico.getNome(),
				   medico.getTelefone(),
				   medico.getEmail(),
				   medico.getCrm(),
				   medico.getEspecialidade()
				   ));
	   }
             
	   
	
}
