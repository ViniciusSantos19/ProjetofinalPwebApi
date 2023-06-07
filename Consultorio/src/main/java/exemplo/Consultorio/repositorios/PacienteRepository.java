package exemplo.Consultorio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import exemplo.Consultorio.entidades.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

}
