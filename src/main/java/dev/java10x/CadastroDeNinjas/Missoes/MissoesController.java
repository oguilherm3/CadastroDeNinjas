package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @GetMapping("/getMissoes")
    public String getMissoes(){
        return "Essa rota será usada para mostrar as missoes";
    }

    // GET - Mandar um requisição para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissoes(){
        return "Missoes listadas com sucessos";
    }

    // POST - Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public String criarMissao(){
        return "Missao criada com sucesso";
    }

    // PUT - Mandar uma requisição para alterar as missões
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada com sucesso";
    }

    // DELETE -- Mandar uma requisição para deletar as missões
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada com sucesso";
    }

}
