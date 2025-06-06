import javax.swing.*;
import view.TelaCategoria;
import view.TelaEntrada;
import view.TelaProduto;
import view.TelaReajustePreco;
import view.TelaRelatorios;
import view.TelaSaida;

public class App extends JFrame {

    public App() {
        setTitle("Sistema de Controle de Estoque");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem menuProduto = new JMenuItem("Produto");
        JMenuItem menuCategoria = new JMenuItem("Categoria");

        menuProduto.addActionListener(e -> new TelaProduto());
        menuCategoria.addActionListener(e -> new TelaCategoria());

        menuCadastro.add(menuProduto);
        menuCadastro.add(menuCategoria);
        menuBar.add(menuCadastro);

        JMenu menuMov = new JMenu("Movimentação");
        JMenuItem menuEntrada = new JMenuItem("Entrada");
        JMenuItem menuSaida = new JMenuItem("Saída");

        menuEntrada.addActionListener(e -> new TelaEntrada());
        menuSaida.addActionListener(e -> new TelaSaida());

        menuMov.add(menuEntrada);
        menuMov.add(menuSaida);
        menuBar.add(menuMov);

        JMenu menuRelatorio = new JMenu("Relatórios");
        JMenuItem menuRel = new JMenuItem("Visualizar Relatórios");
        menuRel.addActionListener(e -> new TelaRelatorios());
        menuRelatorio.add(menuRel);
        menuBar.add(menuRelatorio);

        setJMenuBar(menuBar);
        setVisible(true);

        JMenu menuUtil = new JMenu("Utilitários");
JMenuItem menuReajuste = new JMenuItem("Reajustar Preços");

menuReajuste.addActionListener(e -> new TelaReajustePreco());
menuUtil.add(menuReajuste);
menuBar.add(menuUtil);

    }

    public static void main(String[] args) {
        new App();
    }
}
