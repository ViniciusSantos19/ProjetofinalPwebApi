package exemplo.Consultorio.entidades;

import exemplo.Consultorio.Dtos.PacienteDto;
import exemplo.Consultorio.Dtos.PacienteListagemDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "Pacientes")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "Ativo")
	private Boolean atividade = true;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
    private Endereco endereco;
	
	public Boolean isAtivo() {
		return this.atividade;
	}
	
	public void setAtividade(Boolean atividade) {
		this.atividade = atividade;
	}
	
	public Paciente() {
		
	}
	
	public Paciente(PacienteDto dto) {
	    this.nome = dto.nome();
	    this.email = dto.email();
	    this.cpf = dto.cpf();
	    this.telefone = dto.telefone();
	    this.endereco = new Endereco(dto.endereco());
	}
	
	public Paciente(PacienteListagemDto dto) {
		
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
