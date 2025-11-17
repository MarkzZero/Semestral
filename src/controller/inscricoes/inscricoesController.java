package controller.inscricoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.arquivos.ArquivosController;

public class inscricoesController implements ActionListener{

	private JTextField cpf;
	private JTextField codDisc;
	
	
	public inscricoesController(JTextField cpf, JTextField codDisc) {
		this.codDisc = codDisc;
		this.cpf = cpf;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
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
		String cpfProfessor = cpf.getText().trim();
		String codDisciplina = codDisc.getText().trim();
		
		String arqName = "inscricoes.csv";
		String codProcesso = gerarCodigoNumerico();
		String conteudo = cpfProfessor + ";" + codDisciplina + ";" + codProcesso + "\n";
		
		ArquivosController arq = new ArquivosController();
		try {
			arq.createFile(arqName, conteudo);
			JOptionPane.showMessageDialog(null, "Inscrito cadastrado com sucesso!");
		}catch(IOException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public static String gerarCodigoNumerico() {
	    long time = System.currentTimeMillis(); 
	    int hash = Math.abs(UUID.randomUUID().toString().hashCode()) % 100;

	    return time + String.format("%01d", hash);
	}

}
