package exemplo.Consultorio.erros;

@SuppressWarnings("serial")
public class SemMedicosDisponiveisException extends Exception{
	
	public SemMedicosDisponiveisException(String mensagem) {
		super(mensagem);
	}
	
}
