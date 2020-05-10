package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.*;
import si.fri.core.primer_foreign_tables.*;

import java.util.List;
import java.util.Optional;

public class PrimerDAO extends AbstractDAO<Primer> {

    private final SessionFactory sessionFactory;
    private final Session currentSession;

    public PrimerDAO(SessionFactory factory) {
        super(factory);
        this.sessionFactory = factory;
        this.currentSession = currentSession();
    }

    public Optional<Primer> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Primer create(Primer primer) {
        Primer p = persist(primer);
        p.generateName();
        return p;
    }

    @SuppressWarnings("unchecked")
    public List<Primer> findAll() {
        return list((Query<Primer>) namedQuery("si.fri.core.Primer.findAll"));
    }

    public void deletePrimer(long id) {
        String hql = "DELETE FROM Primer p WHERE p.id = :primerId";
        Query query = currentSession.createQuery(hql);
        query.setParameter("primerId", id).executeUpdate();
    }
}
