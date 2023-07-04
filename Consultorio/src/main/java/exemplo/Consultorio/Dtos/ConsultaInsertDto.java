package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

public record ConsultaInsertDto(String crm, String cpf, LocalDateTime dataHora) {

}
  