package controller.curso;

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

import controller.disciplinas.disciplinaController;
import controller.inscricoes.inscricoesController;
import fila.Fila;
import lista.Lista;
import model.cursos.Curso;
import model.disciplina.Disciplina;
import model.inscrito.Inscrito;
import view.cursos.CRUDcursos;
import view.cursos.Consulta;
import view.disciplinas.DisciplinasCurso;


public class CursoController implements ActionListener{
	
	private JTextField tfCodCurso;
	private JTextField tfNomeCurso;
	private JTextField tfArea;
	
	CRUDcursos tela;
	
	final String path = "C:\\temp";
	final String fileName = "cursos.csv";
	
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
			File file = new File(path, fileName);
			
			if(file.exists()) {
				consultar();
			}else {
				JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado!");
			}
		}
		if(cmd.equals("Consultar disciplinas")) {
			consultarDisciplinas();
		}
	}

	private void consultarDisciplinas() {
		disciplinaController discCtrl = new disciplinaController(null, null, null, null, null, null);
		String codCurso = tfCodCurso.getText().trim();
		File file = new File(path, "disciplinas.csv");
		
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada para esse curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			Lista<Disciplina> list = discCtrl.readFile("disciplinas.csv");
			Lista<Disciplina> listDisc = new Lista<>();
			int tamanho = list.size();
			
			for(int i = 0; i < tamanho; i++) {
				if(list.get(i).getCodCurso().equals(codCurso)) {
					listDisc.addLast(list.get(i));
				}
			}
			
			Curso curso = buscarCurso(codCurso);
			
			if(!listDisc.isEmpty()) {
				DisciplinasCurso tela = new DisciplinasCurso(listDisc, codCurso, curso);
				tela.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada neste curso!", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void consultar() {
		String codCurso = tfCodCurso.getText().trim();
		
		try {
			Curso encontrado = buscarCurso(codCurso);
			
			if(encontrado != null) {
				Consulta tela = new Consulta(encontrado);
				tela.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Código de curso não cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}

	private void editar() {		
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
		disciplinaController discCtrl = new disciplinaController(null, null, null, null, null, null);
		inscricoesController inscCtrl = new inscricoesController(null, null);
		String codCurso = tfCodCurso.getText().trim();
		
		try {
			Lista<Curso> lista = readFile(fileName);
			Lista<Disciplina> listaDisc = discCtrl.readFile("disciplinas.csv");
			Lista<Inscrito> listaInsc = inscCtrl.readFile("Inscricoes.csv");
			int tamanho = lista.size();
			for (int i = lista.size() - 1; i >= 0; i--) {
			    if (lista.get(i).getCodCurso().equals(codCurso)) {
			        lista.remove(i);
			    }
			}
			
			tamanho = listaDisc.size();
			for(int i = tamanho - 1; i >= 0 ; i--) {
				if(listaDisc.get(i).getCodCurso().equals(codCurso)) {
			        Disciplina d = listaDisc.get(i);
					listaDisc.remove(i);
			        
					if(listaInsc.get(i).getCodDisciplina().equals(d.getCodDisc())) {
						listaInsc.remove(i);
					}
			        i--; 
				}
			}
			
			inscCtrl.salvarLista(listaInsc);
			discCtrl.salvarLista(listaDisc);
			salvarLista(fileName, lista);
			JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}

	private void cadastro() {
		File file = new File(path, fileName);
		
		String codCurso = tfCodCurso.getText().trim();
		String Nome = tfNomeCurso.getText().trim();
		String AreaConhecimento = tfArea.getText().trim();
		
		if(codCurso.equals("Código do curso") || Nome.equals("Nome do curso") || AreaConhecimento.equals("Área de conhecimento")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		
		String conteudo = codCurso + ";" + Nome + ";" + AreaConhecimento + "\n";
		
		if(file.exists()) {
			try {
				Lista<Curso> lista = readFile(fileName);
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
		}
		
		try {
			createFile(fileName, conteudo);
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
	
	private Fila<Curso> popularFila(Fila<Curso> fila, Lista<Curso> lista ){
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			try {
				Curso c = lista.get(i);
				fila.Insert(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fila;
	}
	
	public Curso buscarCurso(String codCurso) {
		Fila<Curso> fila = new Fila<>();
		try {
			Lista<Curso> lista = readFile(fileName);
			Fila<Curso> fila_ = popularFila(fila, lista);
			int tamanho = fila_.size();
			for(int i = 0; i < tamanho; i++) {
				Curso c = fila_.remove();
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
