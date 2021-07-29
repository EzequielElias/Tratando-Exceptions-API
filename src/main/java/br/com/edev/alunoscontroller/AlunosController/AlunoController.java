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

    // Listar todos os alunos sem parâmetros
    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunosService.ListarAlunos();
    }

    // Encontrar aluno através do param nome >> localhost:8080/alunos/encontrarpornome
    @GetMapping("/encontrarpornome")
    public List<Aluno> encontrarPorNome(@RequestParam String nome) {
        return alunosService.encontrarPorNome(nome);
    }

    // Encontrar aluno através do param id >> localhost:8080/alunos/encontrarporid
    @GetMapping("/encontrarporid")
    public List<Aluno> encontrarPorId(@RequestParam Integer id) {
        return alunosService.encontrarPorid(id);
    }

    // Deletar aluno pelo id > localhost:8080/deletaraluno={id}
    @DeleteMapping("/deletaraluno={id}")
    public void deletarAluno(@PathVariable("id") Integer id) {
        alunosService.deletarAluno(id);
    }

}
