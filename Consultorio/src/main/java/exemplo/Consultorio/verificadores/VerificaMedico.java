package exemplo.Consultorio.verificadores;

import exemplo.Consultorio.erros.InsertAgendaExcption;

public class VerificaMedico extends VerificadorConsulta{

	@Override
	protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
		if (!contexto.medicoIsPresent() || contexto.medicoIsAtivo() == false) {
	           throw new InsertAgendaExcption("Paciente não encontrado ou inativo");
	        }
	}
	
	

}
