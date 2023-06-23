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
import exemplo.Consultorio.Dtos.MedicoListagemDto;
import exemplo.Consultorio.entidades.Endereco;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.repositorios.MedicoRepository;
import exemplo.Consultorio.utils.MedicoUtils;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	private List<MedicoListagemDto> converteEmMedicoDto(List<Medico> lista){
		return lista.stream().map(a -> MedicoUtils.converteMedicoListagemDto(a)).collect(Collectors.toList());
	}
	
	public List<MedicoListagemDto> listarMedicos() {
     
        return this.converteEmMedicoDto(repository.findAll(Sort.by(Sort.Direction.ASC, "nome")));
    }
	
	   public ResponseEntity<MedicoDto> updateMedico(MedicoDto medicoDto, Long id){
	        Optional<Medico> medicoOptional = repository.findById(id);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            medico.setId(id);
	            medico.setNome(medicoDto.nome());
	            medico.setTelefone(medicoDto.telefone());
	            Endereco endereco = new Endereco(medicoDto.endereco());
	            medico.setEndereco(endereco);
	            repository.save(medico);
	            return new ResponseEntity<MedicoDto>(MedicoUtils.converteMedicoDto(medico),HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<MedicoDto> deleteMedico(Long id){
	        Optional<Medico> medicoOptional = repository.findById(id);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            medico.setId(id);
	            medico.setAtividade(false);
	            
	            
	            ResponseEntity<MedicoDto> ent = new ResponseEntity<MedicoDto>(MedicoUtils.converteMedicoDto(medico),HttpStatus.OK);
	            
	            repository.save(medico);
	            
	            return ent;
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<MedicoDto> insertIntoMedico(MedicoDto medicoDto, UriComponentsBuilder uriBuilder){
		   Medico medico = new Medico(medicoDto);
		   repository.save(medico);
		   URI url = uriBuilder.path("/Medicos/{id}").buildAndExpand(medico.getId()).toUri();
		   return ResponseEntity.created(url).body(MedicoUtils.converteMedicoDto(medico));
	   }
             
	   
	
}
