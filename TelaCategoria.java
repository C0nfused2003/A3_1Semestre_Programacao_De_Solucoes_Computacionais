package view;

import dao.CategoriaDAO;
import model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCategoria extends JFrame {
    private JTextField txtNome;
    private JComboBox<String> cbTamanho;
    private JComboBox<String> cbEmbalagem;
    private JButton btnSalvar;

    public TelaCategoria() {
        setTitle("Cadastro de Categoria");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Tamanho:"));
        cbTamanho = new JComboBox<>(new String[]{"Pequeno", "Médio", "Grande"});
        add(cbTamanho);

        add(new JLabel("Embalagem:"));
        cbEmbalagem = new JComboBox<>(new String[]{"Lata", "Vidro", "Plástico"});
        add(cbEmbalagem);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Categoria c = new Categoria();
                    c.setNome(txtNome.getText());
                    c.setTamanho((String) cbTamanho.getSelectedItem());
                    c.setEmbalagem((String) cbEmbalagem.getSelectedItem());

                    CategoriaDAO dao = new CategoriaDAO();
                    dao.inserir(c);

                    JOptionPane.showMessageDialog(null, "Categoria salva com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
                }
            }
        });
        add(new JLabel());
        add(btnSalvar);

        setVisible(true);
    }
}
