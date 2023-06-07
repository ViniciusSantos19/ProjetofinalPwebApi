package exemplo.Consultorio.verificadores;

import java.time.LocalDateTime;
import java.util.Optional;

import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.entidades.Paciente;

public class ConsultaContext {
	private Optional<Medico> medico;
	private Optional<Paciente> paciente;
	private LocalDateTime dataHora;
	
	public ConsultaContext(Optional<Medico> medico, Optional<Paciente> paciente, LocalDateTime dataHora) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.dataHora = dataHora;
	}

	public Optional<Medico> getMedico() {
		return medico;
	}

	public Optional<Paciente> getPaciente() {
		return paciente;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public Boolean pacienteIsPresent() {
		return paciente.isPresent();
	}
	
	public Boolean medicoIsPresent() {
		return medico.isPresent();
	}
	
	public Boolean pacienteIsAtivo() {
		return paciente.get().isAtivo();
	}
	
	public Boolean medicoIsAtivo() {
		return medico.get().isAtivo();
	}
	
}
