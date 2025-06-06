package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaEntrada extends JFrame {
    private JComboBox<Produto> cbProduto;
    private JTextField txtQuantidade;
    private JButton btnSalvar;

    public TelaEntrada() {
        setTitle("Entrada de Produto");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Produto:"));
        cbProduto = new JComboBox<>();
        carregarProdutos();
        add(cbProduto);

        add(new JLabel("Quantidade a Adicionar:"));
        txtQuantidade = new JTextField();
        add(txtQuantidade);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                Produto p = (Produto) cbProduto.getSelectedItem();
                int qtd = Integer.parseInt(txtQuantidade.getText());
                p.setQuantidadeEstoque(p.getQuantidadeEstoque() + qtd);

                if (p.getQuantidadeEstoque() > p.getQuantidadeMaxima()) {
                    JOptionPane.showMessageDialog(null, "Atenção: Quantidade excede o máximo permitido!");
                }

                ProdutoDAO dao = new ProdutoDAO();
                dao.atualizar(p);

                JOptionPane.showMessageDialog(null, "Entrada registrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        add(new JLabel());
        add(btnSalvar);
        setVisible(true);
    }

    private void carregarProdutos() {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> produtos = dao.listar();
            for (Produto p : produtos) {
                cbProduto.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage());
        }
    }
}
