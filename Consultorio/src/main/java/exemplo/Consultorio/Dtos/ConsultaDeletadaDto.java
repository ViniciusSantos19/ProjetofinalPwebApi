package exemplo.Consultorio.Dtos;

import java.time.LocalDateTime;

import exemplo.Consultorio.entidades.MotivoCancelamento;

public record ConsultaDeletadaDto(String nomeMedico, String nomePaciente, MotivoCancelamento motivoCancelamento, LocalDateTime dataHoraCancelamento) {

}
