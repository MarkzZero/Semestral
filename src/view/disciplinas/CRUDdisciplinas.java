package view.disciplinas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.disciplinas.disciplinaController;

public class CRUDdisciplinas extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfDiaSemana;
	private JTextField tfCodDisciplina;
	private JTextField tfQuantidadeHoras;
	private JTextField tfCodCurso;
	private JTextField tfHoraInicial;
	private JButton btnInscritos;

	public CRUDdisciplinas() {
		setTitle("Disciplinas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new Font("Segoe UI", Font.PLAIN, 14);

		tfCodDisciplina = new JTextField();
		tfCodDisciplina.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodDisciplina.setBounds(27, 42, 157, 32);
		contentPane.add(tfCodDisciplina);

		JButton btnConsultar = new JButton("Buscar disciplina");
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnConsultar.setBounds(215, 42, 157, 32);
		contentPane.add(btnConsultar);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNome.setBounds(27, 101, 345, 32);
		contentPane.add(tfNome);

		tfDiaSemana = new JTextField();
		tfDiaSemana.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfDiaSemana.setBounds(27, 160, 157, 32);
		contentPane.add(tfDiaSemana);

		tfCodCurso = new JTextField();
		tfCodCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodCurso.setBounds(215, 160, 157, 32);
		contentPane.add(tfCodCurso);

		tfHoraInicial = new JTextField();
		tfHoraInicial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfHoraInicial.setBounds(27, 222, 157, 32);
		contentPane.add(tfHoraInicial);

		tfQuantidadeHoras = new JTextField();
		tfQuantidadeHoras.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfQuantidadeHoras.setBounds(215, 222, 157, 32);
		contentPane.add(tfQuantidadeHoras);

		btnInscritos = new JButton("Consultar inscritos");
		btnInscritos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnInscritos.setBounds(27, 291, 157, 32);
		contentPane.add(btnInscritos);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCadastrar.setBounds(27, 344, 157, 32);
		contentPane.add(btnCadastrar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnFechar.setBounds(215, 344, 157, 32);
		contentPane.add(btnFechar);

		addPlaceholder(tfCodDisciplina, " Código da disciplina");
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfDiaSemana, " Dia da semana");
		addPlaceholder(tfCodCurso, " Código do curso");
		addPlaceholder(tfHoraInicial, " Hora inicial");
		addPlaceholder(tfQuantidadeHoras, " Carga horária");

		disciplinaController discCtrl = new disciplinaController(
				tfCodDisciplina, tfNome, tfCodCurso, tfHoraInicial, tfDiaSemana, tfQuantidadeHoras
		);

		discCtrl.setTela(this);

		btnCadastrar.addActionListener(discCtrl);
		btnConsultar.addActionListener(discCtrl);
		btnInscritos.addActionListener(discCtrl);
		btnFechar.addActionListener(e -> dispose());

		SwingUtilities.invokeLater(() -> btnConsultar.requestFocusInWindow());
	}

	public void limparCampos() {
		tfCodDisciplina.setText("");
		tfNome.setText("");
		tfDiaSemana.setText("");
		tfCodCurso.setText("");
		tfHoraInicial.setText("");
		tfQuantidadeHoras.setText("");

		addPlaceholder(tfCodDisciplina, " Código da disciplina");
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfDiaSemana, " Dia da semana");
		addPlaceholder(tfCodCurso, " Código do curso");
		addPlaceholder(tfHoraInicial, " Hora inicial");
		addPlaceholder(tfQuantidadeHoras, " Carga horária");
	}

	private static void addPlaceholder(JTextField field, String placeholder) {
		field.setForeground(Color.GRAY);
		field.setText(placeholder);

		field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (field.getText().equals(placeholder)) {
					field.setText("");
					field.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setForeground(Color.GRAY);
					field.setText(placeholder);
				}
			}
		});
	}
}
