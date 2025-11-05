package view.inscritos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class CRUDinscritos extends JFrame {

	private static final long serialVersionUID = 5L;
	private JPanel contentPane;

	public CRUDinscritos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Inscritos");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cadastro = new JButton("Cadastrar");
		cadastro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cadastro.setBounds(37, 77, 108, 25);
		contentPane.add(cadastro);
		
		JButton consultar = new JButton("Consultar");
		consultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		consultar.setBounds(218, 77, 108, 25);
		contentPane.add(consultar);
		
		JButton editar = new JButton("Editar");
		editar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editar.setBounds(37, 146, 108, 25);
		contentPane.add(editar);
		
		JButton deletar = new JButton("Deletar");
		deletar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deletar.setBounds(218, 146, 108, 26);
		contentPane.add(deletar);
		
		cadastro.addActionListener(e -> new Cadastro().setVisible(true));
		deletar.addActionListener(e -> new Deletar().setVisible(true));
		editar.addActionListener(e -> new Editar().setVisible(true));
		consultar.addActionListener(e -> new Consultar().setVisible(true));
	}

}
