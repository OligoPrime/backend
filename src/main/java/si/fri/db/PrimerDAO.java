package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.*;

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

    public Freezer findFreezer(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM Freezer f WHERE f.freezer = :freezerName";
        Query query = session.createQuery(hql);
        query.setParameter("freezerName", name);
        List results = query.list();
        Freezer freezer = (Freezer) results.get(0);
        return freezer;
    }

    public Organism findOrganism(String name) {
        Session session = currentSession();
        String hql = "SELECT o FROM Organism o WHERE o.organism = :organismName";
        Query query = session.createQuery(hql);
        query.setParameter("organismName", name);
        List results = query.list();
        Organism organism = (Organism) results.get(0);
        return organism;
    }

    public Drawer findDrawer(String name) {
        Session session = currentSession();
        String hql = "SELECT d FROM Drawer d WHERE d.drawer = :drawerName";
        Query query = session.createQuery(hql);
        query.setParameter("drawerName", name);
        List results = query.list();
        Drawer drawer = (Drawer) results.get(0);
        return drawer;
    }

    public Box findBox(String name) {
        Session session = currentSession();
        String hql = "SELECT b FROM Box b WHERE b.box = :boxName";
        Query query = session.createQuery(hql);
        query.setParameter("boxName", name);
        List results = query.list();
        Box box = (Box) results.get(0);
        return box;
    }

    public PositionInReference findPositionInReference(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PositionInReference p WHERE p.positionInReference = :positionInReferenceName";
        Query query = session.createQuery(hql);
        query.setParameter("positionInReferenceName", name);
        List results = query.list();
        PositionInReference positionInReference = (PositionInReference) results.get(0);
        return positionInReference;
    }

    public PurificationMethod findPurificationMethod(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PurificationMethod p WHERE p.purificationMethod = :purificationMethodName";
        Query query = session.createQuery(hql);
        query.setParameter("purificationMethodName", name);
        List results = query.list();
        PurificationMethod purificationMethod = (PurificationMethod) results.get(0);
        return purificationMethod;
    }

    public HumanGenomBuild findHumanGenomBuild(String name) {
        Session session = currentSession();
        String hql = "SELECT h FROM HumanGenomBuild h WHERE h.humanGenomBuild = :humanGenomBuildName";
        Query query = session.createQuery(hql);
        query.setParameter("humanGenomBuildName", name);
        List results = query.list();
        HumanGenomBuild humanGenomBuild = (HumanGenomBuild) results.get(0);
        return humanGenomBuild;
    }

    public Formulation findFormulation(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM Formulation f WHERE f.formulation = :formulationName";
        Query query = session.createQuery(hql);
        query.setParameter("formulationName", name);
        List results = query.list();
        Formulation formulation = (Formulation) results.get(0);
        return formulation;
    }

    public TypeOfPrimer findTypeOfPrimer(String name) {
        Session session = currentSession();
        String hql = "SELECT t FROM TypeOfPrimer t WHERE t.typeOfPrimer = :typeOfPrimerName";
        Query query = session.createQuery(hql);
        query.setParameter("typeOfPrimerName", name);
        List results = query.list();
        TypeOfPrimer typeOfPrimer = (TypeOfPrimer) results.get(0);
        return typeOfPrimer;
    }

    public PrimerApplication findPrimerApplication(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PrimerApplication p WHERE p.primerApplication = :primerApplicationName";
        Query query = session.createQuery(hql);
        query.setParameter("primerApplicationName", name);
        List results = query.list();
        PrimerApplication primerApplication = (PrimerApplication) results.get(0);
        return primerApplication;
    }

    public FiveModification findFiveModification(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM FiveModification f WHERE f.fiveModification = :fiveModificationName";
        Query query = session.createQuery(hql);
        query.setParameter("fiveModificationName", name);
        List results = query.list();
        FiveModification fiveModification = (FiveModification) results.get(0);
        return fiveModification;
    }

    public ThreeModification findThreeModification(String name) {
        Session session = currentSession();
        String hql = "SELECT t FROM ThreeModification t WHERE t.threeModification = :threeModificationName";
        Query query = session.createQuery(hql);
        query.setParameter("threeModificationName", name);
        List results = query.list();
        ThreeModification threeModification = (ThreeModification) results.get(0);
        return threeModification;
    }

    public Project findProject(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM Project p WHERE p.project = :projectName";
        Query query = session.createQuery(hql);
        query.setParameter("projectName", name);
        List results = query.list();
        Project project = (Project) results.get(0);
        return project;
    }

    public Supplier findSupplier(String name) {
        Session session = currentSession();
        String hql = "SELECT s FROM Supplier s WHERE s.supplier = :supplierName";
        Query query = session.createQuery(hql);
        query.setParameter("supplierName", name);
        List results = query.list();
        Supplier supplier = (Supplier) results.get(0);
        return supplier;
    }

    public Manufacturer findManufacturer(String name) {
        Session session = currentSession();
        String hql = "SELECT m FROM Manufacturer m WHERE m.manufacturer = :manufacturerName";
        Query query = session.createQuery(hql);
        query.setParameter("manufacturerName", name);
        List results = query.list();
        Manufacturer manufacturer = (Manufacturer) results.get(0);
        return manufacturer;
    }
}
