package model.inscrito;

public class InscritoDTO {
	private String cpf;
	private String nome;
	private String area;
	private int pontos;
	private String codProcesso;
	private String codDisciplina;

	public InscritoDTO(String cpf, String nome, String area, int pontos, String codDisciplina, String codProcesso) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.area = area;
		this.pontos = pontos;
		this.codProcesso = codProcesso;
		this.codDisciplina = codDisciplina;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public String getCodProcesso() {
		return codProcesso;
	}

	public void setCodProcesso(String codProcesso) {
		this.codProcesso = codProcesso;
	}

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	@Override
	public String toString() {
		return "InscritoDTO [cpf=" + cpf + ", nome=" + nome + ", area=" + area + ", pontos=" + pontos + ", codProcesso="
				+ codProcesso + ", codDisciplina=" + codDisciplina + "]";
	}
	
	

}
