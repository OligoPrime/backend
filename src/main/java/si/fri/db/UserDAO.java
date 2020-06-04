package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.AuditInterceptor;
import si.fri.core.User;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {


    private final HistoryDAO hDao;

    public UserDAO(SessionFactory factory, HistoryDAO hDao) {
        super(factory);
        this.hDao = hDao;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public User create(User user) {
        return persist(user);
    }

    public void delete(String username, User user) {
        User u = getForUsername(username).get();
        u.setRemoved(true);
        Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession();

        session.beginTransaction();
        session.merge(u);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<User> getForUsername(String username) {
        return Optional.ofNullable((User) namedQuery("si.fri.core.User.getForUsername").setParameter("username", username).getSingleResult());
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list((Query<User>) namedQuery("si.fri.core.User.findAll"));
    }
}
