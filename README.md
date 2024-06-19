Hello here !

__EntityFactory__ is a plugin that help you to manage your entities.

# Use EntityManager
Create your entities and thier repositories following that example:
```java
public class YourEntity extends Entity {
  
    //your fields
  
    public YourEntity() {
    }

    //your getters and setters
}

public class YourEnntityRepository extends Repository<YourEntity>{
    public YourEntityRepository() {
        super(new ArrayList<>());
    }

    @Override
    protected Class<YourEntity> getEntityClass() {
        return YourEntity.class;
    }
}
```
Create new class that extends `EntityManager`
```java
public class MyEntityManager extends EntityManager {

    private final YourEntityRepository yourEntityRepository;

    public MyEntityManager() {
        yourEntityRepository = new YourEntityRepository();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity, R extends Repository<T>> Optional<R> getRepository(Class<T> clazz) {
        if (!super.getRepository(clazz).isPresent()) {
            if (clazz == YourEntity.class) {
                return Optional.of((R) yourEntityRepository);
            }
        }
        return Optional.empty();
    }
}
```
Use your own EntityManager
```java
//EntityManager use SharedPreferences in this version...
public class MyActivity extends Activity{

    private static EntityManager entityManager;
    @Override
    protected void onStart(){
      //Context can be other, as you want !
      //your_cache_db can be customized !
      new SharedPreferenceActivity("your_cache_db", Context.MODE_PRIVATE);
      entityManager = new MyEntityManager();
      YourEntity yourEntity = new YourEntity();
      entityManager.getRepository(YourEntity.class).ifPresent(repo -> repo.add(yourEntity));
    }

    public static EntityManager getEntityManager(){
        return entityManager;
    }
}
``
