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
    private JFormattedTextField txtHorario; 
    private JTextField DiaSemana;
    private JTextField QntHoras;
    

	public disciplinaController(JTextField codDisciplina, JTextField nomeDisciplina, JTextField codCurso, JFormattedTextField txtHorario, JTextField DiaSemana, JTextField QntHoras) {
		super();
		this.codDisciplina = codDisciplina;
		this.nomeDisciplina = nomeDisciplina;
		this.codCurso = codCurso;
		this.txtHorario = txtHorario;
		this.DiaSemana = DiaSemana;
		this.QntHoras = QntHoras;
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
		String horario = txtHorario.getText().trim();
		String dia = DiaSemana.getText().trim();
		String horas = QntHoras.getText().trim();
		
	    ArquivosController arq = new ArquivosController();
	    
	    String nomeArq = "disciplinas.csv";
	    String conteudo = codDisc + ";" + nome + ";" + dia + ";" + horario + ";" + horas + ";" + codCur;
	    
	    try {
	    	arq.createFile(nomeArq, conteudo);
	    	JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	JOptionPane.showMessageDialog(null, "Diretório inválido!");
	    }
	    
	    
	    
		
	}
}
