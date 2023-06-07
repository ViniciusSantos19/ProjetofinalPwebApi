package exemplo.Consultorio.Dtos;

import exemplo.Consultorio.entidades.Especialidade;

public record MedicoDto(String nome, String telefone, 
		String email, String crm, Especialidade especialidade) {

}
