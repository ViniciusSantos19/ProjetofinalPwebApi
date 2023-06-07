package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

public record ConsultaDto(PacienteDto paciente, MedicoDto medico, 
		LocalDateTime dataHora) {

}
