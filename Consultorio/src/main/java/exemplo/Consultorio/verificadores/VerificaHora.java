package exemplo.Consultorio.verificadores;

import java.time.LocalTime;

import exemplo.Consultorio.erros.InsertAgendaExcption;

public class VerificaHora extends VerificadorConsulta{

	@Override
	protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
		LocalTime horaConsulta = contexto.getDataHora().toLocalTime();
        if (horaConsulta.isBefore(LocalTime.of(7, 0)) || horaConsulta.isAfter(LocalTime.of(19, 0))) {
        	throw new InsertAgendaExcption("horário inválido");
        }
	}

	
        
	

}
