package br.com.edev.alunoscontroller.AlunosController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    AlunosService alunosService = new AlunosService();

    // Cadastrar aluno
    @PostMapping
    public ResponseEntity addAluno(@RequestBody Aluno aluno) {
        return alunosService.cadastrarAluno(aluno);
    }

    // Editar aluno
    @PutMapping
    public ResponseEntity editarAluno(@RequestBody final Aluno aluno) {
        alunosService.editarAluno(aluno);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // Encontrar aluno através do param nome
    @GetMapping
    public List<Aluno> encontrarPorNome(@RequestParam(required = false) String nome, Integer id) {
        if(nome != null) {
            return alunosService.encontrarPorNome(nome);

        } else if(id != null) {
            return alunosService.encontrarPorId(id);
        }
        return alunosService.listarAlunos();
    }

    // Deletar aluno pelo id > localhost:8080/deletaraluno={id}
    @DeleteMapping("/deletaraluno={id}")
    public void deletarAluno(@PathVariable("id") Integer id) {
        alunosService.deletarAluno(id);
    }

}
