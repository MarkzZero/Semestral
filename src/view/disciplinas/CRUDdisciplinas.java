package view.disciplinas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.cursos.Deletar;

import javax.swing.JButton;

public class CRUDdisciplinas extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;


	public CRUDdisciplinas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Disciplinas");
		setBounds(100, 100, 450, 300);
		setLocation(300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cadastro = new JButton("Cadastrar");
		cadastro.setBounds(41, 69, 109, 46);
		contentPane.add(cadastro);
		
		JButton consulta = new JButton("Consultar");
		consulta.setBounds(258, 75, 125, 35);
		contentPane.add(consulta);
		
		JButton editar = new JButton("Editar");
		editar.setBounds(41, 167, 109, 29);
		contentPane.add(editar);
		
		JButton deletar = new JButton("Deletar");
		deletar.setBounds(258, 168, 125, 26);
		contentPane.add(deletar);
		
		cadastro.addActionListener(e -> new Cadastro().setVisible(true));
		consulta.addActionListener(e -> new Consultar().setVisible(true));
		editar.addActionListener(e -> new Editar().setVisible(true));
		deletar.addActionListener(e -> new Deletar().setVisible(true));

	}

}
