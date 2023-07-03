package exemplo.Consultorio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.Consultorio.entidades.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	Optional<Medico> findByCrm(String crm);
}
