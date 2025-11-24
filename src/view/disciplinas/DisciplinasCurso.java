package view.disciplinas;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lista.Lista;
import model.cursos.Curso;
import model.disciplina.Disciplina;

public class DisciplinasCurso extends JFrame {

	private static final long serialVersionUID = 1L;

	public DisciplinasCurso(Lista<Disciplina> lista, String codCurso, Curso curso) {
		setTitle("Inscritos da Disciplina");
        setSize(763, 466);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        String[] colunas = { "Curso", "Código da disciplina", "Nome da disciplina", "Dia da semana", "Hora de início", "Quantidade de horas"};

        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        int tamanho = lista.size();
        for (int i = 0; i < tamanho; i++) {
            try {
					model.addRow(new Object[]{
				    curso.getNomeCurso(),
				    lista.get(i).getCodDisc(),
				    lista.get(i).getNomeDisc(),
				    lista.get(i).getDiaSemana(),
				    lista.get(i).getHoraInicio(),
				    lista.get(i).getQuantidadeHoras()
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        

        JTable tabela = new JTable(model);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JScrollPane scroll = new JScrollPane(tabela);

        getContentPane().add(scroll, BorderLayout.CENTER);
	}

}
