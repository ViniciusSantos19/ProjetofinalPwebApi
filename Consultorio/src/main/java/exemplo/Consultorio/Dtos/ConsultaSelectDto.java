package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

public record ConsultaSelectDto(String nomeMedico, String nomePaciente, Long id, LocalDateTime dataHora) {

}
