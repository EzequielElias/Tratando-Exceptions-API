package br.com.edev.alunoscontroller.AlunosController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunosService {

    private final List<Aluno> alunos;

    public AlunosService() {
        this.alunos = new ArrayList<>();
    }

    // Cadastro de aluno
    public ResponseEntity cadastrarAluno(Aluno aluno) {

        if(aluno.getId() == null) {
            aluno.setId(alunos.size() + 1);
        }

        if(alunos.contains(aluno)) {
            // Verificar se já existe um aluno com o nome cadastrado
            String alunoJaExistente = "O nome digitado já existe";
            return new ResponseEntity<>(alunoJaExistente, HttpStatus.CONFLICT);
        }
        alunos.add(aluno); // Aluno Cadastrado
        return new ResponseEntity<>("Aluno cadastrado com sucesso!" +
                                          "\n Nome: " + aluno.getNome() +
                                          "\n Idade: " + aluno.getIdade() +
                                          "\n Id: " + aluno.getId(),
                                            HttpStatus.CREATED);
    }

    // Editar aluno existente
    public void editarAluno(@RequestBody final Aluno aluno) {

        alunos.stream()
                .filter(aln -> aln.getId().equals(aluno.getId()))
                .forEach(aln -> aln.setNome(aluno.getNome()));

        alunos.stream()
                .filter(aln -> aln.getId().equals(aluno.getId()))
                .forEach(aln -> aln.setIdade(aluno.getIdade()));

    }

    // Listar todos os alunos sem parâmetros
    public List<Aluno> ListarAlunos(){
        return alunos;
    }

    // Encontrar aluno através do parâmetro nome
    public List<Aluno> encontrarPorNome(String nome) {

        if (nome != null) {

            try {
                return alunos.stream()
                        .filter(aln -> aln.getNome().contains(nome))
                        .collect(Collectors.toList());
            } catch (AlunoInexistenteException e) {
                throw new AlunoInexistenteException();
            }
        }
        return alunos;
    }

    //  Encontrar aluno através do parâmetro id
    public List<Aluno> encontrarPorid(Integer id) {

        if (id != null) {

            try {
                return alunos.stream()
                        .filter(aln -> aln.getId().equals(id))
                        .collect(Collectors.toList());
            } catch (AlunoInexistenteException e) {
                throw new AlunoInexistenteException();
            }
        }
        return alunos;
    }
    // Deletar cadastro de aluno
    public ResponseEntity deletarAluno(Integer id) {
        alunos.removeIf(aln -> aln.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
