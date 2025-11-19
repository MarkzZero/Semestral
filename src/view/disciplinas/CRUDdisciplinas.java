package view.disciplinas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class CRUDdisciplinas extends JFrame {

	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfDiaSemana;
	private JTextField txtHorario;
	private JTextField tfCodDisciplina;
	private JTextField tfQuantidadeHoras;
	private JTextField tfCodCurso;

	public CRUDdisciplinas() {
		setTitle("Disciplinas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		new Font("Segoe UI", Font.PLAIN, 14);
		
		try {
		    MaskFormatter mask = new MaskFormatter(" ###.###.###-##");
		    mask.setPlaceholderCharacter('_');
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNome.setBounds(27, 76, 157, 32);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		
		tfDiaSemana = new JTextField();
		tfDiaSemana.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfDiaSemana.setBounds(27, 131, 157, 32);
		contentPane.add(tfDiaSemana);
		tfDiaSemana.setColumns(10);
		
		
		try {
            MaskFormatter mascaraHora = new MaskFormatter("##:##");
            mascaraHora.setPlaceholderCharacter('_');
            txtHorario = new JFormattedTextField(mascaraHora);
            txtHorario.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            txtHorario.setBounds(27, 185, 157, 32);
            contentPane.add(txtHorario);
            
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		tfCodDisciplina = new JTextField();
		tfCodDisciplina.setBounds(27, 23, 157, 32);
		contentPane.add(tfCodDisciplina);
		tfCodDisciplina.setColumns(10);
		
		tfQuantidadeHoras = new JTextField();
		tfQuantidadeHoras.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfQuantidadeHoras.setBounds(215, 185, 157, 32);
		contentPane.add(tfQuantidadeHoras);
		tfQuantidadeHoras.setColumns(10);
		
		tfCodCurso = new JTextField();
		tfCodCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodCurso.setBounds(215, 131, 157, 32);
		contentPane.add(tfCodCurso);
		tfCodCurso.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnConsultar.setBounds(215, 23, 157, 32);
		contentPane.add(btnConsultar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCadastrar.setBounds(27, 237, 157, 32);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnLimpar.setBounds(215, 237, 157, 32);
		contentPane.add(btnLimpar);
		
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfDiaSemana, " Dia da semana");
		addPlaceholder(tfQuantidadeHoras, " Quantidade de horas");
		addPlaceholder(tfCodCurso, " Código do curso");
		addPlaceholder(tfCodDisciplina, " Código da discipina");

	}
	
	public void limparCampos() {
		tfNome.setText("");
		tfDiaSemana.setCaretPosition(0);
		tfQuantidadeHoras.setText("");
		tfCodCurso.setText("");
		tfCodDisciplina.setText("");
		
		addPlaceholder(tfNome, " Nome");
		addPlaceholder(tfDiaSemana, " Dia da semana");
		addPlaceholder(tfQuantidadeHoras, " Quantidade de horas");
		addPlaceholder(tfCodCurso, " Código do curso");
		addPlaceholder(tfCodDisciplina, " Código da discipina");
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
