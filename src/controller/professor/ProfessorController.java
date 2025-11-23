package controller.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fila.Fila;
import lista.Lista;
import model.professor.Professor;
import view.professor.CRUDProfessor;
import view.professor.Consulta;
public class ProfessorController implements ActionListener{
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfArea;
	private JTextField tfPontos;
	
    CRUDProfessor tela;
    
    final String path = "C:\\temp";
    
    
	public ProfessorController(JTextField nome, JTextField cpf, JTextField area, JTextField qpontos) {
		super();
		tfNome = nome;
		tfCPF = cpf;
		tfArea = area;
		tfPontos = qpontos;
	}

	public void setTela(CRUDProfessor tela) {
		this.tela = tela;
	}
	
	public void actionPerformed(ActionEvent e){
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
		if(cmd.equals("Buscar professor")) {
			File file = new File(path, "professor.csv");
			
			if(file.exists()) {
				consultar();
			}else {
				JOptionPane.showMessageDialog(null, "Nenhum professor cadastrado!");
			}
			
		}
	}

	private void consultar(){
		String cpf = tfCPF.getText().trim();
		
		try {
			Professor encontrado = buscarProfessor(cpf);
			
			if(encontrado != null) {
				Consulta tela = new Consulta(encontrado);
				tela.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "CPF não cadastrado");
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}



	private void deletar() {
		String fileName = "professor.csv";
		String cpf = tfCPF.getText().trim();
		
		try {
			Lista<Professor> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				if(lista.get(i).getCpf().equals(cpf)) {
					lista.remove(i);
					break;
				}
			}
			
			salvarLista(fileName, lista);
			JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
	}

	private void editar() {
		String fileName = "professor.csv";
		
		String cpf = tfCPF.getText().trim();
		String nome = tfNome.getText().trim();
		String area = tfArea.getText().trim();
		String pontosStr = tfPontos.getText().trim();
		
	    if (nome.isEmpty() || cpf.isEmpty() || area.isEmpty() || pontosStr.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
	        return;
	    }
	    
	    int pontos = Integer.parseInt(pontosStr);
	    
	    try {
	    	Lista<Professor> lista = readFile(fileName);
	    	int tamanho = lista.size();
	    	
	    	for(int i = 0; i < tamanho; i++) {
	    		Professor p = lista.get(i);
	    		if(p.getCpf().equals(cpf)) {
	    			p.setNome(nome);
	    			p.setArea(area);
	    			p.setQpontos(pontos);
	    		}
	    	
	    	}
    		salvarLista(fileName, lista);
    		JOptionPane.showMessageDialog(null, "Professor atualizado com sucesso!");
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	e.printStackTrace();
	    }
	}

	private void cadastro() {
	    String nome = tfNome.getText().trim();
	    String cpf = tfCPF.getText().trim();
	    String area = tfArea.getText().trim();
	    String pontosStr = tfPontos.getText().trim();

	    if (nome.equals("Nome") || cpf.equals("___.___.___-__") || area.equals("Área de conhecimento") || pontosStr.equals("Quantidade de Pontos")){
	    	JOptionPane.showMessageDialog(null, "Preencha todos os campos!" , "Aviso", JOptionPane.WARNING_MESSAGE);
	    	return;
	    }

	    int Qpontos;
	    try {
	        Qpontos = Integer.parseInt(pontosStr);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Quantidade de pontos deve ser um número válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    String ArqNome = "professor.csv";
	    String conteudo = cpf + ";" + nome + ";" + area + ";" + Qpontos + "\n";
	    File file = new File(path, ArqNome);
	    
	    if(file.exists()) {
	    	try {
		    	Lista<Professor> lista = readFile(ArqNome);
		    	int tamanho = lista.size();
		    	
		    	for(int i = 0; i < tamanho; i++) {
		    		Professor p = lista.get(i);
		    		
		    		if(p.getCpf().equals(cpf)) {
		    			JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
		    			return;
		    		}
		    	}
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }	
	    }
	    
	    
	    try {
	        createFile(ArqNome, conteudo);
	        JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
	        tela.limparCampos();
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, e);	
	    }
	}
	
	private void salvarLista(String fileName, Lista<Professor> lista) throws Exception{
		File file = new File(path, fileName);
		FileWriter fw = new FileWriter(file, false);
		PrintWriter pw = new PrintWriter(fw);
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			Professor p = lista.get(i);
			pw.println(p.getCpf() + ";" +p.getNome() + ";" + p.getArea() + ";" + p.getQpontos());
		}
		
		pw.flush();
		pw.close();
		fw.close();
	}
	
	public Professor buscarProfessor(String cpf) {		
		Fila<Professor> fila = new Fila<>();
		try {
			Lista<Professor> lista = readFile("professor.csv");
			Fila<Professor> fila_ = popularFila(fila, lista);
			int tamanho = fila_.size();
			for(int i = 0; i < tamanho; i++) {
				Professor p = fila_.remove();
				if(p.getCpf().equals(cpf)) {
					return p;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Fila<Professor> popularFila(Fila<Professor> fila, Lista<Professor> lista ){
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			try {
				Professor p = lista.get(i);
				fila.Insert(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fila;
	}
	
	private void createFile(String nome, String conteudo) throws IOException {
		File dir = new File(path);
		File file = new File(path, nome);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(file.exists()) {
				existe = true;
			}
			FileWriter fileWriter = new FileWriter(file, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush(); 
			print.close();
			fileWriter.close();
		}else {
			throw new IOException("Diretório inválido");
		}
		
	}

	private Lista<Professor> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Professor> listaProfessor = new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String cpf = campos[0];
	            String nomeProf = campos[1];
	            String area = campos[2];
	            int pontos = Integer.parseInt(campos[3]);

	            Professor professor = new Professor(cpf, nomeProf, area, pontos);

	            if(listaProfessor.isEmpty()) {
	                listaProfessor.addFirst(professor);
	            } else {
	                try {
						listaProfessor.addLast(professor);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaProfessor;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}


}
