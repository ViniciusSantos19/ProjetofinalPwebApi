package exemplo.Consultorio.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import exemplo.Consultorio.Dtos.ConsultaDeletadaDto;
import exemplo.Consultorio.Dtos.ConsultaDeleteDto;
import exemplo.Consultorio.Dtos.ConsultaDto;
import exemplo.Consultorio.Dtos.ConsultaInsertDto;
import exemplo.Consultorio.Dtos.ConsultaSelectDto;
import exemplo.Consultorio.erros.InsertAgendaExcption;
import exemplo.Consultorio.erros.SemMedicosDisponiveisException;
import exemplo.Consultorio.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Consultas")
public class ConsultaController {
	
	@Autowired
	private ConsultaService service;
	
	@GetMapping
	public List<ConsultaSelectDto> getAllCosultas(@RequestParam int pagina) {
		return service.listarConsultas(pagina);
	}
	
	@PostMapping()
	public ResponseEntity<ConsultaDto> inserir(@RequestBody ConsultaInsertDto consultaDto,UriComponentsBuilder uriBuilder) throws InsertAgendaExcption, SemMedicosDisponiveisException{
		LocalDateTime dataHoraConsulta = consultaDto.dataHora();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formattedDateTime = consultaDto.dataHora().format(formatter);
		dataHoraConsulta = LocalDateTime.parse(formattedDateTime);

		return service.agendarConsulta(consultaDto, dataHoraConsulta, uriBuilder);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ConsultaDeletadaDto> cancelarConsulta(@RequestBody @Valid ConsultaDeleteDto dto, @PathVariable Long id) {
		return service.cancelarConsulta(dto, id);
	}
	
}
