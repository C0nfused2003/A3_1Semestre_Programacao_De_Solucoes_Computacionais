package dao;

import java.sql.*;
import java.util.*;
import model.Produto;
import model.Categoria;
import connection.Conexao;

public class ProdutoDAO {
    public void inserir(Produto produto) throws Exception {
        String sql = "INSERT INTO produto (nome, preco, unidade, quantidade_estoque, quantidade_minima, quantidade_maxima, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setString(3, produto.getUnidade());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setInt(5, produto.getQuantidadeMinima());
        stmt.setInt(6, produto.getQuantidadeMaxima());
        stmt.setInt(7, produto.getCategoria().getId());
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public List<Produto> listar() throws Exception {
        List<Produto> lista = new ArrayList<>();
        Connection conn = Conexao.getConexao();
        String sql = "SELECT p.*, c.nome as nome_categoria, c.tamanho, c.embalagem FROM produto p JOIN categoria c ON p.categoria_id = c.id";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Categoria cat = new Categoria();
            cat.setId(rs.getInt("categoria_id"));
            cat.setNome(rs.getString("nome_categoria"));
            cat.setTamanho(rs.getString("tamanho"));
            cat.setEmbalagem(rs.getString("embalagem"));

            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));
            p.setUnidade(rs.getString("unidade"));
            p.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
            p.setQuantidadeMinima(rs.getInt("quantidade_minima"));
            p.setQuantidadeMaxima(rs.getInt("quantidade_maxima"));
            p.setCategoria(cat);

            lista.add(p);
        }
        rs.close();
        stmt.close();
        conn.close();
        return lista;
    }

    public void atualizar(Produto produto) throws Exception {
        String sql = "UPDATE produto SET nome=?, preco=?, unidade=?, quantidade_estoque=?, quantidade_minima=?, quantidade_maxima=?, categoria_id=? WHERE id=?";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setString(3, produto.getUnidade());
        stmt.setInt(4, produto.getQuantidadeEstoque());
        stmt.setInt(5, produto.getQuantidadeMinima());
        stmt.setInt(6, produto.getQuantidadeMaxima());
        stmt.setInt(7, produto.getCategoria().getId());
        stmt.setInt(8, produto.getId());
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM produto WHERE id=?";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }
}