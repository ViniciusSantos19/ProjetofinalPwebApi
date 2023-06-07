package exemplo.Consultorio.verificadores;

import exemplo.Consultorio.erros.InsertAgendaExcption;

public abstract class VerificadorConsulta {
	private VerificadorConsulta proximo;
	
	public VerificadorConsulta() {
		
	}
	
	public void setProximo(VerificadorConsulta proximo) {
		this.proximo = proximo;
	}
	
	public void verifica(ConsultaContext contexto) throws InsertAgendaExcption {
		verificaInterno(contexto);
		
		if(proximo != null) {
			proximo.verifica(contexto);
		}
		
	}
	
	protected abstract void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption;
	
}
