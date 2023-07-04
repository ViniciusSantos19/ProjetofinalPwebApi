package exemplo.Consultorio.utils;

import exemplo.Consultorio.Dtos.EnderecoDto;
import exemplo.Consultorio.entidades.Endereco;

public class EnderecoUtils {
	public static EnderecoDto converteEnderecoDto(Endereco endereco) {
		return new EnderecoDto(endereco.getLogradouro(), endereco.getComplemento(), endereco.getNumero(), endereco.getBairro(), endereco.getUnidadeFederal(), endereco.getCidade(), endereco.getCep());
	}
	public static Endereco checaSeEnderecoMudou(Endereco endereco, EnderecoDto dto) {
		Integer numero = dto.numero();
		if(!dto.bairro().isBlank()) {
			endereco.setBairro(dto.bairro());
		}
		if(!dto.cep().isBlank()) {
			endereco.setCep(dto.cep());
		}
		if(!dto.logradouro().isBlank()){
			endereco.setLogradouro(dto.logradouro());
		}
		
		if(numero != null || numero != 0) {
			endereco.setNumero(dto.numero());
		}
		
		if(!dto.cidade().isBlank()) {
			endereco.setCidade(dto.cidade());
		}
		
		if(!dto.complemento().isBlank()) {
			endereco.setComplemento(dto.complemento());
		}
		
		if(!dto.unidadeFederal().isBlank()) {
			endereco.setUnidadeFederal(dto.unidadeFederal());
		}
		return endereco;
			}
		}
