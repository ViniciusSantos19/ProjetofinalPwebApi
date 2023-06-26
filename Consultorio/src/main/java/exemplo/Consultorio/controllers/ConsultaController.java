package exemplo.Consultorio.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.Consultorio.Dtos.ConsultaDeletadaDto;
import exemplo.Consultorio.Dtos.ConsultaDeleteDto;
import exemplo.Consultorio.Dtos.ConsultaDto;
import exemplo.Consultorio.Dtos.ConsultaInsertDto;
import exemplo.Consultorio.erros.InsertAgendaExcption;
import exemplo.Consultorio.erros.SemMedicosDisponiveisException;
import exemplo.Consultorio.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Consultas")
public class ConsultaController {
	
	@Autowired
	private ConsultaService service;
	
	@PostMapping()
	public ResponseEntity<ConsultaDto> inserir(@RequestBody ConsultaInsertDto consultaDto,UriComponentsBuilder uriBuilder) throws InsertAgendaExcption, SemMedicosDisponiveisException{
		LocalDateTime dataHoraConsulta = consultaDto.dataHora();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formattedDateTime = consultaDto.dataHora().format(formatter);
		dataHoraConsulta = LocalDateTime.parse(formattedDateTime);

		return service.agendarConsulta(consultaDto, dataHoraConsulta, uriBuilder);
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity<ConsultaDeletadaDto> cancelarConsulta(@RequestBody @Valid ConsultaDeleteDto dto) {
		return service.cancelarConsulta(dto);
	}
	
}
