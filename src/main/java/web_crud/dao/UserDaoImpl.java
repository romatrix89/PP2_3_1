package web_crud.dao;

import org.springframework.stereotype.Repository;
import web_crud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
        return em.createNativeQuery("SELECT t.* FROM spring_hiber.users t", User.class).getResultList();
    }

    @Override
    public User findUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void edit(User user) {
        User userToBeEdit = findUserById(user.getId());
        userToBeEdit.setFirstName(user.getFirstName());
        userToBeEdit.setLastName(user.getLastName());
        userToBeEdit.setEmail(user.getEmail());
    }

    public void delete(long id) {
        User userToBeDelete = findUserById(id);
        if (userToBeDelete != null) {
            em.remove(userToBeDelete);
        }
        em.flush();
        em.clear();
    }
}
