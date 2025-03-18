package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodItemService {

    private final FoodItemRepository foodRepository;

    public FoodItemService(FoodItemRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        log.info("Iniciando salvamento de foodItem");
        log.info("FoodItem salvo com sucesso!");
        return foodRepository.save(foodItem);

    }

    public List<FoodItem> list() {
        log.info("Iniciando listagem de itens cadastrados");
        log.info("Finalizando listagem de itens cadastrados");
        return foodRepository.findAll();
    }

    public FoodItem listById(Long id) {
        log.info("Iniciando busca de item cadastrado por ID");
        Optional<FoodItem> foodById = foodRepository.findById(id);
        log.info("Finalizando busca de item cadastrado, retornando item caso encontrado");
        return foodById.orElse(null);
    }

    public void deleteById(Long id) {
        log.info("Iniciando deleção de item cadastrado por ID caso encontrado");
        log.info("Finalizando deleção de item cadastrado");
        foodRepository.deleteById(id);
    }

    public FoodItem updateFood(Long id, FoodItem updatedFoodItem) {
        log.info("Iniciando atualização nos dados do item cadastrado");
        if (foodRepository.existsById(id)) {
            updatedFoodItem.setId(id);
            log.info("Finalizando atualização e salvando o item novo");
            return foodRepository.save(updatedFoodItem);
        }
        log.error("Finalizando atualização, item não encontrado");
        return null;
    }

}
