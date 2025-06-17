package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/getMissoes")
    public String getMissoes(){
        return "Essa rota será usada para mostrar as missoes";
    }

    // GET - Mandar um requisição para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        return ResponseEntity.status(HttpStatus.OK).
                body(missoesService.listarMissoes());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable long id){
        MissoesDTO missao = missoesService.listarMissaoPorId(id);

        if(missao == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com Id " + id + " não encontrada");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(missao);
    }

    // POST - Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missao){
        MissoesDTO missaoCriada = missoesService.criarMissao(missao);

        return ResponseEntity.ok("Missao com" + missaoCriada.getId() + " criada com sucesso");

    }

    // PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missao){

        MissoesDTO missaoPorId = missoesService.alterarMissaoPorId(id, missao);

        if(missaoPorId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Houve um erro ao atualizar o ninja com Id: " + id);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(missaoPorId);
    }

    // DELETE -- Mandar uma requisição para deletar as missões
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id){

        if(missoesService.listarMissaoPorId(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com id " + id + " não encontrado");
        }

        missoesService.deletarMissaoPorID(id);
        return  ResponseEntity.status(HttpStatus.OK)
                .body("Missao com id " + id + "deletada com sucesso");
    }

}
