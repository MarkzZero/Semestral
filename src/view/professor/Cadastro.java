package view.professor;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.professor.ProfessorController;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfCPF;
	private JTextField tfArea;
	private JTextField tfQuantidadePontos;


	/**
	 * Create the frame.
	 */
	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel area = new JLabel("Área de conhecimento");
		area.setFont(new Font("Tahoma", Font.PLAIN, 14));
		area.setBounds(413, 77, 153, 32);
		contentPane.add(area);
		
		JLabel cpf = new JLabel("CPF");
		cpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cpf.setBounds(254, 72, 100, 42);
		contentPane.add(cpf);
		
		JLabel nome = new JLabel("Nome");
		nome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nome.setBounds(44, 77, 58, 32);
		contentPane.add(nome);
		
		JLabel Qpontos = new JLabel("Quantidade de pontos");
		Qpontos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Qpontos.setBounds(44, 180, 163, 47);
		contentPane.add(Qpontos);
		
		tfNome = new JTextField();
		tfNome.setBounds(44, 120, 116, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfCPF = new JTextField();
		tfCPF.setBounds(254, 120, 100, 20);
		contentPane.add(tfCPF);
		tfCPF.setColumns(10);
		
		tfArea = new JTextField();
		tfArea.setBounds(423, 120, 153, 20);
		contentPane.add(tfArea);
		tfArea.setColumns(10);
		
		tfQuantidadePontos = new JTextField();
		tfQuantidadePontos.setBounds(44, 238, 146, 20);
		contentPane.add(tfQuantidadePontos);
		tfQuantidadePontos.setColumns(10);
		
		JButton BtnCadastrar = new JButton("Cadastrar");
		BtnCadastrar.setBounds(254, 303, 121, 32);
		contentPane.add(BtnCadastrar);
		
		ProfessorController pCont = new ProfessorController(tfNome, tfCPF, tfArea, tfQuantidadePontos);
		BtnCadastrar.addActionListener(pCont);
		BtnCadastrar.addActionListener(e -> dispose());
	}
}
