package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

public record ConsultaSelectDto(String nomeMedico, String nomePacinete, Long id, LocalDateTime dataHora) {

}
