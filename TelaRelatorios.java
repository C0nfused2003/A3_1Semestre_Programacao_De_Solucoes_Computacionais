package view;

import dao.ProdutoDAO;
import model.Produto;
import model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TelaRelatorios extends JFrame {

    private JTextArea areaTexto;
    private JComboBox<String> cbRelatorio;
    private JButton btnGerar;

    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel();
        cbRelatorio = new JComboBox<>(new String[]{
            "Lista de Preços",
            "Balanço Físico/Financeiro",
            "Produtos abaixo da Quantidade Mínima",
            "Produtos acima da Quantidade Máxima",
            "Quantidade de Produtos por Categoria"
        });
        btnGerar = new JButton("Gerar Relatório");

        painelTopo.add(cbRelatorio);
        painelTopo.add(btnGerar);
        add(painelTopo, BorderLayout.NORTH);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        btnGerar.addActionListener(e -> gerarRelatorio());

        setVisible(true);
    }

    private void gerarRelatorio() {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            List<Produto> produtos = dao.listar();
            String relatorio = "";
            String tipo = (String) cbRelatorio.getSelectedItem();

            switch (tipo) {
                case "Lista de Preços":
                    relatorio += "LISTA DE PREÇOS:\n";
                    for (Produto p : produtos) {
                        relatorio += String.format("%s (%s) - R$ %.2f - %s\n",
                            p.getNome(), p.getCategoria().getNome(), p.getPreco(), p.getUnidade());
                    }
                    break;
                case "Balanço Físico/Financeiro":
                    double total = 0;
                    relatorio += "BALANÇO FÍSICO/FINANCEIRO:\n";
                    for (Produto p : produtos) {
                        double valorProduto = p.getPreco() * p.getQuantidadeEstoque();
                        relatorio += String.format("%s - Qtd: %d - Total: R$ %.2f\n",
                            p.getNome(), p.getQuantidadeEstoque(), valorProduto);
                        total += valorProduto;
                    }
                    relatorio += String.format("\nTOTAL DO ESTOQUE: R$ %.2f\n", total);
                    break;
                case "Produtos abaixo da Quantidade Mínima":
                    relatorio += "PRODUTOS ABAIXO DO MÍNIMO:\n";
                    for (Produto p : produtos) {
                        if (p.getQuantidadeEstoque() < p.getQuantidadeMinima()) {
                            relatorio += String.format("%s - Estoque: %d / Mínimo: %d\n",
                                p.getNome(), p.getQuantidadeEstoque(), p.getQuantidadeMinima());
                        }
                    }
                    break;
                case "Produtos acima da Quantidade Máxima":
                    relatorio += "PRODUTOS ACIMA DO MÁXIMO:\n";
                    for (Produto p : produtos) {
                        if (p.getQuantidadeEstoque() > p.getQuantidadeMaxima()) {
                            relatorio += String.format("%s - Estoque: %d / Máximo: %d\n",
                                p.getNome(), p.getQuantidadeEstoque(), p.getQuantidadeMaxima());
                        }
                    }
                    break;
                case "Quantidade de Produtos por Categoria":
                    relatorio += "QUANTIDADE POR CATEGORIA:\n";
                    Map<String, Integer> contagem = new java.util.HashMap<>();
                    for (Produto p : produtos) {
                        String cat = p.getCategoria().getNome();
                        contagem.put(cat, contagem.getOrDefault(cat, 0) + 1);
                    }
                    for (String cat : contagem.keySet()) {
                        relatorio += String.format("%s: %d produtos\n", cat, contagem.get(cat));
                    }
                    break;
            }

            areaTexto.setText(relatorio);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + ex.getMessage());
        }
    }
}
