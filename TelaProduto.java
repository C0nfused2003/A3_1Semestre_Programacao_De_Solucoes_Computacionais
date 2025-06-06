package view;

import dao.ProdutoDAO;
import dao.CategoriaDAO;
import model.Produto;
import model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaProduto extends JFrame {
    private JTextField txtNome, txtPreco, txtUnidade, txtEstoque, txtMin, txtMax;
    private JComboBox<Categoria> cbCategoria;
    private JButton btnSalvar;

    public TelaProduto() {
        setTitle("Cadastro de Produto");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        add(txtPreco);

        add(new JLabel("Unidade:"));
        txtUnidade = new JTextField();
        add(txtUnidade);

        add(new JLabel("Qtd. Estoque:"));
        txtEstoque = new JTextField();
        add(txtEstoque);

        add(new JLabel("Qtd. Mínima:"));
        txtMin = new JTextField();
        add(txtMin);

        add(new JLabel("Qtd. Máxima:"));
        txtMax = new JTextField();
        add(txtMax);

        add(new JLabel("Categoria:"));
        cbCategoria = new JComboBox<>();
        carregarCategorias();
        add(cbCategoria);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Produto p = new Produto();
                    p.setNome(txtNome.getText());
                    p.setPreco(Double.parseDouble(txtPreco.getText()));
                    p.setUnidade(txtUnidade.getText());
                    p.setQuantidadeEstoque(Integer.parseInt(txtEstoque.getText()));
                    p.setQuantidadeMinima(Integer.parseInt(txtMin.getText()));
                    p.setQuantidadeMaxima(Integer.parseInt(txtMax.getText()));
                    p.setCategoria((Categoria) cbCategoria.getSelectedItem());

                    ProdutoDAO dao = new ProdutoDAO();
                    dao.inserir(p);

                    JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
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

    private void carregarCategorias() {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            List<Categoria> categorias = dao.listar();
            for (Categoria c : categorias) {
                cbCategoria.addItem(c);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar categorias: " + e.getMessage());
        }
    }
}
