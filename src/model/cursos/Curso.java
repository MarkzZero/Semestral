package model.cursos;

public class Curso {
	
	private String codCurso;
	private String nomeCurso;
	private String area;
	
	public Curso(String codCurso, String nomeCurso, String area) {
		this.area = area;
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
	}
	
	
	
	public String getCodCurso() {
		return codCurso;
	}



	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}



	public String getNomeCurso() {
		return nomeCurso;
	}



	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	@Override
	public String toString() {
		return "Curso [código: " + codCurso + " nome: " + nomeCurso + " área de conhecimento: " + area + "]";
	}

}
