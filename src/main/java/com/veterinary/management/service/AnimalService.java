/*
 * This class is used to provide the service for the Animal entity
 * It is annotated with the @Service annotation
 * The AnimalService class is a parameterized class as it is annotated with the @Service annotation
 * The AnimalService class is used to provide the service for the Animal entity
 * 
 * Methods:
 * findAllAnimals() - This method is used to find all the animals
 * findAnimalById() - This method is used to find an animal by its id
 * findAnimalsByName() - This method is used to find animals by their name
 * findAnimalsByCustomer() - This method is used to find animals by their customer
 * createAnimal() - This method is used to create an animal
 * updateAnimal() - This method is used to update an animal
 * deleteAnimal() - This method is used to delete an animal
 *
 * Attributes:
 * animalRepository - This is the repository for the Animal entity
 * modelMapper - This is the mapper for the Animal entity
 */



package com.veterinary.management.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.veterinary.management.request.AnimalRequestDto;
import com.veterinary.management.entity.Animal;
import com.veterinary.management.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    public List<Animal> findAllAnimals (){
        return animalRepository.findAll();
    }

    public Animal findAnimalById (Long id){
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "Animal could not found!!!"));
    }
    public List<Animal> findAnimalsByName(String name) {
        return animalRepository.findByNameContaining(name);
    }
    public List<Animal> findAnimalsByCustomer(Long id) {
        return animalRepository.findByCustomerId(id);
    }

    public Animal createAnimal(AnimalRequestDto animalRequestDto){
        Optional<Animal> existAnimalWithSameSpecs = animalRepository.findByNameAndSpeciesAndGenderAndDateOfBirth(animalRequestDto.getName(),animalRequestDto.getSpecies(),animalRequestDto.getGender(),animalRequestDto.getDateOfBirth());

        if (existAnimalWithSameSpecs.isPresent()){
            throw new RuntimeException(animalRequestDto.getName() + "Animal has already been saved.");
        }
        Animal newAnimal = modelMapper.map(animalRequestDto, Animal.class);
        return animalRepository.save(newAnimal);
    }

    public Animal updateAnimal (Long id, AnimalRequestDto animalRequestDto){
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        Optional<Animal> existOtherAnimalFromRequest = animalRepository.findByNameAndSpeciesAndGenderAndDateOfBirth(animalRequestDto.getName(),animalRequestDto.getSpecies(),animalRequestDto.getGender(),animalRequestDto.getDateOfBirth());

        if (animalFromDb.isEmpty()){
            throw new RuntimeException(id + "Animal could not found!!!");
        }

        if (existOtherAnimalFromRequest.isPresent() && !existOtherAnimalFromRequest.get().getId().equals(id)){
            throw new RuntimeException("The animal has already been saved.");
        }

        Animal updatedAnimal = animalFromDb.get();
        modelMapper.map(animalRequestDto, updatedAnimal);
        return animalRepository.save(updatedAnimal);
    }

    public String deleteAnimal (Long id){
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isEmpty()){
            throw new RuntimeException(id + "Animal could not found!!!");
        }
        else {
            animalRepository.delete(animalFromDb.get());
            return "Animal deleted.";
        }
    }
}
