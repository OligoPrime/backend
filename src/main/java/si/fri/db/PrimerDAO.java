package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.Primer;

import java.util.List;
import java.util.Optional;

public class PrimerDAO extends AbstractDAO<Primer> {
    public PrimerDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Primer> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Primer create(Primer primer) {
        return persist(primer);
    }

    @SuppressWarnings("unchecked")
    public List<Primer> findAll() {
        return list((Query<Primer>) namedQuery("si.fri.core.Primer.findAll"));
    }
}
