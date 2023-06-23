package exemplo.Consultorio.utils;

import exemplo.Consultorio.Dtos.MedicoDto;
import exemplo.Consultorio.Dtos.MedicoListagemDto;
import exemplo.Consultorio.entidades.Medico;

public class MedicoUtils {
	public static MedicoDto converteMedicoDto(Medico medico) {
		return new MedicoDto(medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), EnderecoUtils.converteEnderecoDto(medico.getEndereco()));
	}
	
	public static MedicoListagemDto converteMedicoListagemDto(Medico medico) {
		return new MedicoListagemDto(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
