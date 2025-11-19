package controller.curso;

import java.awt.Desktop;
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

import lista.Lista;
import model.cursos.Curso;
import model.professor.Professor;
import view.cursos.CRUDcursos;
import view.cursos.Consulta;


public class CursoController implements ActionListener{
	
	private JTextField tfCodCurso;
	private JTextField tfNomeCurso;
	private JTextField tfArea;
	
	CRUDcursos tela;
	
	final String path = "C:\\temp";
	
	public CursoController(JTextField tfCodCurso, JTextField tfNomeCurso, JTextField tfArea) {
		
		this.tfArea = tfArea;
		this.tfCodCurso = tfCodCurso;
		this.tfNomeCurso = tfNomeCurso;
		
	}
	
	public void setTela(CRUDcursos tela) {
		this.tela = tela;
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
		if(cmd.equals("Consultar")) {
			consultar();
		}
		
	}

	private void consultar() {
		String codCurso = tfCodCurso.getText().trim();
		
		Curso encontrado = buscarCurso(codCurso);
		if(encontrado == null) {
			JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			if(encontrado != null) {
				Consulta tela = new Consulta(encontrado);
				tela.setVisible(true);
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e, "Aviso", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	private void editar() {
		String fileName = "cursos.csv";
		
		String codCurso = tfCodCurso.getText().trim();
		String nome = tfNomeCurso.getText().trim();
		String area = tfArea.getText().trim();
		
	    if (nome.isEmpty() || area.isEmpty() || codCurso.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
	        return;
	    }
	    
	    try {
	    	Lista<Curso> lista = readFile(fileName);
	    	int tamanho = lista.size();
	    	
	    	for(int i = 0; i < tamanho; i++) {
	    		Curso c = lista.get(i);
	    		if(c.getCodCurso().equals(codCurso)) {
	    			c.setNomeCurso(nome);
	    			c.setArea(area);
	    			c.setCodCurso(codCurso);
	    		}
	    	
	    	}
    		salvarLista(fileName, lista);
    		JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!");
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	e.printStackTrace();
	    }
	}

	private void deletar() {
		String fileName = "cursos.csv";
		String codCurso = tfCodCurso.getText().trim();
		
		try {
			Lista<Curso> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				if(lista.get(i).getCodCurso().equals(codCurso)) {
					lista.remove(i);
					break;
				}
			}
			
			salvarLista(fileName, lista);
			JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}

	private void cadastro() {
		String codCurso = tfCodCurso.getText().trim();
		String Nome = tfNomeCurso.getText().trim();
		String AreaConhecimento = tfArea.getText().trim();
		
		if(codCurso.equals("Código do curso") || Nome.equals("Nome do curso") || AreaConhecimento.equals("Área de conhecimento")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		
		String arqName = "cursos.csv";
		String conteudo = codCurso + ";" + Nome + ";" + AreaConhecimento + "\n";
		
		try {
			Lista<Curso> lista = readFile(arqName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				Curso c = lista.get(i);
				
				if(c.getCodCurso().equals(codCurso)) {
					JOptionPane.showMessageDialog(null, "Curso já cadastrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			createFile(arqName, conteudo);
			JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			tela.limparCampos();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}
	
	private void salvarLista(String fileName, Lista<Curso> lista) throws Exception{
		File file = new File(path, fileName);
		FileWriter fw = new FileWriter(file, false);
		PrintWriter pw = new PrintWriter(fw);
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			Curso c = lista.get(i);
			pw.println(c.getCodCurso() + ";" +c.getNomeCurso() + ";" + c.getArea());
		}
		
		pw.flush();
		pw.close();
		fw.close();
	}
	
	private Curso buscarCurso(String codCurso) {
		String fileName = "cursos.csv";
		File file = new File(path, fileName);
				
		try {
			Lista<Curso> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				Curso c = lista.get(i);
				if(c.getCodCurso().equals(codCurso)) {
					return c;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Manipulação de arquivos
	
	public void createFile(String nome, String conteudo) throws IOException {
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

	// Resolver o erro de quando não tem nenhum professor cadastrado
	
	public Lista<Curso> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Curso> listaCurso = new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String codCurso = campos[0];
	            String nomeCurso = campos[1];
	            String area = campos[2];

	            Curso Curso = new Curso(codCurso, nomeCurso, area);

	            if(listaCurso.isEmpty()) {
	                listaCurso.addFirst(Curso);
	            } else {
	                try {
						listaCurso.addLast(Curso);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaCurso;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}

}
