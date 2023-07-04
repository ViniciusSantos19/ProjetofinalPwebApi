package exemplo.Consultorio.utils;

import exemplo.Consultorio.Dtos.ConsultaSelectDto;
import exemplo.Consultorio.entidades.Consulta;

public class ConsultaUtils {
	public static ConsultaSelectDto converteConsultaEmSelect(Consulta consulta) {
		return new ConsultaSelectDto(consulta.getMedicoNome(), consulta.getPacienteNome(), consulta.getId(), consulta.getDataHora());
	}
}

