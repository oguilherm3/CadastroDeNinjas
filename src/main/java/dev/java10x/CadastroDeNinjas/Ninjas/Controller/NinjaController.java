package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninja){
        return ninjaService.criarNinja(ninja);
    }

    // Mostar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaDTO> ListarNinjas(){
        return ninjaService.listarNinjas();
    }

    // Mostrar Ninja por Id (READ)
    @GetMapping("/listar/{id}")
    public NinjaDTO listarNinjasProId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    public NinjaDTO alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaDTO ninja){
        return ninjaService.atualizarNinja(id, ninja);
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
    }

}
