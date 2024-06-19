package fr.steve.entityfactory.service;

import java.util.Optional;

import fr.steve.helloworld.entity.address.Address;
import fr.steve.helloworld.entity.address.AddressRepository;
import fr.steve.helloworld.entity.person.Person;
import fr.steve.helloworld.entity.person.PersonRepository;
import fr.steve.helloworld.factory.Entity;
import fr.steve.helloworld.factory.Repository;

public abstract class EntityManager {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public EntityManager() {
        this.personRepository = new PersonRepository();
        this.addressRepository = new AddressRepository();
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity, R extends Repository<T>> Optional<R> getRepository(Class<T> clazz) {
        if (clazz == Person.class) {
            return Optional.of((R) personRepository);
        } else if (clazz == Address.class) {
            return Optional.of((R) addressRepository);
        }
        return Optional.empty();
    }
}
