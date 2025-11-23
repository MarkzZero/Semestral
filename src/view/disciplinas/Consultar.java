package view.disciplinas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.disciplinas.disciplinaController;
import model.disciplina.Disciplina;

public class Consultar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfDiaSemana;
	private JTextField tfCodDisciplina;
	private JTextField tfQuantidadeHoras;
	private JTextField tfCodCurso;
	private JTextField tfHoraInicial;

	public Consultar(Disciplina d) {
		setTitle("Disciplinas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new Font("Segoe UI", Font.PLAIN, 14);
		
		tfNome = new JTextField(d.getNomeDisc());
		tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNome.setBounds(215, 23, 157, 32);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		
		tfDiaSemana = new JTextField(d.getDiaSemana());
		tfDiaSemana.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfDiaSemana.setBounds(27, 82, 157, 32);
		contentPane.add(tfDiaSemana);
		tfDiaSemana.setColumns(10);
		
		tfCodDisciplina = new JTextField(d.getCodDisc());
		tfCodDisciplina.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodDisciplina.setBounds(27, 23, 157, 32);
		contentPane.add(tfCodDisciplina);
		tfCodDisciplina.setColumns(10);
		tfCodDisciplina.setEnabled(false);
		
		tfQuantidadeHoras = new JTextField(d.getQuantidadeHoras());
		tfQuantidadeHoras.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfQuantidadeHoras.setBounds(215, 141, 157, 32);
		contentPane.add(tfQuantidadeHoras);
		tfQuantidadeHoras.setColumns(10);
		
		tfCodCurso = new JTextField(d.getCodCurso());
		tfCodCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodCurso.setBounds(215, 82, 157, 32);
		contentPane.add(tfCodCurso);
		tfCodCurso.setColumns(10);
		
		tfHoraInicial = new JTextField(d.getHoraInicio());
		tfHoraInicial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfHoraInicial.setBounds(27, 141, 157, 32);
		contentPane.add(tfHoraInicial);
		tfHoraInicial.setColumns(10);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDeletar.setBounds(27, 199, 157, 32);
		contentPane.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(215, 199, 157, 32);
		contentPane.add(btnEditar);
		
		disciplinaController discCtrl = new disciplinaController(tfCodDisciplina, tfNome, tfCodCurso, tfHoraInicial, tfDiaSemana, tfQuantidadeHoras);

		btnDeletar.addActionListener(discCtrl);
		btnDeletar.addActionListener(e -> dispose());
		btnEditar.addActionListener(discCtrl);
		btnEditar.addActionListener(e -> dispose());
		
	}

}
