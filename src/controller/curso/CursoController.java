package controller.curso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.arquivos.ArquivosController;

public class CursoController implements ActionListener{
	
	private JTextField codCurso;
	private JTextField NomeCurso;
	private JTextField Area;
	
	public CursoController(JTextField codCurso, JTextField NomeCurso, JTextField Area) {
		
		this.Area = Area;
		this.codCurso = codCurso;
		this.NomeCurso = NomeCurso;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd  = e.getActionCommand();
		if(cmd.equals("Cadastrar")) {
			cadastro();
		}
		if(cmd.equals("Deletar")) {
			deletar();
		}
		if(cmd.equals("Editar")) {
			editar();
		}
		
	}

	private void editar() {
		// TODO Auto-generated method stub
		
	}

	private void deletar() {
		// TODO Auto-generated method stub
		
	}

	private void cadastro() {
		String Curso = codCurso.getText().trim();
		String Nome = NomeCurso.getText().trim();
		String AreaConhecimento = Area.getText().trim();
		
		String arqName = "cursos.csv";
		String conteudo = Curso + ";" + Nome + ";" + AreaConhecimento;
		
		ArquivosController arq = new ArquivosController();
		
		try {
			arq.createFile(arqName, conteudo);
			JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
		}catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Diretório inválido!");
		}
	}

}
