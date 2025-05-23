package src.model;

import java.sql.Date;

public class Emprestimo {
    private int id;
    private int livroId;
    private int usuarioId;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int id, int livroId, int usuarioId, Date dataEmprestimo, Date dataDevolucao) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(int livroId, int usuarioId, Date dataEmprestimo) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getId() { return id; }
    public int getLivroId() { return livroId; }
    public int getUsuarioId() { return usuarioId; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
