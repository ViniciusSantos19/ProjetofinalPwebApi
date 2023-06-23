package exemplo.Consultorio.utils;

import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.Dtos.PacienteListagemDto;
import exemplo.Consultorio.entidades.Paciente;

public class PacienteUtils {
	
	public static  PacienteDto convertePacienteDto(Paciente paciente) {
		return new PacienteDto(paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), EnderecoUtils.converteEnderecoDto(paciente.getEndereco()));
	}
	
	public static PacienteListagemDto convertePacienteListagemDto(Paciente paciente) {
		return new PacienteListagemDto(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}
}
