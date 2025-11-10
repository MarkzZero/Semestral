package controller.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.arquivos.ArquivosController;

public class ProfessorController implements ActionListener{
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfArea;
	private JTextField tfQuantidadePontos;

	public ProfessorController(JTextField nome, JTextField cpf, JTextField area, JTextField qpontos) {
		super();
		tfNome = nome;
		tfCPF = cpf;
		tfArea = area;
		tfQuantidadePontos = qpontos;
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
	    String nome = tfNome.getText().trim();
	    String cpf = tfCPF.getText().trim();
	    String area = tfArea.getText().trim();
	    String pontosStr = tfQuantidadePontos.getText().trim();

	    if (nome.isEmpty() || cpf.isEmpty() || area.isEmpty() || pontosStr.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Preencha todos os campos antes de cadastrar.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    int Qpontos;
	    try {
	        Qpontos = Integer.parseInt(pontosStr);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Quantidade de pontos deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    ArquivosController arq = new ArquivosController();
	    String dir = "C:\\temp";
	    String ArqNome = "professor.csv";
	    String conteudo = cpf + ";" + nome + ";" + area + ";" + Qpontos + "\n";
	    
	    try {
			arq.createFile(dir, ArqNome, conteudo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    

	    JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
	}


}
