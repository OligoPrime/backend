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

    public PrimerDAO(SessionFactory factory) {
        super(factory);
        this.sessionFactory = factory;
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

    @SuppressWarnings("unchecked")
    public List<Primer> findWanted() {
        return list((Query<Primer>) namedQuery("si.fri.core.Primer.findWanted"));
    }

    @SuppressWarnings("unchecked")
    public List<Primer> findOrdered() {
        return list((Query<Primer>) namedQuery("si.fri.core.Primer.findOrdered"));
    }

    @SuppressWarnings("unchecked")
    public List<Primer> findReceived() {
        return list((Query<Primer>) namedQuery("si.fri.core.Primer.findReceived"));
    }

    public void deletePrimer(long id) {
        String hql = "DELETE FROM Primer p WHERE p.id = :primerId";
        Query query = currentSession().createQuery(hql);
        query.setParameter("primerId", id).executeUpdate();
    }
}
