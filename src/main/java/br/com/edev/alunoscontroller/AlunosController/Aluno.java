package br.com.edev.alunoscontroller.AlunosController;

public class Aluno {

    private String nome;
    private Integer idade;
    private Integer id;

    // Constructor
    public Aluno(String nome, Integer idade, Integer id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }

    // Getter's and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
