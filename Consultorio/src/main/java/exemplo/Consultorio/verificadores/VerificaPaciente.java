package exemplo.Consultorio.verificadores;

import exemplo.Consultorio.erros.InsertAgendaExcption;

public class VerificaPaciente extends VerificadorConsulta{

	public VerificaPaciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
		if (!contexto.pacienteIsPresent() || contexto.pacienteIsAtivo() == false) {
	           throw new InsertAgendaExcption("Paciente não encontrado ou inativo");
	        }
	}
	
	

}
