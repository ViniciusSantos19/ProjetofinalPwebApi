package exemplo.Consultorio.repositorios;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.Consultorio.entidades.Consulta;
import exemplo.Consultorio.entidades.Medico;
import exemplo.Consultorio.entidades.Paciente;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	public boolean existsByPacienteAndDataHoraConsultaBetween(Paciente paciente, LocalDateTime inicio, LocalDateTime fim);

	public boolean existsByMedicoAndDataHoraConsulta(Medico medico, LocalDateTime dataHoraConsulta);


	
}
