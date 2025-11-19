package view.cursos;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.curso.CursoController;

public class CRUDcursos extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;
	private JTextField tfCodCurso;
	private JTextField tfNomeCurso;
	private JTextField tfArea;

	public CRUDcursos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cursos");
		setBounds(100, 100, 429, 300);
		setLocation(400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null); 
		contentPane.setLayout(null);
		
		tfCodCurso = new JTextField();
		tfCodCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodCurso.setBounds(24, 23, 148, 29);
		contentPane.add(tfCodCurso);
		tfCodCurso.setColumns(10);
		
		tfNomeCurso = new JTextField();
		tfNomeCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNomeCurso.setBounds(24, 87, 148, 29);
		contentPane.add(tfNomeCurso);
		tfNomeCurso.setColumns(10);
		
		tfArea = new JTextField();
		tfArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfArea.setBounds(24, 146, 147, 29);
		contentPane.add(tfArea);
		tfArea.setColumns(10);
		
		addPlaceholder(tfCodCurso, "Código do curso");
		addPlaceholder(tfNomeCurso, "Nome do curso");
		addPlaceholder(tfArea, "Área de conhecimento");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnCadastrar.setBounds(24, 208, 148, 29);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnLimpar.setBounds(240, 208, 148, 29);
		contentPane.add(btnLimpar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnConsultar.setBounds(240, 23, 148, 29);
		contentPane.add(btnConsultar);
		
		CursoController cursoCtrl = new CursoController(tfCodCurso, tfNomeCurso, tfArea);
		
		cursoCtrl.setTela(this);
		
		btnCadastrar.addActionListener(cursoCtrl);
		btnLimpar.addActionListener(e -> limparCampos());
		btnConsultar.addActionListener(cursoCtrl);
	}
	
	public void limparCampos() {
		tfCodCurso.setText("");
		tfCodCurso.setCaretPosition(0);
		tfNomeCurso.setText("");
		tfArea.setText("");
		
		addPlaceholder(tfNomeCurso, " Nome do curso");
		addPlaceholder(tfArea, " Área de conhecimento");
		addPlaceholder(tfCodCurso, " Código do curso");
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
