package dao;

import java.sql.*;
import java.util.*;
import model.Categoria;
import connection.Conexao;

public class CategoriaDAO {
    public void inserir(Categoria categoria) throws Exception {
        String sql = "INSERT INTO categoria (nome, tamanho, embalagem) VALUES (?, ?, ?)";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, categoria.getNome());
        stmt.setString(2, categoria.getTamanho());
        stmt.setString(3, categoria.getEmbalagem());
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public List<Categoria> listar() throws Exception {
        List<Categoria> lista = new ArrayList<>();
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categoria");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Categoria c = new Categoria();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setTamanho(rs.getString("tamanho"));
            c.setEmbalagem(rs.getString("embalagem"));
            lista.add(c);
        }
        rs.close();
        stmt.close();
        conn.close();
        return lista;
    }

    public void atualizar(Categoria categoria) throws Exception {
        String sql = "UPDATE categoria SET nome=?, tamanho=?, embalagem=? WHERE id=?";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, categoria.getNome());
        stmt.setString(2, categoria.getTamanho());
        stmt.setString(3, categoria.getEmbalagem());
        stmt.setInt(4, categoria.getId());
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM categoria WHERE id=?";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }
}