package model.inscrito;

public class Inscrito {
	private String cpf;
	private String codDisciplina;
	private String codProcesso;
	
	public Inscrito(String cpf, String codDisciplina, String codProcesso) {
		this.cpf = cpf;
		this.codDisciplina = codDisciplina;
		this.codProcesso = codProcesso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getCodProcesso() {
		return codProcesso;
	}

	public void setCodProcesso(String codProcesso) {
		this.codProcesso = codProcesso;
	}

	@Override
	public String toString() {
		return "Inscrito [cpf=" + cpf + ", codDisciplina=" + codDisciplina + ", codProcesso=" + codProcesso + "]";
	}
	
	

}
