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

    public List<Aluno> listarAlunos() {

        if(alunos.isEmpty()) {
            throw new AlunoInexistenteException();
        }
        return alunos;
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

    // Encontrar aluno através do parâmetro nome
    public List<Aluno> encontrarPorNome(String nome) {
        List<Aluno> alunosEncontrados = new ArrayList<>(alunos.stream().filter(aln -> aln.getNome().contains(nome)).collect(Collectors.toList()));

        if(alunosEncontrados.isEmpty()) {
            throw new AlunoInexistenteException();
        }
        return alunosEncontrados;
    }

    //  Encontrar aluno através do parâmetro id
    public List<Aluno> encontrarPorId(int id) {
        List<Aluno> alunosEncontrados = new ArrayList<>(alunos.stream().filter(aln -> aln.getId() == id).collect(Collectors.toList()));

        if(alunosEncontrados.isEmpty()) {
            throw new AlunoInexistenteException();
        }
        return alunosEncontrados;
    }
    
    // Deletar cadastro de aluno
    public ResponseEntity deletarAluno(Integer id) {
        alunos.removeIf(aln -> aln.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
