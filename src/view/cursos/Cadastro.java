package view.cursos;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.curso.CursoController;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codCurso;
	private JTextField NomeCurso;
	private JTextField Area;

	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código do curso");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 61, 107, 24);
		contentPane.add(lblNewLabel);
		
		codCurso = new JTextField();
		codCurso.setBounds(28, 95, 107, 20);
		contentPane.add(codCurso);
		codCurso.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do curso");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(213, 68, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		NomeCurso = new JTextField();
		NomeCurso.setBounds(213, 95, 100, 20);
		contentPane.add(NomeCurso);
		NomeCurso.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Área de conhecimento");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(28, 144, 137, 24);
		contentPane.add(lblNewLabel_2);
		
		Area = new JTextField();
		Area.setBounds(184, 148, 130, 20);
		contentPane.add(Area);
		Area.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(158, 206, 107, 23);
		contentPane.add(btnCadastrar);
		
		CursoController cursoCtrl = new CursoController(codCurso, NomeCurso, Area);
		
		btnCadastrar.addActionListener(cursoCtrl);
		btnCadastrar.addActionListener(e -> dispose());

	}
}
