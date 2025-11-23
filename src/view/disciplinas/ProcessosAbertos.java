package view.disciplinas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import lista.Lista;
import model.cursos.Curso;
import model.disciplina.Disciplina;
import model.disciplina.DisciplinaProcesso;
import model.inscrito.Inscrito;
import repository.CursoRepository;
import repository.DisciplinaRepository;
import repository.InscritoRepository;
import tabela.TabelaHash;

public class ProcessosAbertos extends JFrame {
    

	private static final long serialVersionUID = 1L;
	private String path = "C:\\temp";
    

    public ProcessosAbertos() {
        setTitle("Processos Seletivos Abertos");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        
        JPanel painelRodape = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnFechar.addActionListener(e -> dispose());
        painelRodape.add(btnFechar);
        getContentPane().add(painelRodape, BorderLayout.SOUTH);
        
        carregarDados();
    }
    

    
    private void carregarDados() {
        try {
            TabelaHash<DisciplinaProcesso> tabela = new TabelaHash<>();
            
            InscritoRepository rInscrito = new InscritoRepository(path);
            DisciplinaRepository rDisciplina = new DisciplinaRepository(path);
            CursoRepository rCurso = new CursoRepository(path);
            
            Lista<Inscrito> inscricoes = rInscrito.readFile("inscricoes.csv");
            
            for (int i = 0; i < inscricoes.size(); i++) {
                Inscrito inscricao = inscricoes.get(i);
                
                Disciplina disc = rDisciplina.buscarDisciplina(inscricao.getCodDisciplina());
                
                if (disc != null) {
                    Curso curso = rCurso.buscar(disc.getCodCurso());
                    
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
                        
                        // Insere na tabela hash (não duplica)
                        tabela.inserir(disc.getCodDisc(), dp);
                    }
                }
            }
            
            Lista<DisciplinaProcesso> disciplinas = tabela.listarTodos();
            
            if (disciplinas.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Não há processos seletivos abertos no momento.", 
                    "Aviso", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return;
            }
            
            // Cria e preenche a tabela visual
            exibirTabela(disciplinas);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar processos: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void exibirTabela(Lista<DisciplinaProcesso> disciplinas) throws Exception {
        String[] colunas = {
            "Cód. Disciplina",
            "Nome da Disciplina",
            "Dia da Semana",
            "Horário",
            "Horas",
            "Cód. Curso",
            "Nome do Curso",
            "Área de Conhecimento"
        };
        
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        for (int i = 0; i < disciplinas.size(); i++) {
            DisciplinaProcesso d = disciplinas.get(i);
            model.addRow(new Object[]{
                d.codigoDisciplina,
                d.nomeDisciplina,
                d.diaSemana,
                d.horarioInicial,
                d.quantidadeHoras + "h",
                d.codigoCurso,
                d.nomeCurso,
                d.areaConhecimento
            });
        }
        
        JTable tabela = new JTable(model);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabela.setRowHeight(25);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tabela.getColumnModel().getColumn(0).setPreferredWidth(100);  // Cód. Disciplina
        tabela.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nome Disciplina
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);  // Dia
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);   // Horário
        tabela.getColumnModel().getColumn(4).setPreferredWidth(60);   // Horas
        tabela.getColumnModel().getColumn(5).setPreferredWidth(90);   // Cód. Curso
        tabela.getColumnModel().getColumn(6).setPreferredWidth(180);  // Nome Curso
        tabela.getColumnModel().getColumn(7).setPreferredWidth(150);  // Área
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblTotal = new JLabel("Total de disciplinas disponíveis: " + disciplinas.size());
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 12));
        painelInfo.add(lblTotal);
        
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelInfo, BorderLayout.NORTH);
        painelCentral.add(scrollPane, BorderLayout.CENTER);
        
        getContentPane().add(painelCentral, BorderLayout.CENTER);
    }
    
}

