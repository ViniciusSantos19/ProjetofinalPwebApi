package exemplo.Consultorio.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import exemplo.Consultorio.verificadores.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.Consultorio.Dtos.ConsultaDto;
import exemplo.Consultorio.Dtos.ConsultaInsertDto;
import exemplo.Consultorio.Dtos.MedicoDto;
import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.entidades.Consulta;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.entidades.Paciente;
import exemplo.Consultorio.erros.InsertAgendaExcption;
import exemplo.Consultorio.repositorios.ConsultaRepository;
import exemplo.Consultorio.repositorios.MedicoRepository;
import exemplo.Consultorio.repositorios.PacienteRepository;

@Service
public class ConsultaService {
	
		@Autowired
	    private ConsultaRepository consultaRepository;
	    
	    @Autowired
	    private PacienteRepository pacienteRepository;
	    
	    @Autowired
	    private MedicoRepository medicoRepository;
	    
	    public ResponseEntity<ConsultaDto> agendarConsulta(ConsultaInsertDto consultaDto,LocalDateTime dataHora, UriComponentsBuilder uriBuilder) throws InsertAgendaExcption {
	        
	        Optional<Paciente> pacienteOptional = pacienteRepository.findById(consultaDto.pacienteId());
	    	Optional<Medico> medicoOptional = medicoRepository.findById(consultaDto.medicoId());
	    	
	    	ConsultaContext contexto = new ConsultaContext(medicoOptional, pacienteOptional, dataHora);
	    	
	    	realizarVerificações(contexto);
	    	
	    	Paciente paciente = pacienteOptional.get();
	        
	        Medico medico = medicoOptional.get();
	     
	        Consulta consulta = new Consulta();
	        consulta.setPaciente(paciente);
	        consulta.setMedico(medico);
	        consulta.setDataHora(consultaDto.dataHora());
	        
	        consultaRepository.save(consulta);
	        
	        URI url = uriBuilder.path("/Consultas/{id}").buildAndExpand(consulta.getId()).toUri();
	        return ResponseEntity.created(url).body(converteEmConsultaDto(consulta));
	    }
	
	    private ConsultaDto converteEmConsultaDto(Consulta consulta) {
	        PacienteDto pacienteDto = new PacienteDto(consulta.getPaciente().getNome(), consulta.getPaciente().getTelefone(), consulta.getPaciente().getEmail(), consulta.getPaciente().getCpf());
	        MedicoDto medicoDto = new MedicoDto(consulta.getMedico().getNome(), consulta.getMedico().getTelefone(), consulta.getMedico().getEmail(), consulta.getMedico().getCrm(), consulta.getMedico().getEspecialidade());
	        LocalDateTime dataHoraConsulta = consulta.getDataHora();
	        return new ConsultaDto(pacienteDto, medicoDto, dataHoraConsulta);
	    }
	    
	    private void realizarVerificações(ConsultaContext contexto) throws InsertAgendaExcption {
	    	
	    	VerificaPaciente verificaPaciente = new VerificaPaciente();
	    	VerificaMedico verificaMedico = new VerificaMedico();
	    	VerificaHora verificaHora = new VerificaHora();
			VerificaDiaSemana verificaDiaSemana = new VerificaDiaSemana();
			VerificaAntecedencia verificaAntecedencia = new VerificaAntecedencia();
	    	VerificaConsultaPaciente verficaConsultaPaciente = new VerificaConsultaPaciente(consultaRepository);
	    	VerificaConsultaMedico verificaConsultaMedico = new VerificaConsultaMedico(consultaRepository);
	    	
	    	verificaPaciente.setProximo(verificaMedico);
	    	verificaMedico.setProximo(verificaHora);
	    	verificaHora.setProximo(verificaDiaSemana);
			verificaDiaSemana.setProximo(verificaAntecedencia);
			verificaAntecedencia.setProximo(verficaConsultaPaciente);
	    	verficaConsultaPaciente.setProximo(verificaConsultaMedico);
	    	
	    	verificaPaciente.verifica(contexto);
	    }
	    
}
