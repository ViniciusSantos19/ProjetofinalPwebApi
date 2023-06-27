package exemplo.Consultorio.verificadores;

import java.time.LocalDate;


import exemplo.Consultorio.erros.InsertAgendaExcption;
import exemplo.Consultorio.repositorios.ConsultaRepository;

public class VerificaConsultaPaciente extends VerificadorConsulta{
	
	private ConsultaRepository consultaRepository;
	
	public VerificaConsultaPaciente(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	
	@Override
	protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
		 LocalDate dataConsulta = contexto.getDataHora().toLocalDate();
	        if (consultaRepository.existsByPacienteAndDataHoraConsultaBetweenAndCancelamento(contexto.getPaciente().get(), 
	                dataConsulta.atStartOfDay(), dataConsulta.plusDays(1).atStartOfDay(), true)) {
	        	throw new InsertAgendaExcption("Já tem outra consulta do mesmo paciente");
	        }
		
	}
	
}
