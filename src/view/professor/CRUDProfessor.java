package view.professor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.professor.ProfessorController;

public class CRUDProfessor extends JFrame {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField tfCPF;
	private JTextField tfNome;
	private JTextField tfArea;
	private JTextField tfPontos;
	
	
	public CRUDProfessor() {
		setTitle("Professor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new Font("Segoe UI", Font.PLAIN, 14);
		
		try {
		    MaskFormatter mask = new MaskFormatter(" ###.###.###-##");
		    mask.setPlaceholderCharacter('_');
		    tfCPF = new JFormattedTextField(mask);
		    tfCPF.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		    tfCPF.setBounds(27, 23, 157, 32);
		    contentPane.add(tfCPF);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNome.setBounds(27, 76, 157, 32);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		
		tfArea = new JTextField();
		tfArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfArea.setBounds(27, 131, 157, 32);
		contentPane.add(tfArea);
		tfArea.setColumns(10);
		
		tfPontos = new JTextField();
		tfPontos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfPontos.setBounds(215, 131, 157, 32);
		contentPane.add(tfPontos);
		tfPontos.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnConsultar.setBounds(215, 23, 157, 32);
		contentPane.add(btnConsultar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCadastrar.setBounds(27, 187, 157, 32);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnLimpar.setBounds(215, 187, 157, 32);
		contentPane.add(btnLimpar);
		
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfArea, " Área de conhecimento");
		addPlaceholder(tfPontos, " Quantidade de Pontos");
		
		ProfessorController profCtrl = new ProfessorController(tfNome, tfCPF, tfArea, tfPontos);
		
		btnCadastrar.addActionListener(profCtrl);
		btnLimpar.addActionListener(e -> limparCampos());
		btnConsultar.addActionListener(profCtrl);
		
	}
	
	
	public void limparCampos() {
		tfCPF.setText("");
		tfCPF.setCaretPosition(0);
		tfNome.setText("");
		tfArea.setText("");
		tfPontos.setText("");
		
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfArea, " Área de conhecimento");
		addPlaceholder(tfPontos, " Quantidade de Pontos");
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
