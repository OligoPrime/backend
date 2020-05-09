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

    public Gen findGen(String name) {
        Session session = currentSession();
        String hql = "SELECT g FROM Gen g WHERE g.gen = :genName";
        Query query = session.createQuery(hql);
        query.setParameter("genName", name);
        List results = query.list();
        return (Gen) results.get(0);
    }

    public NcbiGenId findNcbiGenId(String name) {
        Session session = currentSession();
        String hql = "SELECT n FROM NcbiGenId n WHERE n.ncbiGenId = :ncbiGenIdName";
        Query query = session.createQuery(hql);
        query.setParameter("ncbiGenIdName", name);
        List results = query.list();
        return (NcbiGenId) results.get(0);
    }

    public DesignerName findDesignerName(String name) {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerName d WHERE d.designerName = :designerNameName";
        Query query = session.createQuery(hql);
        query.setParameter("designerNameName", name);
        List results = query.list();
        return (DesignerName) results.get(0);
    }

    public DesignerPublication findDesignerPublication(String name) {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerPublication d WHERE d.designerPublication = :designerPublicationName";
        Query query = session.createQuery(hql);
        query.setParameter("designerPublicationName", name);
        List results = query.list();
        return (DesignerPublication) results.get(0);
    }

    public DesignerDatabase findDesignerDatabase(String name) {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerDatabase d WHERE d.designerDatabase = :designerDatabaseName";
        Query query = session.createQuery(hql);
        query.setParameter("designerDatabaseName", name);
        List results = query.list();
        return (DesignerDatabase) results.get(0);
    }

    public List<Box> findAllBox() {
        Session session = currentSession();
        String hql = "SELECT b FROM Box b";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<DesignerDatabase> findAllDesignerDatabase() {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerDatabase d";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<DesignerName> findAllDesignerName() {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerName d";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<DesignerPublication> findAllDesignerPublication() {
        Session session = currentSession();
        String hql = "SELECT d FROM DesignerPublication d";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Drawer> findAllDrawer() {
        Session session = currentSession();
        String hql = "SELECT d FROM Drawer d";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<FiveDye> findAllFiveDye() {
        Session session = currentSession();
        String hql = "SELECT f FROM FiveDye f";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<FiveModification> findAllFiveModification() {
        Session session = currentSession();
        String hql = "SELECT f FROM FiveModification f";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Formulation> findAllFormulation() {
        Session session = currentSession();
        String hql = "SELECT f FROM Formulation f";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Freezer> findAllFreezer() {
        Session session = currentSession();
        String hql = "SELECT f FROM Freezer f";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Gen> findAllGen() {
        Session session = currentSession();
        String hql = "SELECT g FROM Gen g";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<HumanGenomBuild> findAllHumanGenomBuild() {
        Session session = currentSession();
        String hql = "SELECT h FROM HumanGenomBuild h";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Manufacturer> findAllManufacturer() {
        Session session = currentSession();
        String hql = "SELECT m FROM Manufacturer m";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<NcbiGenId> findAllNcbiGenId() {
        Session session = currentSession();
        String hql = "SELECT n FROM NcbiGenId n";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Organism> findAllOrganism() {
        Session session = currentSession();
        String hql = "SELECT o FROM Organism o";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<PositionInReference> findAllPositionInReference() {
        Session session = currentSession();
        String hql = "SELECT p FROM PositionInReference p";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<PrimerApplication> findAllPrimerApplication() {
        Session session = currentSession();
        String hql = "SELECT p FROM PrimerApplication p";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Project> findAllProject() {
        Session session = currentSession();
        String hql = "SELECT p FROM Project p";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<PurificationMethod> findAllPurificationMethod() {
        Session session = currentSession();
        String hql = "SELECT p FROM PurificationMethod p";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<Supplier> findAllSupplier() {
        Session session = currentSession();
        String hql = "SELECT s FROM Supplier s";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<ThreeModification> findAllThreeModification() {
        Session session = currentSession();
        String hql = "SELECT t FROM ThreeModification t";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<ThreeQuencher> findAllThreeQuencher() {
        Session session = currentSession();
        String hql = "SELECT t FROM ThreeQuencher t";
        Query query = session.createQuery(hql);
        return query.list();
    }

    public List<TypeOfPrimer> findAllTypeOfPrimer() {
        Session session = currentSession();
        String hql = "SELECT t FROM TypeOfPrimer t";
        Query query = session.createQuery(hql);
        return query.list();
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

    public Gen addGen(String name) {
        Session session = currentSession();
        Gen gen = new Gen(name);
        session.save(gen);
        session.getTransaction().commit();
        return gen;
    }

    public NcbiGenId addNcbiGenId(String name) {
        Session session = currentSession();
        NcbiGenId ncbiGenId = new NcbiGenId(name);
        session.save(ncbiGenId);
        session.getTransaction().commit();
        return ncbiGenId;
    }

    public DesignerName addDesignerName(String name) {
        Session session = currentSession();
        DesignerName designerName = new DesignerName(name);
        session.save(designerName);
        session.getTransaction().commit();
        return designerName;
    }

    public DesignerPublication addDesignerPublication(String name) {
        Session session = currentSession();
        DesignerPublication designerPublication = new DesignerPublication(name);
        session.save(designerPublication);
        session.getTransaction().commit();
        return designerPublication;
    }

    public DesignerDatabase addDesignerDatabase(String name) {
        Session session = currentSession();
        DesignerDatabase designerDatabase = new DesignerDatabase(name);
        session.save(designerDatabase);
        session.getTransaction().commit();
        return designerDatabase;
    }

    public void deletePrimer(long id) {
        Session session = currentSession();
        String hql = "DELETE FROM Primer p WHERE p.id = :primerId";
        Query query = session.createQuery(hql);
        query.setParameter("primerId", id).executeUpdate();
    }
}
