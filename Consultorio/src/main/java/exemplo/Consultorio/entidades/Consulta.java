package exemplo.Consultorio.entidades;

import java.time.LocalDateTime;

import exemplo.Consultorio.Dtos.ConsultaDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity(name = "Consultas")
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "id_medico")
	@ManyToOne(cascade = CascadeType.ALL)
	private Medico medico;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@NotNull
    private LocalDateTime dataHoraConsulta;
	
	@Column(name = "motivo_cancelamento")
	private MotivoCancelamento motivoCancelamento;

	public Consulta() {
		
	}
	
	public Consulta(ConsultaDto dto) {
		this.medico = new Medico(dto.medico());
		this.paciente = new Paciente(dto.paciente());
		this.dataHoraConsulta = dto.dataHora();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getDataHora() {
		return dataHoraConsulta;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHoraConsulta = dataHora;
	}
	
	
	
}
