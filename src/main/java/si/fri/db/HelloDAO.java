package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.Hello;

import java.util.List;
import java.util.Optional;

public class HelloDAO extends AbstractDAO<Hello> {
    public HelloDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Hello> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Hello create(Hello hello) {
        return persist(hello);
    }

    @SuppressWarnings("unchecked")
    public List<Hello> findAll() {
        return list((Query<Hello>) namedQuery("si.fri.core.Hello.findAll"));
    }
}
