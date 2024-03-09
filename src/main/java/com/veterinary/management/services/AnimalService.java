package com.veterinary.management.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.veterinary.management.models.Animal;
import com.veterinary.management.models.Customer;
import com.veterinary.management.repositories.AnimalRepository;
import com.veterinary.management.requests.AnimalRequest;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerService customerService;

    /*
     * This method returns all the animals in the database
     * 
     * @return the list of all animals
     */
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    /*
     * This method returns an animal by its id
     * 
     * @param id the id of the animal
     * 
     * @return the animal with the given id
     * 
     * @throws UnsupportedOperationException if the id is null
     */
    public Animal getAnimalById(Long id) {
        if (id == null) {
            return null;
        }
        return animalRepository.findById(id).orElse(null);
    }

    /*
     * This method adds a new animal to the database
     * 
     * @param animalRequest the request for adding a new animal
     * 
     * @return the added animal
     * 
     * @throws UnsupportedOperationException if there is no customer with the given
     * id
     */
    public Animal addAnimal(AnimalRequest animalRequest) {
        Animal animal = new Animal();
        animal.setName(animalRequest.getName());
        animal.setSpecies(animalRequest.getSpecies());
        animal.setBreed(animalRequest.getBreed());
        animal.setGender(animalRequest.getGender());
        animal.setColour(animalRequest.getColour());
        animal.setBirthDate(animalRequest.getBirthDate());
        // find the customer by id and throw an exception if there is no customer with
        // the given id
        Customer customer = customerService.getCustomerById(animalRequest.getCustomerId());
        if (customer == null) {
            throw new UnsupportedOperationException("There is no customer with the given id");
        }

        animal.setCustomer(customer);
        return animalRepository.save(animal);
    }

    /*
     * This method updates an animal
     * 
     * @param id the id of the animal
     * 
     * @param animalRequest the request for updating an animal
     * 
     * @return the updated animal
     */
    public Animal updateAnimal(Long id, AnimalRequest animalRequest) {
        if (id == null) {
            return null;
        }
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal != null) {
            animal.setName(animalRequest.getName());
            animal.setSpecies(animalRequest.getSpecies());
            animal.setBreed(animalRequest.getBreed());
            animal.setGender(animalRequest.getGender());
            animal.setColour(animalRequest.getColour());
            animal.setBirthDate(animalRequest.getBirthDate());
            // find the customer by id and throw an exception if there is no customer with
            // the given id
            Customer customer = customerService.getCustomerById(animalRequest.getCustomerId());
            if (customer != null) {
                throw new UnsupportedOperationException("There is no customer with the given id");
            }
            animal.setCustomer(customer);
            return animalRepository.save(animal);
        }
        return null;
    }

    /*
     * This method deletes an animal by its id
     * 
     * @param id the id of the animal
     */
    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        animalRepository.deleteById(id);
    }

    /*
     * This method returns all animals with the given name
     * 
     * @param name the name of the animal
     * 
     * @return the list of animals with the given name
     */
    public List<Animal> findByName(String name) {
        return animalRepository.findByNameContaining(name);
    }

}
