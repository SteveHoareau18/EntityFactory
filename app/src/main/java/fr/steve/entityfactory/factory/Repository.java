package fr.steve.entityfactory.factory;

import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.steve.entityfactory.Manifest;
import fr.steve.entityfactory.SharedPreferenceActivity;

public abstract class Repository<T extends Entity> {

    private final List<T> list;
    private final Gson gson;
    private ArrayAdapter<T> adapter;

    public Repository(List<T> list) {
        this.list = list;
        this.gson = new Gson();
    }

    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        Class<T> clazz = getEntityClass();
        Map<String, ?> allEntries = SharedPreferenceActivity.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith(clazz.getName())) {
                String json = (String) entry.getValue();
                T entity = gson.fromJson(json, clazz);
                entities.add(entity);
            }
        }
        return entities;
    }

    public void add(T entity) {
        this.list.add(entity);
        if (this.adapter != null) this.adapter.notifyDataSetChanged();
        persist(entity);
    }

    public void remove(T entity) {
        Optional<T> find = this.list.stream()
                .filter(x -> x.getId() == entity.getId())
                .findFirst();

        find.ifPresent(list::remove);
        if (this.adapter != null) this.adapter.notifyDataSetChanged();
        persist(entity);
    }

    public void setAdapter(ArrayAdapter<T> adapter) {
        this.adapter = adapter;
    }


    private void persist(T entity) {
        String key = entity.getClass().getName() + "_" + entity.getId();
        String json = gson.toJson(entity);
        SharedPreferenceActivity.edit().putString(key, json).apply();
    }

    @SuppressWarnings("unchecked")
    public T findOneById(int id) {
        String key = Entity.class.getName() + "_" + id;
        String json = SharedPreferenceActivity.getString(key, null);
        if (json != null) {
            return gson.fromJson(json, (Class<T>) Entity.class);
        }
        return null;
    }

    protected abstract Class<T> getEntityClass();
}
