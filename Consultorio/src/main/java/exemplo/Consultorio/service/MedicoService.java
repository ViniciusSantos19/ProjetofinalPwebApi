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


import exemplo.Consultorio.Dtos.MedicoDto;
import exemplo.Consultorio.Dtos.MedicoListagemDto;
import exemplo.Consultorio.entidades.Endereco;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.repositorios.MedicoRepository;
import exemplo.Consultorio.utils.EnderecoUtils;
import exemplo.Consultorio.utils.MedicoUtils;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository repository;
	
	private List<MedicoListagemDto> converteEmMedicoDto(List<Medico> lista){
		return lista.stream().map(a -> MedicoUtils.converteMedicoListagemDto(a)).collect(Collectors.toList());
	}
	
	public List<MedicoListagemDto> listarMedicos(int pagina) {
		int registrosPorPagina = 10;
	    PageRequest pageRequest = PageRequest.of(pagina - 1, registrosPorPagina, Sort.by("nome").ascending());
	    Page<Medico> medicosPage = repository.findByAtivoTrue(pageRequest);
	    List<Medico> medicos = medicosPage.getContent();
        return this.converteEmMedicoDto(medicos);
    }
	
	   public ResponseEntity<MedicoDto> updateMedico(MedicoDto medicoDto, String crm){
	        Optional<Medico> medicoOptional = repository.findByCrm(crm);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            MedicoUtils.checaSeMedicoMudou(medico, medicoDto);
	            Endereco endereco = EnderecoUtils.checaSeEnderecoMudou(medico.getEndereco(), medicoDto.endereco());
	            medico.setEndereco(endereco);
	            repository.save(medico);
	            return new ResponseEntity<MedicoDto>(MedicoUtils.converteMedicoDto(medico),HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	   
	   public ResponseEntity<MedicoDto> deleteMedico(String crm){
	        Optional<Medico> medicoOptional = repository.findByCrm(crm);
	        if(medicoOptional.isPresent()){
	            Medico medico = medicoOptional.get();
	            medico.setCrm(crm);;
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
