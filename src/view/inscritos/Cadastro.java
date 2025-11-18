package view.inscritos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.inscricoes.inscricoesController;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cpf;
	private JTextField codDisc;

	public Cadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CPF do professor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 63, 119, 28);
		contentPane.add(lblNewLabel);
		
		cpf = new JTextField();
		cpf.setBounds(30, 90, 119, 20);
		contentPane.add(cpf);
		cpf.setColumns(10);
		
		codDisc = new JTextField();
		codDisc.setBounds(214, 90, 125, 20);
		contentPane.add(codDisc);
		codDisc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Código de disciplina");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(214, 63, 125, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(142, 189, 89, 23);
		contentPane.add(btnCadastrar);
		
		inscricoesController inscCtrl = new inscricoesController(cpf, codDisc);
		
		btnCadastrar.addActionListener(inscCtrl);
		btnCadastrar.addActionListener(e -> dispose());

	}

}
