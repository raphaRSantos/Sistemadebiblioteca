package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import src.dao.LivroDAO;
import src.model.Livro;

public class MainView extends JFrame {
    private JTextField tfTitulo = new JTextField(20);
    private JTextField tfAutor = new JTextField(20);
    private JTextField tfAno = new JTextField(4);
    private JTextArea taLivros = new JTextArea(10, 30);
    private LivroDAO dao;

    public MainView() {
        try {
            dao = new LivroDAO();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco: " + e.getMessage());
            System.exit(1);
        }

        setTitle("Sistema de Biblioteca");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("Título:"));
        panel.add(tfTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(tfAutor);
        panel.add(new JLabel("Ano:"));
        panel.add(tfAno);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnListar = new JButton("Listar Livros");

        btnSalvar.addActionListener(e -> salvarLivro());
        btnListar.addActionListener(e -> listarLivros());

        JPanel botoes = new JPanel();
        botoes.add(btnSalvar);
        botoes.add(btnListar);

        taLivros.setEditable(false);
        JScrollPane scroll = new JScrollPane(taLivros);

        add(panel, BorderLayout.NORTH);
        add(botoes, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void salvarLivro() {
        try {
            String titulo = tfTitulo.getText();
            String autor = tfAutor.getText();
            int ano = Integer.parseInt(tfAno.getText());
            dao.adicionarLivro(new Livro(titulo, autor, ano));
            JOptionPane.showMessageDialog(this, "Livro salvo!");
            tfTitulo.setText("");
            tfAutor.setText("");
            tfAno.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
    }

    private void listarLivros() {
        try {
            List<Livro> livros = dao.listarLivros();
            taLivros.setText("");
            for (Livro l : livros) {
                taLivros.append("ID: " + l.getId() + " | " + l.getTitulo() + " - " + l.getAutor() + " (" + l.getAno() + ")
");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + e.getMessage());
        }
    }
}
