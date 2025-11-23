package controller.disciplinas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.curso.CursoController;
import controller.inscricoes.inscricoesController;
import lista.Lista;
import model.cursos.Curso;
import model.disciplina.Disciplina;
import model.disciplina.DisciplinaProcesso;
import model.inscrito.Inscrito;
import tabela.TabelaHash;
import view.disciplinas.ProcessosAbertos;

public class ProcessosController implements ActionListener{
	
	final private String path = "C:\\temp";
	
	public ProcessosController() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Consultar processos abertos")) {
			ConsultarProcessos();
		}
	}
	
	private void ConsultarProcessos() {
		disciplinaController discCtrl = new disciplinaController(null, null, null, null, null, null);
		inscricoesController inscCtrl = new inscricoesController(null, null);
		CursoController cursoCtrl = new CursoController(null, null, null);
		try {
	        TabelaHash<DisciplinaProcesso> tabela = new TabelaHash<>();	        
	        Lista<Inscrito> inscricoes = inscCtrl.readFile("inscricoes.csv");
	        
	        for (int i = 0; i < inscricoes.size(); i++) {
	            Inscrito inscricao = inscricoes.get(i);
	            Disciplina disc = discCtrl.buscarDisciplina(inscricao.getCodDisciplina());
	            
	            if (disc != null) {
	                Curso curso = cursoCtrl.buscarCurso(disc.getCodCurso());
	                
	                if (curso != null) {
	                    DisciplinaProcesso dp = new DisciplinaProcesso(
	                        disc.getCodDisc(), 
	                        disc.getNomeDisc(), 
	                        disc.getDiaSemana(),
	                        disc.getHoraInicio(), 
	                        disc.getQuantidadeHoras(),
	                        curso.getCodCurso(), 
	                        curso.getNomeCurso(), 
	                        curso.getArea()
	                    );
	                    
	                    tabela.inserir(disc.getCodDisc(), dp);
	                }
	            }
	        }
	        
	        Lista<DisciplinaProcesso> disciplinas = tabela.listarTodos();
	        
	        if (disciplinas.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Não há processos abertos");
	            return;
	        }
	        
	        ProcessosAbertos tela = new ProcessosAbertos();
	        tela.setVisible(true);
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
	        e.printStackTrace();
	    }
	}



}
