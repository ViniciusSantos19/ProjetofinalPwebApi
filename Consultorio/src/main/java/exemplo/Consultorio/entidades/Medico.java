package exemplo.Consultorio.entidades;

import exemplo.Consultorio.Dtos.MedicoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Medicos")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)

	private String nome;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false, unique = true)
	private String crm;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Especialidade  especialidade;
	
	@Column(name = "Ativo")
	private Boolean atividade = true;
	
	public Boolean isAtivo() {
		return this.atividade;
	}
	
	public void setAtividade(Boolean atividade) {
		this.atividade = atividade;
	}
	
	public Medico() {
		
	}
	
	public Medico(MedicoDto dto) {
	    this.nome = dto.nome();
	    this.email = dto.email();
	    this.crm = dto.crm();
	    this.telefone = dto.telefone();
	    this.especialidade = dto.especialidade();
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCrm() {
		return crm;
	}
	
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	
	
}
