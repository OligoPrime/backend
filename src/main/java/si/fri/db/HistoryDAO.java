package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.History;

import java.util.List;
import java.util.Optional;

public class HistoryDAO extends AbstractDAO<History> {
    public HistoryDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<History> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public History create(History history) {
        return persist(history);
    }

    @SuppressWarnings("unchecked")
    public List<History> findAll() {
        return list((Query<History>) namedQuery("si.fri.core.Hello.findAll"));
    }
}
