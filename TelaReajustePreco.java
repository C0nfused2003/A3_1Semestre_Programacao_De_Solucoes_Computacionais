package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaReajustePreco extends JFrame {
    private JTextField txtPercentual;
    private JButton btnAplicar;

    public TelaReajustePreco() {
        setTitle("Reajuste de Preço");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Percentual de reajuste (%):"));
        txtPercentual = new JTextField();
        add(txtPercentual);

        btnAplicar = new JButton("Aplicar");
        btnAplicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double percentual = Double.parseDouble(txtPercentual.getText()) / 100.0;

                    ProdutoDAO dao = new ProdutoDAO();
                    List<Produto> produtos = dao.listar();

                    for (Produto p : produtos) {
                        double novoPreco = p.getPreco() * (1 + percentual);
                        p.setPreco(novoPreco);
                        dao.atualizar(p);
                    }

                    JOptionPane.showMessageDialog(null, "Preços reajustados com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao reajustar preços: " + ex.getMessage());
                }
            }
        });

        add(new JLabel());
        add(btnAplicar);
        setVisible(true);
    }
}
