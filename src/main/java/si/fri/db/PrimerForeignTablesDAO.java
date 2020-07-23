package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import si.fri.core.Primer;
import si.fri.core.primer_foreign_tables.*;

import java.util.List;

public class PrimerForeignTablesDAO extends AbstractDAO<Primer> {

    private final SessionFactory sessionFactory;

    public PrimerForeignTablesDAO(SessionFactory factory) {
        super(factory);
        this.sessionFactory = factory;
    }


    public Freezer findFreezer(String name) {
        String hql = "SELECT f FROM Freezer f WHERE f.freezer = :freezerName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("freezerName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addFreezer(name);
        } else {
            return (Freezer) results.get(0);
        }
    }

    public Organism findOrganism(String name) {
        String hql = "SELECT o FROM Organism o WHERE o.organism = :organismName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("organismName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addOrganism(name);
        } else {
            return (Organism) results.get(0);
        }
    }

    public Drawer findDrawer(String name) {
        String hql = "SELECT d FROM Drawer d WHERE d.drawer = :drawerName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("drawerName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addDrawer(name);
        } else {
            return (Drawer) results.get(0);
        }
    }

    public Box findBox(String name) {
        String hql = "SELECT b FROM Box b WHERE b.box = :boxName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("boxName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addBox(name);
        } else {
            return (Box) results.get(0);
        }
    }

    public PositionInReference findPositionInReference(String name) {
        String hql = "SELECT p FROM PositionInReference p WHERE p.positionInReference = :positionInReferenceName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("positionInReferenceName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addPositionInReference(name);
        } else {
            return (PositionInReference) results.get(0);
        }
    }

    public PurificationMethod findPurificationMethod(String name) {
        String hql = "SELECT p FROM PurificationMethod p WHERE p.purificationMethod = :purificationMethodName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("purificationMethodName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addPurificationMethod(name);
        } else {
            return (PurificationMethod) results.get(0);
        }
    }

    public HumanGenomBuild findHumanGenomBuild(String name) {
        String hql = "SELECT h FROM HumanGenomBuild h WHERE h.humanGenomBuild = :humanGenomBuildName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("humanGenomBuildName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addHumanGenomBuild(name);
        } else {
            return (HumanGenomBuild) results.get(0);
        }
    }

    public Formulation findFormulation(String name) {
        String hql = "SELECT f FROM Formulation f WHERE f.formulation = :formulationName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("formulationName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addFormulation(name);
        } else {
            return (Formulation) results.get(0);
        }
    }

    public TypeOfPrimer findTypeOfPrimer(String name) {
        String hql = "SELECT t FROM TypeOfPrimer t WHERE t.typeOfPrimer = :typeOfPrimerName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("typeOfPrimerName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addTypeOfPrimer(name);
        } else {
            return (TypeOfPrimer) results.get(0);
        }
    }

    public PrimerApplication findPrimerApplication(String name) {
        String hql = "SELECT p FROM PrimerApplication p WHERE p.primerApplication = :primerApplicationName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("primerApplicationName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addPrimerApplication(name);
        } else {
            return (PrimerApplication) results.get(0);
        }
    }

    public FiveModification findFiveModification(String name) {
        String hql = "SELECT f FROM FiveModification f WHERE f.fiveModification = :fiveModificationName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("fiveModificationName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addFiveModification(name);
        } else {
            return (FiveModification) results.get(0);
        }
    }

    public ThreeModification findThreeModification(String name) {
        String hql = "SELECT t FROM ThreeModification t WHERE t.threeModification = :threeModificationName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("threeModificationName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addThreeModification(name);
        } else {
            return (ThreeModification) results.get(0);
        }
    }

    public Project findProject(String name) {
        String hql = "SELECT p FROM Project p WHERE p.project = :projectName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("projectName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addProject(name);
        } else {
            return (Project) results.get(0);
        }
    }

    public Supplier findSupplier(String name) {
        String hql = "SELECT s FROM Supplier s WHERE s.supplier = :supplierName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("supplierName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addSupplier(name);
        } else {
            return (Supplier) results.get(0);
        }
    }

    public Manufacturer findManufacturer(String name) {
        String hql = "SELECT m FROM Manufacturer m WHERE m.manufacturer = :manufacturerName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("manufacturerName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addManufacturer(name);
        } else {
            return (Manufacturer) results.get(0);
        }
    }

    public ThreeQuencher findThreeQuencher(String name) {
        String hql = "SELECT t FROM ThreeQuencher t WHERE t.threeQuencher = :threeQuencherName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("threeQuencherName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addThreeQuencher(name);
        } else {
            return (ThreeQuencher) results.get(0);
        }
    }

    public FiveDye findFiveDye(String name) {
        String hql = "SELECT f FROM FiveDye f WHERE f.fiveDye = :fiveDyeName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("fiveDyeName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addFiveDye(name);
        } else {
            return (FiveDye) results.get(0);
        }
    }

    public Gen findGen(String name) {
        String hql = "SELECT g FROM Gen g WHERE g.gen = :genName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("genName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addGen(name);
        } else {
            return (Gen) results.get(0);
        }
    }

    public NcbiGenId findNcbiGenId(String name) {
        String hql = "SELECT n FROM NcbiGenId n WHERE n.ncbiGenId = :ncbiGenIdName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("ncbiGenIdName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addNcbiGenId(name);
        } else {
            return (NcbiGenId) results.get(0);
        }
    }

    public DesignerName findDesignerName(String name) {
        String hql = "SELECT d FROM DesignerName d WHERE d.designerName = :designerNameName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("designerNameName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addDesignerName(name);
        } else {
            return (DesignerName) results.get(0);
        }
    }

    public DesignerPublication findDesignerPublication(String name) {
        String hql = "SELECT d FROM DesignerPublication d WHERE d.designerPublication = :designerPublicationName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("designerPublicationName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addDesignerPublication(name);
        } else {
            return (DesignerPublication) results.get(0);
        }
    }

    public DesignerDatabase findDesignerDatabase(String name) {
        String hql = "SELECT d FROM DesignerDatabase d WHERE d.designerDatabase = :designerDatabaseName";
        Query query = currentSession().createQuery(hql);
        query.setParameter("designerDatabaseName", name);
        List results = query.list();
        if (results == null || results.isEmpty()) {
            return addDesignerDatabase(name);
        } else {
            return (DesignerDatabase) results.get(0);
        }
    }

    public List<Box> findAllBox() {
        String hql = "SELECT b FROM Box b";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<DesignerDatabase> findAllDesignerDatabase() {
        String hql = "SELECT d FROM DesignerDatabase d";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<DesignerName> findAllDesignerName() {
        String hql = "SELECT d FROM DesignerName d";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<DesignerPublication> findAllDesignerPublication() {
        String hql = "SELECT d FROM DesignerPublication d";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Drawer> findAllDrawer() {
        String hql = "SELECT d FROM Drawer d";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<FiveDye> findAllFiveDye() {
        String hql = "SELECT f FROM FiveDye f";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<FiveModification> findAllFiveModification() {
        String hql = "SELECT f FROM FiveModification f";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Formulation> findAllFormulation() {
        String hql = "SELECT f FROM Formulation f";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Freezer> findAllFreezer() {
        String hql = "SELECT f FROM Freezer f";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Gen> findAllGen() {
        String hql = "SELECT g FROM Gen g";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<HumanGenomBuild> findAllHumanGenomBuild() {
        String hql = "SELECT h FROM HumanGenomBuild h";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Manufacturer> findAllManufacturer() {
        String hql = "SELECT m FROM Manufacturer m";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<NcbiGenId> findAllNcbiGenId() {
        String hql = "SELECT n FROM NcbiGenId n";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Organism> findAllOrganism() {
        String hql = "SELECT o FROM Organism o";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<PositionInReference> findAllPositionInReference() {
        String hql = "SELECT p FROM PositionInReference p";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<PrimerApplication> findAllPrimerApplication() {
        String hql = "SELECT p FROM PrimerApplication p";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Project> findAllProject() {
        String hql = "SELECT p FROM Project p";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<PurificationMethod> findAllPurificationMethod() {
        String hql = "SELECT p FROM PurificationMethod p";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<Supplier> findAllSupplier() {
        String hql = "SELECT s FROM Supplier s";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<ThreeModification> findAllThreeModification() {
        String hql = "SELECT t FROM ThreeModification t";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<ThreeQuencher> findAllThreeQuencher() {
        String hql = "SELECT t FROM ThreeQuencher t";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public List<TypeOfPrimer> findAllTypeOfPrimer() {
        String hql = "SELECT t FROM TypeOfPrimer t";
        Query query = currentSession().createQuery(hql);
        return query.list();
    }

    public Freezer addFreezer(String name) {
        Freezer freezer = new Freezer(name);
        currentSession().save(freezer);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return freezer;
    }

    public Organism addOrganism(String name) {
        Organism organism = new Organism(name);
        currentSession().save(organism);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return organism;
    }

    public Drawer addDrawer(String name) {
        Drawer drawer = new Drawer(name);
        currentSession().save(drawer);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return drawer;
    }

    public Box addBox(String name) {
        Box box = new Box(name);
        currentSession().save(box);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return box;
    }

    public PositionInReference addPositionInReference(String name) {
        PositionInReference positionInReference = new PositionInReference(name);
        currentSession().save(positionInReference);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return positionInReference;
    }

    public PurificationMethod addPurificationMethod(String name) {
        PurificationMethod purificationMethod = new PurificationMethod(name);
        currentSession().save(purificationMethod);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return purificationMethod;
    }

    public HumanGenomBuild addHumanGenomBuild(String name) {
        HumanGenomBuild humanGenomBuild = new HumanGenomBuild(name);
        currentSession().save(humanGenomBuild);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return humanGenomBuild;
    }

    public Formulation addFormulation(String name) {
        Formulation formulation = new Formulation(name);
        currentSession().save(formulation);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return formulation;
    }

    public TypeOfPrimer addTypeOfPrimer(String name) {
        TypeOfPrimer typeOfPrimer = new TypeOfPrimer(name);
        currentSession().save(typeOfPrimer);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return typeOfPrimer;
    }

    public PrimerApplication addPrimerApplication(String name) {
        PrimerApplication primerApplication = new PrimerApplication(name);
        currentSession().save(primerApplication);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return primerApplication;
    }

    public FiveModification addFiveModification(String name) {
        FiveModification fiveModification = new FiveModification(name);
        currentSession().save(fiveModification);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return fiveModification;
    }

    public ThreeModification addThreeModification(String name) {
        ThreeModification threeModification = new ThreeModification(name);
        currentSession().save(threeModification);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return threeModification;
    }

    public Project addProject(String name) {
        Project project = new Project(name);
        currentSession().save(project);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return project;
    }

    public Supplier addSupplier(String name) {
        Supplier supplier = new Supplier(name);
        currentSession().save(supplier);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return supplier;
    }

    public Manufacturer addManufacturer(String name) {
        Manufacturer manufacturer = new Manufacturer(name);
        currentSession().save(manufacturer);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return manufacturer;
    }

    public ThreeQuencher addThreeQuencher(String name) {
        ThreeQuencher threeQuencher = new ThreeQuencher(name);
        currentSession().save(threeQuencher);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return threeQuencher;
    }

    public FiveDye addFiveDye(String name) {
        FiveDye fiveDye = new FiveDye(name);
        currentSession().save(fiveDye);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return fiveDye;
    }

    public Gen addGen(String name) {
        Gen gen = new Gen(name);
        currentSession().save(gen);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return gen;
    }

    public NcbiGenId addNcbiGenId(String name) {
        NcbiGenId ncbiGenId = new NcbiGenId(name);
        currentSession().save(ncbiGenId);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return ncbiGenId;
    }

    public DesignerName addDesignerName(String name) {
        DesignerName designerName = new DesignerName(name);
        currentSession().save(designerName);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return designerName;
    }

    public DesignerPublication addDesignerPublication(String name) {
        DesignerPublication designerPublication = new DesignerPublication(name);
        currentSession().save(designerPublication);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return designerPublication;
    }

    public DesignerDatabase addDesignerDatabase(String name) {
        DesignerDatabase designerDatabase = new DesignerDatabase(name);
        currentSession().save(designerDatabase);
        if (currentSession().getTransaction().getStatus().equals(TransactionStatus.ACTIVE))
            currentSession().getTransaction().commit();
        return designerDatabase;
    }
}
