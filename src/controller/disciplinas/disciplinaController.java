package controller.disciplinas;

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

import controller.curso.CursoController;
import controller.inscricoes.inscricoesController;
import controller.professor.ProfessorController;
import fila.Fila;
import lista.Lista;
import model.cursos.Curso;
import model.disciplina.Disciplina;
import model.inscrito.Inscrito;
import model.inscrito.InscritoDTO;
import model.professor.Professor;
import view.disciplinas.CRUDdisciplinas;
import view.disciplinas.Consultar;
import view.inscritos.Inscritos;

public class disciplinaController implements ActionListener {
	
    private JTextField tfcodDisciplina;
    private JTextField tfnomeDisciplina;
    private JTextField tfcodCurso;
    private JTextField tfHoraInicial; 
    private JTextField tfDiaSemana;
    private JTextField tfQntHoras;
    
    CRUDdisciplinas tela;
    
    final String path = "C:\\temp";
    final String fileName = "disciplinas.csv";

	public disciplinaController(JTextField codDisciplina, JTextField nomeDisciplina, JTextField codCurso, JTextField horarioInicial, JTextField DiaSemana, JTextField QntHoras) {
		super();
		tfcodDisciplina = codDisciplina;
		tfnomeDisciplina = nomeDisciplina;
		tfcodCurso = codCurso;
		tfHoraInicial= horarioInicial;
		tfDiaSemana = DiaSemana;
		tfQntHoras = QntHoras;
	}
	
	public void setTela(CRUDdisciplinas tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Cadastrar")) {
			try {
				cadastro();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(cmd.equals("Editar")) {
			editar();
		}
		if(cmd.equals("Deletar")) {
			deletar();
		}
		if(cmd.equals("Buscar disciplina")) {
			consultar();
		}
		if(cmd.equals("Consultar inscritos")) {
			consultarInscritos();
		}
	}

	private void consultarInscritos() {
		String codDisciplina = tfcodDisciplina.getText().trim();
	    File file = new File(path, "inscricoes.csv");
	    
	    if(!file.exists()) {
	    	JOptionPane.showMessageDialog(null, "Nenhum inscrito cadastrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
	    	return;
	    }
	    
		Lista<InscritoDTO> listaDTO = listarInscritos(codDisciplina);
		if(!listaDTO.isEmpty()) {
			Inscritos telaInscritos = new Inscritos(listaDTO);
			telaInscritos.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(null, "Nenhum inscrito cadastrado para esta disciplina", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private Lista<InscritoDTO> listarInscritos(String codDisciplina){
		inscricoesController inscCtrl = new inscricoesController(tfDiaSemana, tfDiaSemana);
		ProfessorController profCtrl = new ProfessorController(null, null, null, null);
		Lista<InscritoDTO> listaDTO = new Lista<>();
		
	    try{
	        Lista<Inscrito> listaInscritos = inscCtrl.readFile("inscricoes.csv");
	        
	        for(int i = 0; i < listaInscritos.size(); i++) {
	            Inscrito inscrito = listaInscritos.get(i);
	            
	            if(inscrito.getCodDisciplina().equals(codDisciplina)) {
	                Professor prof = profCtrl.buscarProfessor(inscrito.getCpf());
	                
	                if(prof != null) {
	                    InscritoDTO dto = new InscritoDTO(
	                        prof.getCpf(), 
	                        prof.getNome(), 
	                        prof.getArea(), 
	                        prof.getQpontos(), 
	                        inscrito.getCodDisciplina(),
	                        inscrito.getCodProcesso()
	                    );
	                    listaDTO.addLast(dto);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    try {
	        ordenarPorPontos(listaDTO);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return listaDTO;
	}

	private void consultar() {
		String codDisciplina = tfcodDisciplina.getText().trim();
		
		try {
			Disciplina encontrado = buscarDisciplina(codDisciplina);
			
			if(encontrado != null) {
				Consultar tela = new Consultar(encontrado);
				tela.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Código de disciplina não cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
			
	}

	public void deletar() {
		File fileInsc = new File(path, "Inscricoes.csv");
		String codDisc = tfcodDisciplina.getText().trim();
		inscricoesController inscCtrl = new inscricoesController(null , null);
		
		try {
			Lista<Disciplina> lista = readFile(fileName);
			int tamanho = lista.size();
			for(int i = 0; i < tamanho; i++) {
				if(lista.get(i).getCodDisc().equals(codDisc)) {
					lista.remove(i);
					break;
				}
			}
			
			if(fileInsc.exists()) {
				Lista<Inscrito> listaInsc = inscCtrl.readFile("inscricoes.csv");
				tamanho = listaInsc.size();
				for (int i = 0; i < tamanho; i++) {
					if(listaInsc.get(i).getCodDisciplina().equals(codDisc)) {
						listaInsc.remove(i);
					}
				}
				
				inscCtrl.salvarLista(listaInsc);
			}
			
			salvarLista(lista);
			JOptionPane.showMessageDialog(null, "Disciplina removida com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}		
	}

	private void editar() {
		String codDisc = tfcodDisciplina.getText().trim();
		String nomeDisc = tfnomeDisciplina.getText().trim();
		String diaSemana = tfDiaSemana.getText().trim();
		String horaInicial = tfHoraInicial.getText().trim();
		String quantidadeHoras = tfQntHoras.getText().trim();
		String codCurso = tfcodCurso.getText().trim();
		
		if(nomeDisc.equals("Nome") || diaSemana.equals("Dia da semana") || quantidadeHoras.equals("Quantidade horas") || codCurso.equals("Código do curso") || codDisc.equals("Código da disciplina") || horaInicial.equals("Horário inicial")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
	    
	    try {
	    	Lista<Disciplina> lista = readFile(fileName);
	    	int tamanho = lista.size();
	    	
	    	for(int i = 0; i < tamanho; i++) {
	    		Disciplina d = lista.get(i);
	    		if(d.getCodDisc().equals(codDisc)) {
	    			d.setNomeDisc(nomeDisc);
	    			d.setDiaSemana(diaSemana);
	    			d.setHoraInicio(horaInicial);
	    			d.setQuantidadeHoras(quantidadeHoras);
	    			d.setCodCurso(codCurso);
	    		}
	    	
	    	}
    		salvarLista(lista);
    		JOptionPane.showMessageDialog(null, "Disciplina atualizada com sucesso!");
	    }catch(Exception e) {
	    	JOptionPane.showMessageDialog(null, e);
	    	e.printStackTrace();
	    }
	}

	private void cadastro() throws Exception {
		CursoController cursoCtrl = new CursoController(null, null, null);
		boolean cursoExiste = false;
		File file = new File(path, fileName);
		File fileCurso = new File(path, "cursos.csv");
		
		String codDisc = tfcodDisciplina.getText().trim();
		String nomeDisc = tfnomeDisciplina.getText().trim();
		String diaSemana = tfDiaSemana.getText().trim();
		String horaInicial = tfHoraInicial.getText().trim();
		String quantidadeHoras = tfQntHoras.getText().trim();
		String codCurso = tfcodCurso.getText().trim();
		
		if(nomeDisc.equals("Nome") || diaSemana.equals("Dia da semana") || quantidadeHoras.equals("Quantidade horas") || codCurso.equals("Código do curso") || codDisc.equals("Código da disciplina") || horaInicial.equals("Horário inicial")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if(fileCurso.exists()) {
			try {
				Lista<Curso> listaCurso = cursoCtrl.readFile("cursos.csv");
				int tamanho = listaCurso.size();
				
				for(int i = 0; i < tamanho; i++) {
					if(listaCurso.get(i).getCodCurso().equals(codCurso)) {
						cursoExiste = true;
						break;
					}
				}
				
				if(!cursoExiste) {
					JOptionPane.showMessageDialog(null, "Código de curso inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			JOptionPane.showMessageDialog(null, "Código de curso inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String conteudo = codDisc + ";" + nomeDisc + ";" + diaSemana + ";" + horaInicial + ";" + quantidadeHoras + ";" + codCurso + "\n";
		
		if(file.exists()) {
			try {
				Lista<Disciplina> lista = readFile(fileName);
				int tamanho = lista.size();
				for(int i = 0; i < tamanho; i++) {
					Disciplina d = lista.get(i);
					
					if(d.getCodDisc().equals(codDisc)) {
						JOptionPane.showMessageDialog(null, "Disciplina já cadastrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		
		try {
			createFile(fileName, conteudo);
			JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			tela.limparCampos();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}
	
	public void salvarLista(Lista<Disciplina> lista) throws Exception{
		File file = new File(path, fileName);
		FileWriter fw = new FileWriter(file, false);
		PrintWriter pw = new PrintWriter(fw);
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			Disciplina d = lista.get(i);
			pw.println(d.getCodDisc() + ";" + d.getNomeDisc() + ";" + d.getDiaSemana() + ";" + d.getHoraInicio() + ";" + d.getQuantidadeHoras() + ";" + d.getCodCurso());
		}
		
		pw.flush();
		pw.close();
		fw.close();
	}
	
	public Disciplina buscarDisciplina(String codDisc) {
		Fila<Disciplina> fila = new Fila<>();
		try {
			Lista<Disciplina> lista = readFile(fileName);
			Fila<Disciplina> fila_ = popularFila(fila, lista);
			
			int tamanho = fila_.size();
	        for (int i = 0; i < tamanho; i++) {
	            Disciplina d = fila_.remove();
	            if (d.getCodDisc().equals(codDisc)) {
	                return d;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	public void ordenarPorPontos(Lista<InscritoDTO> listaDTO) throws Exception {
        if (listaDTO.isEmpty() || listaDTO.size() <= 1) {
            return;
        }
        quickSort(listaDTO, 0, listaDTO.size() - 1);
    }
    
    private void quickSort(Lista<InscritoDTO> lista, int inicio, int fim) throws Exception {
        if (inicio < fim) {
            int indicePivo = particionar(lista, inicio, fim);
            quickSort(lista, inicio, indicePivo - 1);
            quickSort(lista, indicePivo + 1, fim);
        }
    }
    
    private int particionar(Lista<InscritoDTO> lista, int inicio, int fim) throws Exception {
        InscritoDTO pivo = lista.get(fim);
        int i = inicio - 1;
        
        for (int j = inicio; j < fim; j++) {
            InscritoDTO elemento = lista.get(j);
            
            if (elemento.getPontos() >= pivo.getPontos()) {
                i++;
                trocar(lista, i, j);
            }
        }
        
        trocar(lista, i + 1, fim);
        return i + 1;
    }
    
    private void trocar(Lista<InscritoDTO> lista, int pos1, int pos2) throws Exception {
        if (pos1 == pos2) {
            return;
        }
        
        InscritoDTO temp1 = lista.get(pos1);
        InscritoDTO temp2 = lista.get(pos2);
        
        lista.remove(pos1);
        lista.add(temp2, pos1);
        
        lista.remove(pos2);
        lista.add(temp1, pos2);
    }

	private Fila<Disciplina> popularFila(Fila<Disciplina> fila, Lista<Disciplina> lista ){
		int tamanho = lista.size();
		
		for(int i = 0; i < tamanho; i++) {
			try {
				Disciplina d = lista.get(i);
				fila.Insert(d);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fila;
	}
	
	// Manipulação de arquivos
	
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
	
	public Lista<Disciplina> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Disciplina> listaDisc= new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String codDisc = campos[0];
	            String nomeDisc = campos[1];
	            String diaSemana = campos[2];
	            String horaInicio = campos[3];
	            String quantidadeHoras = campos[4];
	            String codCurso = campos[5];

	            Disciplina disciplina = new Disciplina(codDisc, nomeDisc, diaSemana, horaInicio, quantidadeHoras, codCurso);

	            if(listaDisc.isEmpty()) {
	                listaDisc.addFirst(disciplina);
	            } else {
	                try {
						listaDisc.addLast(disciplina);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaDisc;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}
}
