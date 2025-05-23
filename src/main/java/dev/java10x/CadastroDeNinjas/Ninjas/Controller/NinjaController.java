package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String criarNinja(){
        return "Ninja criado!";
    }

    // Mostar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> ListarNinjas(){
        return ninjaService.listarNinjas();
    }

    // Mostrar Ninja por Id (READ)
    @GetMapping("/listarID")
    public String mostrarTodosOsNinjasPorId(){
        return "Mostrar Ninja por Id";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarId")
    public String alterarNinjasPorId(){
        return "Alterar ninja por Id";
    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId(){
        return "Ninja deletado";
    }

}
