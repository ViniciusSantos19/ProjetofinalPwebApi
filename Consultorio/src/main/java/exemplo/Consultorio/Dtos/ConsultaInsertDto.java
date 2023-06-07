package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

public record ConsultaInsertDto(Long medicoId, Long pacienteId, LocalDateTime dataHora) {

}
