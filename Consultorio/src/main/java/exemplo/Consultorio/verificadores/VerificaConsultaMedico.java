package exemplo.Consultorio.verificadores;


import exemplo.Consultorio.erros.InsertAgendaExcption;
import exemplo.Consultorio.repositorios.ConsultaRepository;

public class VerificaConsultaMedico extends VerificadorConsulta{

	private ConsultaRepository consultaRepository;
	
	public VerificaConsultaMedico(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	
	@Override
	protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
		
		 if (consultaRepository.existsByMedicoAndDataHoraConsultaAndCancelamento(contexto.getMedico().get(), contexto.getDataHora() ,true)) {
	        throw new InsertAgendaExcption("O médico já possui uma consulta na mesma data/hora");
	     }
		
	}

}
