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
        Primer p = persist(primer);
        p.generateName();
        return p;
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
        return (Freezer) results.get(0);
    }

    public Organism findOrganism(String name) {
        Session session = currentSession();
        String hql = "SELECT o FROM Organism o WHERE o.organism = :organismName";
        Query query = session.createQuery(hql);
        query.setParameter("organismName", name);
        List results = query.list();
        return (Organism) results.get(0);
    }

    public Drawer findDrawer(String name) {
        Session session = currentSession();
        String hql = "SELECT d FROM Drawer d WHERE d.drawer = :drawerName";
        Query query = session.createQuery(hql);
        query.setParameter("drawerName", name);
        List results = query.list();
        return (Drawer) results.get(0);
    }

    public Box findBox(String name) {
        Session session = currentSession();
        String hql = "SELECT b FROM Box b WHERE b.box = :boxName";
        Query query = session.createQuery(hql);
        query.setParameter("boxName", name);
        List results = query.list();
        return (Box) results.get(0);
    }

    public PositionInReference findPositionInReference(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PositionInReference p WHERE p.positionInReference = :positionInReferenceName";
        Query query = session.createQuery(hql);
        query.setParameter("positionInReferenceName", name);
        List results = query.list();
        return (PositionInReference) results.get(0);
    }

    public PurificationMethod findPurificationMethod(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PurificationMethod p WHERE p.purificationMethod = :purificationMethodName";
        Query query = session.createQuery(hql);
        query.setParameter("purificationMethodName", name);
        List results = query.list();
        return (PurificationMethod) results.get(0);
    }

    public HumanGenomBuild findHumanGenomBuild(String name) {
        Session session = currentSession();
        String hql = "SELECT h FROM HumanGenomBuild h WHERE h.humanGenomBuild = :humanGenomBuildName";
        Query query = session.createQuery(hql);
        query.setParameter("humanGenomBuildName", name);
        List results = query.list();
        return (HumanGenomBuild) results.get(0);
    }

    public Formulation findFormulation(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM Formulation f WHERE f.formulation = :formulationName";
        Query query = session.createQuery(hql);
        query.setParameter("formulationName", name);
        List results = query.list();
        return (Formulation) results.get(0);
    }

    public TypeOfPrimer findTypeOfPrimer(String name) {
        Session session = currentSession();
        String hql = "SELECT t FROM TypeOfPrimer t WHERE t.typeOfPrimer = :typeOfPrimerName";
        Query query = session.createQuery(hql);
        query.setParameter("typeOfPrimerName", name);
        List results = query.list();
        return (TypeOfPrimer) results.get(0);
    }

    public PrimerApplication findPrimerApplication(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM PrimerApplication p WHERE p.primerApplication = :primerApplicationName";
        Query query = session.createQuery(hql);
        query.setParameter("primerApplicationName", name);
        List results = query.list();
        return (PrimerApplication) results.get(0);
    }

    public FiveModification findFiveModification(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM FiveModification f WHERE f.fiveModification = :fiveModificationName";
        Query query = session.createQuery(hql);
        query.setParameter("fiveModificationName", name);
        List results = query.list();
        return (FiveModification) results.get(0);
    }

    public ThreeModification findThreeModification(String name) {
        Session session = currentSession();
        String hql = "SELECT t FROM ThreeModification t WHERE t.threeModification = :threeModificationName";
        Query query = session.createQuery(hql);
        query.setParameter("threeModificationName", name);
        List results = query.list();
        return (ThreeModification) results.get(0);
    }

    public Project findProject(String name) {
        Session session = currentSession();
        String hql = "SELECT p FROM Project p WHERE p.project = :projectName";
        Query query = session.createQuery(hql);
        query.setParameter("projectName", name);
        List results = query.list();
        return (Project) results.get(0);
    }

    public Supplier findSupplier(String name) {
        Session session = currentSession();
        String hql = "SELECT s FROM Supplier s WHERE s.supplier = :supplierName";
        Query query = session.createQuery(hql);
        query.setParameter("supplierName", name);
        List results = query.list();
        return (Supplier) results.get(0);
    }

    public Manufacturer findManufacturer(String name) {
        Session session = currentSession();
        String hql = "SELECT m FROM Manufacturer m WHERE m.manufacturer = :manufacturerName";
        Query query = session.createQuery(hql);
        query.setParameter("manufacturerName", name);
        List results = query.list();
        return (Manufacturer) results.get(0);
    }

    public ThreeQuencher findThreeQuencher(String name) {
        Session session = currentSession();
        String hql = "SELECT t FROM ThreeQuencher t WHERE t.threeQuencher = :threeQuencherName";
        Query query = session.createQuery(hql);
        query.setParameter("threeQuencherName", name);
        List results = query.list();
        return (ThreeQuencher) results.get(0);
    }

    public FiveDye findFiveDye(String name) {
        Session session = currentSession();
        String hql = "SELECT f FROM FiveDye f WHERE f.fiveDye = :fiveDyeName";
        Query query = session.createQuery(hql);
        query.setParameter("fiveDyeName", name);
        List results = query.list();
        return (FiveDye) results.get(0);
    }

    public Freezer addFreezer(String name) {
        Session session = currentSession();
        Freezer freezer = new Freezer(name);
        session.save(freezer);
        session.getTransaction().commit();
        return freezer;
    }

    public Organism addOrganism(String name) {
        Session session = currentSession();
        Organism organism = new Organism(name);
        session.save(organism);
        session.getTransaction().commit();
        return organism;
    }

    public Drawer addDrawer(String name) {
        Session session = currentSession();
        Drawer drawer = new Drawer(name);
        session.save(drawer);
        session.getTransaction().commit();
        return drawer;
    }

    public Box addBox(String name) {
        Session session = currentSession();
        Box box = new Box(name);
        session.save(box);
        session.getTransaction().commit();
        return box;
    }

    public PositionInReference addPositionInReference(String name) {
        Session session = currentSession();
        PositionInReference positionInReference = new PositionInReference(name);
        session.save(positionInReference);
        session.getTransaction().commit();
        return positionInReference;
    }

    public PurificationMethod addPurificationMethod(String name) {
        Session session = currentSession();
        PurificationMethod purificationMethod = new PurificationMethod(name);
        session.save(purificationMethod);
        session.getTransaction().commit();
        return purificationMethod;
    }

    public HumanGenomBuild addHumanGenomBuild(String name) {
        Session session = currentSession();
        HumanGenomBuild humanGenomBuild = new HumanGenomBuild(name);
        session.save(humanGenomBuild);
        session.getTransaction().commit();
        return humanGenomBuild;
    }

    public Formulation addFormulation(String name) {
        Session session = currentSession();
        Formulation formulation = new Formulation(name);
        session.save(formulation);
        session.getTransaction().commit();
        return formulation;
    }

    public TypeOfPrimer addTypeOfPrimer(String name) {
        Session session = currentSession();
        TypeOfPrimer typeOfPrimer = new TypeOfPrimer(name);
        session.save(typeOfPrimer);
        session.getTransaction().commit();
        return typeOfPrimer;
    }

    public PrimerApplication addPrimerApplication(String name) {
        Session session = currentSession();
        PrimerApplication primerApplication = new PrimerApplication(name);
        session.save(primerApplication);
        session.getTransaction().commit();
        return primerApplication;
    }

    public FiveModification addFiveModification(String name) {
        Session session = currentSession();
        FiveModification fiveModification = new FiveModification(name);
        session.save(fiveModification);
        session.getTransaction().commit();
        return fiveModification;
    }

    public ThreeModification addThreeModification(String name) {
        Session session = currentSession();
        ThreeModification threeModification = new ThreeModification(name);
        session.save(threeModification);
        session.getTransaction().commit();
        return threeModification;
    }

    public Project addProject(String name) {
        Session session = currentSession();
        Project project = new Project(name);
        session.save(project);
        session.getTransaction().commit();
        return project;
    }

    public Supplier addSupplier(String name) {
        Session session = currentSession();
        Supplier supplier = new Supplier(name);
        session.save(supplier);
        session.getTransaction().commit();
        return supplier;
    }

    public Manufacturer addManufacturer(String name) {
        Session session = currentSession();
        Manufacturer manufacturer = new Manufacturer(name);
        session.save(manufacturer);
        session.getTransaction().commit();
        return manufacturer;
    }

    public ThreeQuencher addThreeQuencher(String name) {
        Session session = currentSession();
        ThreeQuencher threeQuencher = new ThreeQuencher(name);
        session.save(threeQuencher);
        session.getTransaction().commit();
        return threeQuencher;
    }

    public FiveDye addFiveDye(String name) {
        Session session = currentSession();
        FiveDye fiveDye = new FiveDye(name);
        session.save(fiveDye);
        session.getTransaction().commit();
        return fiveDye;
    }

    public void deletePrimer(long id) {
        Session session = currentSession();
        String hql = "DELETE FROM Primer p WHERE p.id = :primerId";
        Query query = session.createQuery(hql);
        query.setParameter("primerId", id).executeUpdate();
    }
}
