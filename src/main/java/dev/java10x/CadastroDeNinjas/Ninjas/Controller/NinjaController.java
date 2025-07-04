package dev.java10x.CadastroDeNinjas.Ninjas.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela", deprecated = true)
    public String boasVindas(){
        return "Essa é a minha primeira mensagem nessa rota";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas no banco de dados")
    public ResponseEntity<List<NinjaDTO>> ListarNinjas(){

        List<NinjaDTO> ninjas = ninjaService.listarNinjas();

        return ResponseEntity.ok(
                ninjas
        );

    }

    // Mostrar Ninja por Id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista ninja por Id", description = "Rota lista um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja econtrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado")
    })
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
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não encontrado, não foi possível alterar")
    })
    public ResponseEntity<?> alterarNinjasPorId(
            @Parameter(description = "Usuário manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuário manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninja){

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
    @Operation(summary = "Deleta um ninja pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não econtrado para efetuar deleção")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuário manda o id no caminho da requisição")
            @PathVariable Long id
        ){
        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado");
        }


    }

}
