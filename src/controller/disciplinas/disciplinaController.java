package controller.disciplinas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.arquivos.ArquivosController;

public class disciplinaController implements ActionListener {
	
    private JTextField codDisciplina;
    private JTextField nomeDisciplina;
    private JTextField codCurso;
    private JFormattedTextField txtData;
    private JFormattedTextField txtHorario; 

	public disciplinaController(JTextField codDisciplina, JTextField nomeDisciplina, JTextField codCurso,
			JFormattedTextField txtData, JFormattedTextField txtHorario) {
		super();
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.codCurso = codCurso;
		this.txtData = txtData;
		this.txtHorario = txtHorario;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Cadastrar")) {
			cadastro();
		}
		if(cmd.equals("Editar")) {
			editar();
		}
		if(cmd.equals("Deletar")) {
			deletar();
		}
	}

	private void deletar() {
		// TODO Auto-generated method stub
		
	}

	private void editar() {
		// TODO Auto-generated method stub
		
	}

	private void cadastro() {
		String codDisc = codDisciplina.getText().trim();
		String nome = nomeDisciplina.getText().trim();
		String codCur = codCurso.getText().trim();
		String data = txtData.getText().trim();
		String horario = txtHorario.getText().trim();
	
	    ArquivosController arq = new ArquivosController();
	    
	    String dir = "C:\\temp";
	    String fileName = "disciplinas.csv";
	    String conteudo = codDisc + ";" + nome + ";" + data;
	    
	    
	    
		
	}
}
