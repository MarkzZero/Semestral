package controller.inscricoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lista.Lista;
import model.inscrito.Inscrito;
import view.inscritos.CRUDinscritos;
import view.inscritos.Consulta;

public class inscricoesController implements ActionListener {

	private JTextField tfCPF;
	private JTextField tfCodDisc;

	final String path = "C:\\temp";

	CRUDinscritos tela;
	
	public inscricoesController(JTextField tfCPF, JTextField tfCodDisc) {
		this.tfCodDisc = tfCodDisc;
		this.tfCPF = tfCPF;
	}
	
	public void setTela(CRUDinscritos tela) {
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Cadastrar")) {
			cadastro();
		}
		if (cmd.equals("Deletar")) {
			deletar();
		}
		if (cmd.equals("Editar")) {
			editar();
		}
		if (cmd.equals("Consultar")) {
			consultar();
		}
	}

	private void consultar() {
		String cpf = tfCPF.getText().trim();
		String codDisciplina = tfCodDisc.getText().trim();
		
		Inscrito encontrado = buscarInscrito(cpf, codDisciplina);
		
		try {
			if(encontrado != null) {
				Consulta tela = new Consulta(encontrado);
				tela.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Inscrito não cadastrado");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e, null, JOptionPane.WARNING_MESSAGE);
		}
	}

	private void editar() {
		String fileName = "inscricoes.csv";
		
		String cpf = tfCPF.getText().trim();
		String codDisciplina = tfCodDisc.getText().trim();
		
	    if (cpf.isEmpty() || codDisciplina.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
	        return;
	    }
	    
	    try {
	    	Lista<Inscrito> lista = readFile(fileName);
	    	int tamanho = lista.size();
	    	
	    	for(int i = 0; i < tamanho; i++) {
	    		Inscrito inscrito = lista.get(i);
	    		if(inscrito.getCpf().equals(cpf) && inscrito.getCodDisciplina().equals(codDisciplina)) {
	    			inscrito.setCpf(cpf);
	    			inscrito.setCodDisciplina(codDisciplina);
	    		}
	    	
	    	}
    		salvarLista(lista);
    		JOptionPane.showMessageDialog(null, "Inscrito atualizado com sucesso!");
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	e.printStackTrace();
	    }
	}

	private void deletar() {
		String fileName = "inscricoes.csv";
		String cpf = tfCPF.getText().trim();
		String codDisciplina = tfCodDisc.getText().trim();
		
		try {
			Lista<Inscrito> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				if(lista.get(i).getCpf().equals(cpf) && lista.get(i).getCodDisciplina().equals(codDisciplina)) {
					lista.remove(i);
					break;
				}
			}
			
			salvarLista(lista);
			JOptionPane.showMessageDialog(null, "Curso removido com sucesso!");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

	}

	private void cadastro() {
		File file = new File(path, "inscricoes.csv");
		
		String cpf = tfCPF.getText().trim();
		String codDisciplina = tfCodDisc.getText().trim();
		String codProcesso = gerarCodigoProcesso();
		
		if(cpf.equals("___.___.___-__") || codDisciplina.equals("Código da disciplina")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String arqName = "inscricoes.csv";
		String conteudo = cpf + ";" + codDisciplina + ";" + codProcesso + "\n";
		
		if(file.exists()) {
			try {
				Lista<Inscrito> lista = readFile(arqName);
				int tamanho = lista.size();
				for(int i = 0; i < tamanho; i++) {
					Inscrito inscrito = lista.get(i);
					
					if(inscrito.getCpf().equals(cpf) && inscrito.getCodDisciplina().equals(codDisciplina)) {
						JOptionPane.showMessageDialog(null, "Inscrito já está cadastrado nesta disciplina!", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		try {
			createFile(arqName, conteudo);
			JOptionPane.showMessageDialog(null, "Inscrito cadastrado com sucesso!");
			tela.limparCampos();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void salvarLista(Lista<Inscrito> lista) throws Exception{
		File file = new File(path, "inscricoes.csv");
		FileWriter fw = new FileWriter(file, false);
		PrintWriter pw = new PrintWriter(fw);
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			Inscrito inscrito = lista.get(i);
			pw.println(inscrito.getCpf() + ";" + inscrito.getCodDisciplina() + ";" + inscrito.getCodProcesso());
		}
		
		pw.flush();
		pw.close();
		fw.close();
	}
	
	private Inscrito buscarInscrito(String cpf, String codDisplina) {
		String fileName = "inscricoes.csv";
				
		try {
			Lista<Inscrito> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				Inscrito inscrito = lista.get(i);
				if(inscrito.getCpf().equals(cpf) && inscrito.getCodDisciplina().equals(codDisplina)) {
					return inscrito;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String gerarCodigoProcesso() {
		String ano = String.valueOf(java.time.LocalDate.now().getYear());
		String prefixo = "P" + ano + "-";
		int maior = 0;

		File file = new File(path, "inscricoes.csv");

		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String linha;
				while ((linha = br.readLine()) != null) {
					String[] dados = linha.split(";");

					String codProcesso = dados[2];

					if (codProcesso.startsWith(prefixo)) {
						String numStr = codProcesso.substring(prefixo.length());
						int num = Integer.parseInt(numStr);
						if (num > maior) {
							maior = num;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		int novoNumero = maior + 1;

		return prefixo + String.format("%04d", novoNumero);
	}

	private void createFile(String nome, String conteudo) throws IOException {
		File dir = new File(path);
		File file = new File(path, nome);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (file.exists()) {
				existe = true;
			}
			FileWriter fileWriter = new FileWriter(file, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório inválido");
		}

	}

	public Lista<Inscrito> readFile(String nome) throws IOException {
		File file = new File(path, nome);
		Lista<Inscrito> listaInscrito = new Lista<>();

		if (file.exists()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] campos = linha.split(";");
				String cpf = campos[0];
				String codDisciplina = campos[1];
				String codProcesso = campos[2];

				Inscrito inscrito = new Inscrito(cpf, codDisciplina, codProcesso);

				if (listaInscrito.isEmpty()) {
					listaInscrito.addFirst(inscrito);
				} else {
					try {
						listaInscrito.addLast(inscrito);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

			return listaInscrito;

		} else {
			throw new IOException("Diretório inválido!!");
		}
	}

}
