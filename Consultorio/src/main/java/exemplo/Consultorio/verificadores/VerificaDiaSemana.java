package exemplo.Consultorio.verificadores;

import exemplo.Consultorio.erros.InsertAgendaExcption;

import java.time.DayOfWeek;

public class VerificaDiaSemana extends VerificadorConsulta{

    @Override
    protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
        DayOfWeek diaDaSemana = contexto.getDataHora().getDayOfWeek();
        if(diaDaSemana == DayOfWeek.SUNDAY){
            throw new InsertAgendaExcption("As consultas só podem ser agendadas de segunda a sabado");
        }
    }
}
