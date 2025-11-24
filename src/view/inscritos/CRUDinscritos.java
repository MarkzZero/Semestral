package view.inscritos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.inscricoes.inscricoesController;

public class CRUDinscritos extends JFrame {

    private static final long serialVersionUID = 5L;
    private JPanel contentPane;
    private JTextField tfCodDisciplina;
    private JFormattedTextField tfCPF;

    public CRUDinscritos() {
        setTitle("Inscritos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 420, 219);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tfCodDisciplina = new JTextField();
        tfCodDisciplina.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tfCodDisciplina.setBounds(27, 76, 157, 32);
        contentPane.add(tfCodDisciplina);
        tfCodDisciplina.setColumns(10);

        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            tfCPF = new JFormattedTextField(mask);
            tfCPF.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            tfCPF.setBounds(27, 23, 157, 32);
            contentPane.add(tfCPF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnConsultar.setBounds(215, 23, 157, 32);
        contentPane.add(btnConsultar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnCadastrar.setBounds(27, 130, 157, 32);
        contentPane.add(btnCadastrar);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnFechar.setBounds(215, 130, 157, 32);
        contentPane.add(btnFechar);

        addPlaceholder(tfCodDisciplina, " Código da disciplina");

        SwingUtilities.invokeLater(() -> {
            btnConsultar.requestFocusInWindow();
        });

        inscricoesController inscCtrl = new inscricoesController(tfCPF, tfCodDisciplina);
        inscCtrl.setTela(this);

        btnCadastrar.addActionListener(inscCtrl);
        btnConsultar.addActionListener(inscCtrl);
        btnFechar.addActionListener(e -> dispose());
    }

    public void limparCampos() {
        tfCodDisciplina.setText("");
        tfCPF.setValue(null); 

        addPlaceholder(tfCodDisciplina, " Código da disciplina");
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