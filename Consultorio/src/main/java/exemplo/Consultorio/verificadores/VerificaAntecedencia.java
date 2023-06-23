package exemplo.Consultorio.verificadores;

import exemplo.Consultorio.erros.InsertAgendaExcption;

import java.time.LocalDateTime;

public class VerificaAntecedencia extends VerificadorConsulta{

    @Override
    protected void verificaInterno(ConsultaContext contexto) throws InsertAgendaExcption {
        LocalDateTime dataHoraConsulta = contexto.getDataHora();
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        // Verificar se a consulta está agendada com antecedência mínima de 30 minutos
        if (dataHoraConsulta.isBefore(dataHoraAtual.plusMinutes(30))) {
            throw new InsertAgendaExcption("A consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
