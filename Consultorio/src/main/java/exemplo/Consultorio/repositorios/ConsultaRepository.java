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

	public boolean existsByMedicoAndDataHoraConsultaAndCancelamento(Medico medico, LocalDateTime dataHoraConsulta, boolean cancelamento);

	public boolean existsByPacienteAndDataHoraConsultaBetweenAndCancelamento(Paciente paciente, LocalDateTime inicio, LocalDateTime fim, boolean cancelamento);

	@Query("SELECT m FROM Medicos m WHERE m.id NOT IN " +
	           "(SELECT c.medico.id FROM Consultas c WHERE c.dataHoraConsulta = :dataHoraConsulta)")
	public List<Medico> findMedicosDisponiveis(@Param("dataHoraConsulta") LocalDateTime dataHoraConsulta);

	
}
