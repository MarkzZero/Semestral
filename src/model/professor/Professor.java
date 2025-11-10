package model.professor;

public class Professor {

	private String nome;
	private String cpf;
	private String area;
	private int Qpontos;
	
	public Professor(String area, String nome, String cpf, int Qpontos) {
		this.nome = nome;
		this.cpf = cpf;
		this.area = area;
		this.Qpontos = Qpontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getQpontos() {
		return Qpontos;
	}

	public void setQpontos(int qpontos) {
		Qpontos = qpontos;
	}

	@Override
	public String toString() {
		return "Professor [nome=" + nome + ", cpf=" + cpf + ", area=" + area + ", Qpontos=" + Qpontos + "]";
	}
	
	
}
