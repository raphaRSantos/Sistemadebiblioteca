package src.dao;

import java.sql.*;
import java.util.*;
import src.model.Livro;

public class LivroDAO {
    private Connection conn;

    public LivroDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
    }

    public void adicionarLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getAutor());
        stmt.setInt(3, livro.getAno());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Livro> listarLivros() throws SQLException {
        List<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano")));
        }
        rs.close();
        stmt.close();
        return lista;
    }

    public void removerLivro(int id) throws SQLException {
        String sql = "DELETE FROM livros WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
