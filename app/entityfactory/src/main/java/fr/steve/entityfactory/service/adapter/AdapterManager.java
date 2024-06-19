package fr.steve.entityfactory.service.adapter;

import android.app.Activity;

import java.util.Optional;

import fr.steve.helloworld.entity.address.Address;
import fr.steve.helloworld.entity.address.AddressRepository;
import fr.steve.helloworld.entity.person.Person;
import fr.steve.helloworld.entity.person.PersonRepository;
import fr.steve.helloworld.factory.Entity;
import fr.steve.helloworld.service.EntityManager;

public abstract class AdapterManager {

    private final Activity activity;
    private final EntityManager entityManager;

    public AdapterManager(Activity activity, EntityManager entityManager) {
        this.activity = activity;
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity> Optional<AdapterBundleLayout<?, T>> getEntity(Class<T> clazz) {
        if (clazz == Person.class) {
            return Optional.of((AdapterBundleLayout<?, T>) new AdapterBundleLayout<PersonRepository, Person>(activity)
                    .setLayout(android.R.layout.simple_spinner_item)
                    .setItems((PersonRepository) entityManager.getRepository(Person.class).get()));
        } else if (clazz == Address.class) {
            return Optional.of((AdapterBundleLayout<?, T>) new AdapterBundleLayout<AddressRepository, Address>(activity)
                    .setLayout(android.R.layout.simple_spinner_item)
                    .setItems((AddressRepository) entityManager.getRepository(Address.class).get()));
        }
        return Optional.empty();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Activity getActivity() {
        return activity;
    }
}