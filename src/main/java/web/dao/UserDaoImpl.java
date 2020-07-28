package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByName(String name) {

        Query query = entityManager.createQuery("from User where name = :name")
                .setParameter("name", name);
        return (User) query.getSingleResult();
    }

    @Override
    public User getUserById(Long id) {

        Query query = entityManager.createQuery("from User where id = :id")
                .setParameter("id", id);

        return (User) query.getSingleResult();
    }


    @Override
    public void addUser (User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
            User changedUser = getUserById(user.getId());
            entityManager.detach(changedUser);
            entityManager.merge(user);
    }

    @Override
    public void removeUser(Long id) {
        User deletedUser = getUserById(id);
        entityManager.remove(deletedUser);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        @SuppressWarnings("unchecked")
        List<User> result = entityManager.createQuery("from User").getResultList();
        return result;
    }
}
