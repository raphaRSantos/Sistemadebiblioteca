package src.dao;

import java.sql.*;
import java.util.*;
import src.model.Usuario;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
    }

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            lista.add(new Usuario(rs.getInt("id"), rs.getString("nome")));
        }
        rs.close();
        stmt.close();
        return lista;
    }
}
