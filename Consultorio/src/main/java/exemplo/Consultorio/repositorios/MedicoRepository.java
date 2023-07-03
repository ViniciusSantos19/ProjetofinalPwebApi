package exemplo.Consultorio.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import exemplo.Consultorio.entidades.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	Optional<Medico> findByCrm(String crm);
	@Query("SELECT m FROM Medicos m WHERE m.atividade = true")
    Page<Medico> findByAtivoTrue(Pageable pageable);
}
