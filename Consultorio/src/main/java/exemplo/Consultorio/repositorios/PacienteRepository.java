package exemplo.Consultorio.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import exemplo.Consultorio.entidades.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{
	Optional<Paciente> findByCpf(String cpf);
	@Query("SELECT p FROM Pacientes p WHERE p.atividade = true")
    Page<Paciente> findByAtivoTrue(Pageable pageable);
}
