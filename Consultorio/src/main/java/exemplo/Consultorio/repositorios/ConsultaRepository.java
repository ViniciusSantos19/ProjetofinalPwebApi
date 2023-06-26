package exemplo.Consultorio.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import exemplo.Consultorio.entidades.Consulta;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.entidades.Paciente;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	public boolean existsByPacienteAndDataHoraConsultaBetween(Paciente paciente, LocalDateTime inicio, LocalDateTime fim);

	public boolean existsByMedicoAndDataHoraConsulta(Medico medico, LocalDateTime dataHoraConsulta);

	@Query("SELECT c.medico FROM Consulta c WHERE c.dataHora <> :dataHora")
    List<Medico> findMedicosDisponiveis(@Param("dataHora") LocalDateTime dataHora);
	
}
