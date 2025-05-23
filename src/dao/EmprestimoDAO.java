package src.dao;

import java.sql.*;
import java.util.*;
import src.model.Emprestimo;

public class EmprestimoDAO {
    private Connection conn;

    public EmprestimoDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
    }

    public void registrarEmprestimo(Emprestimo emp) throws SQLException {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, emp.getLivroId());
        stmt.setInt(2, emp.getUsuarioId());
        stmt.setDate(3, emp.getDataEmprestimo());
        stmt.executeUpdate();
        stmt.close();
    }

    public void registrarDevolucao(int emprestimoId, Date dataDevolucao) throws SQLException {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, dataDevolucao);
        stmt.setInt(2, emprestimoId);
        stmt.executeUpdate();
        stmt.close();
    }
}
