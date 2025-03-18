package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodService = foodItemService;
    }

    //POST
    @PostMapping("/create")
    @Operation(summary = "Cria um novo ingrediente", description = "Um novo ingrediente é adicionado na geladeira " +
            "para ser utilizado na receita")
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem) {
        FoodItem created = foodService.save(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);

    }

    //GET
    @GetMapping("/list")
    @Operation(summary = "Lista os ingredientes", description = "Lista todos os ingredientes cadastrados para serem " +
            "utilizados na receita")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente listado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado")
    })
    public ResponseEntity<List<FoodItem>> list() {
        List<FoodItem> list = foodService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Lista um item", description = "Lista um ingrediente específico com base no ID passado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente listado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado")
    })
    public ResponseEntity<?> listById(@PathVariable Long id) {
        FoodItem ingrediente = foodService.listById(id);

        if (ingrediente != null ){
            foodService.listById(id);
            return ResponseEntity.ok(ingrediente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingrediente com o ID: " + id + " Não foi encontrado");
        }
    }

    //UPDATE
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza um ingrediente", description = "Atualiza um ingrediente específico ocm base no ID " +
            "passado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado")
    })
    public ResponseEntity<?> updateById(@PathVariable Long id, FoodItem foodItem) {
        FoodItem ingrediente = foodService.listById(id);

        if (ingrediente != null) {
            foodService.updateFood(id, foodItem);
            return ResponseEntity.ok(ingrediente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingrediente com o ID: " + id + " Não foi encontrado");
        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingrediente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ingrediente não encontrado")
    })
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        FoodItem ingrediente = foodService.listById(id);

        if (ingrediente != null) {
            foodService.deleteById(id);
            return ResponseEntity.ok("Ingrediente com o ID: " + id + " foi deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingrediente com o ID: " + id + " Não foi encontrado");
        }

    }
}
