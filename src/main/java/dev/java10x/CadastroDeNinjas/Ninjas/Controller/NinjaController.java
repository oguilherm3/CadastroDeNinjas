package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> ListarNinjas(){

        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(
                ninjas
        );

    }

    // Mostrar Ninja por Id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){

        NinjaDTO ninjaPorId = ninjaService.listarNinjasPorId(id);

        if (ninjaPorId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não existe!");
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ninjaPorId);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> alterarNinjasPorId(@PathVariable Long id, @RequestBody NinjaDTO ninja){

        NinjaDTO ninjaAtualizado = ninjaService.atualizarNinja(id, ninja);

        if(ninjaAtualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Houve um erro ao atualizar o ninja com Id: " + id);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(ninjaAtualizado);

    }

    // Deletar Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado");
        }


    }

}
