package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public User create(User user) {
        return persist(user);
    }

    public Optional<User> getForUsername(String username){
        return Optional.ofNullable((User) namedQuery("si.fri.core.User.getForUsername").setParameter("username",username).getSingleResult());
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list((Query<User>) namedQuery("si.fri.core.User.findAll"));
    }
}
