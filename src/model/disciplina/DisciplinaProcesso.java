package model.disciplina;

public class DisciplinaProcesso {

	public String codigoDisciplina;
	public String nomeDisciplina;
	public String diaSemana;
	public String horarioInicial;
	public String quantidadeHoras;

	public String codigoCurso;
	public String nomeCurso;
	public String areaConhecimento;

	public DisciplinaProcesso(String codigoDisciplina, String nomeDisciplina, String diaSemana, String horarioInicial,
			String quantidadeHoras, String codigoCurso, String nomeCurso, String areaConhecimento) {
		this.codigoDisciplina = codigoDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.diaSemana = diaSemana;
		this.horarioInicial = horarioInicial;
		this.quantidadeHoras = quantidadeHoras;
		this.codigoCurso = codigoCurso;
		this.nomeCurso = nomeCurso;
		this.areaConhecimento = areaConhecimento;
	}

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(String horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public String getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(String quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	@Override
	public String toString() {
		return "DisciplinaProcesso [codigoDisciplina=" + codigoDisciplina + ", nomeDisciplina=" + nomeDisciplina
				+ ", diaSemana=" + diaSemana + ", horarioInicial=" + horarioInicial + ", quantidadeHoras="
				+ quantidadeHoras + ", codigoCurso=" + codigoCurso + ", nomeCurso=" + nomeCurso + ", areaConhecimento="
				+ areaConhecimento + "]";
	}

}
