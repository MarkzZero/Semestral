package model.disciplina;

public class Disciplina {
	
	private String codDisc;
	private String nomeDisc;
	private String diaSemana;
	private String horaInicio;
	private String quantidadeHoras;
	private String codCurso;
	
	public Disciplina(String codDisc, String nomeDisc, String diaSemana, String horaInicio, String quantidadeHoras, String codCurso) {
		this.codDisc = codDisc;
		this.nomeDisc = nomeDisc;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.quantidadeHoras = quantidadeHoras;
		this.codCurso = codCurso;
	}

	public String getCodDisc() {
		return codDisc;
	}

	public void setCodDisc(String codDisc) {
		this.codDisc = codDisc;
	}

	public String getNomeDisc() {
		return nomeDisc;
	}

	public void setNomeDisc(String nomeDisc) {
		this.nomeDisc = nomeDisc;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(String quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	
	@Override
	public String toString() {
		return "Disciplina [ código " + codDisc + " nome: " + nomeDisc + " dia da semana: " + diaSemana + " horario inicial: " + horaInicio + " quantidade de horas diárias: " 
		+ quantidadeHoras + " código do curso: " + codCurso;
	}

}
