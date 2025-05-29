package dev.java10x.CadastroDeNinjas.Missoes;

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
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    @GetMapping("/listar/{id}")
    public MissoesModel listarMissaoPorId(@PathVariable long id){
        return missoesService.listarMissaoPorId(id).orElseThrow();
    }

    // POST - Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missao){
        return missoesService.criarMissao(missao);
    }

    // PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE -- Mandar uma requisição para deletar as missões
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable Long id){
        missoesService.deletarMissaoPorID(id);
    }

}
