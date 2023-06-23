package exemplo.Consultorio.utils;

import exemplo.Consultorio.Dtos.EnderecoDto;
import exemplo.Consultorio.entidades.Endereco;

public class EnderecoUtils {
	public static EnderecoDto converteEnderecoDto(Endereco endereco) {
		return new EnderecoDto(endereco.getLogradouro(), endereco.getComplemento(), endereco.getNumero(), endereco.getBairro(), endereco.getUnidadeFederal(), endereco.getCidade(), endereco.getCep());
	}
}
