package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.AuditInterceptor;
import si.fri.core.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static si.fri.auth.Passwords.hashPassword;

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

    public void updatePassword(User user, String newPassword) {
        try (Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession()) {
            List<byte[]> salt_hash = Objects.requireNonNull(hashPassword(newPassword));

            User u = session.get(User.class, user.getId());
            if (u != null) {
                u.setSalt(salt_hash.get(0));
                u.setHash(salt_hash.get(1));
            }
            session.beginTransaction();
            session.merge(u);
            session.getTransaction().commit();
        }
    }

    public void addToFavourites(Set<Long> ids, User user) {
        try (Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession()) {
            User u = session.get(User.class, user.getId());
            if (u != null) {
                Set<Long> favourites = u.getFavourites();
                favourites.addAll(ids);
                u.setFavourites(favourites);

                session.beginTransaction();
                session.merge(u);
                session.getTransaction().commit();
            }
        }
    }

    public void removeFavourites(Set<Long> ids, User user) {
        try (Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession()) {
            User u = session.get(User.class, user.getId());
            if (u != null) {
                Set<Long> favourites = u.getFavourites();
                favourites.removeAll(ids);
                u.setFavourites(favourites);

                session.beginTransaction();
                session.merge(u);
                session.getTransaction().commit();
            }
        }
    }

    public void delete(String username, User user) {
        Optional<User> u = getForUsername(username);
        if (u.isPresent()) {
            User user1 = u.get();
            try (Session session = super.currentSession().getSession()) {

                user1.setRemoved(true);

                session.beginTransaction();
                session.merge(user1);
                session.getTransaction().commit();
                session.close();
            }
        }
    }

    public Optional<User> getForUsername(String username) {
        return Optional.ofNullable((User) namedQuery("si.fri.core.User.getForUsername").setParameter("username", username).getSingleResult());
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return list((Query<User>) namedQuery("si.fri.core.User.findAll"));
    }

}
