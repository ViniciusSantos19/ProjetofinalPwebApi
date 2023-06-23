package exemplo.Consultorio.Dtos;

import exemplo.Consultorio.entidades.Especialidade;

public record MedicoListagemDto(String nome, String email, String crm,
		Especialidade especialidade) {

}
