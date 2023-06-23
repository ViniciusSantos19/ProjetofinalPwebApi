package exemplo.Consultorio.entidades;

import exemplo.Consultorio.Dtos.EnderecoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Enderecos")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = true)
	private String complemento;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String unidadeFederal;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String cep;
	
	public Endereco() {
		
	}
	
	public Endereco(EnderecoDto enderecoDto) {
		this.logradouro = enderecoDto.logradouro();
		this.complemento = enderecoDto.complemento();
		this.bairro = enderecoDto.bairro();
		this.cep = enderecoDto.cep();
		this.unidadeFederal = enderecoDto.unidadeFederal();
		this.cidade = enderecoDto.cidade();
		this.numero = enderecoDto.numero();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUnidadeFederal() {
		return unidadeFederal;
	}

	public void setUnidadeFederal(String unidadeFederal) {
		this.unidadeFederal = unidadeFederal;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
