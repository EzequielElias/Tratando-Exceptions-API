package br.com.edev.alunoscontroller.AlunosController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    // Tratamento de exceções
    @ExceptionHandler({AlunoInexistenteException.class})
    public ResponseEntity<String> handle(final AlunoInexistenteException e) {
        final String alunoInexistente = "Aluno não encontrado";
        LOGGER.error(alunoInexistente);
        return new ResponseEntity<>(alunoInexistente, HttpStatus.NOT_FOUND);
    }

}
