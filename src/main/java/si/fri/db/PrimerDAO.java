package si.fri.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import si.fri.core.*;
import si.fri.core.primer_enums.*;
import si.fri.resources.PrimerResource;

import java.util.List;
import java.util.Optional;

public class PrimerDAO extends AbstractDAO<Primer> {

    private final HistoryDAO hDao;
    private final PrimerForeignTablesDAO pftDao;

    public PrimerDAO(SessionFactory factory, HistoryDAO hDao, PrimerForeignTablesDAO pftDao) {
        super(factory);
        this.hDao = hDao;
        this.pftDao = pftDao;
    }

    public Optional<Primer> findById(Long id) {
        Primer primer = get(id);
        if (primer != null) {
            if (primer.isDeleted()) {
                primer = null;
            }
        }
        return Optional.ofNullable(primer);
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

    public Primer create(Primer primer, User user) {
        Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession();
        session.beginTransaction();
        session.saveOrUpdate(primer);
        primer.generateName();
        session.getTransaction().commit();
        session.close();
        return primer;
    }

    public void update(long id, PrimerResource.PrimerJSON primerJson, User user) {
        Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession();

        Primer primer = get(id);

        primer.setAmountAvailable(primerJson.amountAvailable);
        primer.setAmountAvailablePacks(primerJson.amountAvailablePacks);
        primer.setAmountAvailablePackType(AmountAvailablePackType.fromString(primerJson.amountAvailablePackType));
        primer.setAnalysis(primerJson.analysis);
        primer.setApplicationComment(primerJson.applicationComment);
        primer.setAssayId(primerJson.assayId);
        primer.setBox(pftDao.findBox(primerJson.box));
        primer.setCheckSpecifityInBlast(primerJson.checkSpecifityInBlast);
        primer.setComment(primerJson.comment);
        primer.setConcentrationOrdered(primerJson.concentrationOrdered);
        primer.setConcentrationOrderedUnit(ConcentrationOrderedUnit.fromString(primerJson.concentrationOrderedUnit));
        primer.setDate(primerJson.date);
        primer.setDesignerDatabase(pftDao.findDesignerDatabase(primerJson.designerDatabase));
        primer.setDesignerName(pftDao.findDesignerName(primerJson.designerName));
        primer.setDesignerPublication(pftDao.findDesignerPublication(primerJson.designerPublication));
        primer.setDocument(primerJson.document);
        primer.setDrawer(pftDao.findDrawer(primerJson.drawer));
        primer.setFiveDye(pftDao.findFiveDye(primerJson.fiveDye));
        primer.setFiveModification(pftDao.findFiveModification(primerJson.fiveModification));
        primer.setFormulation(pftDao.findFormulation(primerJson.formulation));
        primer.setFreezer(pftDao.findFreezer(primerJson.freezer));
        primer.setGCPercent(primerJson.gcpercent);
        primer.setGen(pftDao.findGen(primerJson.gen));
        primer.setHumanGenomBuild(pftDao.findHumanGenomBuild(primerJson.humanGenomBuild));
        primer.setLengthOfAmplicone(primerJson.lengthOfAmplicone);
        primer.setManufacturer(pftDao.findManufacturer(primerJson.manufacturer));
        primer.setName(primerJson.name);
        primer.setNcbiGenId(pftDao.findNcbiGenId(primerJson.ncbiGenId));
        primer.setOptimalTOfAnnealing(primerJson.optimalTOfAnnealing);
        primer.setOrderStatus(OrderStatus.fromString(primerJson.orderStatus));
        primer.setOrganism(pftDao.findOrganism(primerJson.organism));
        primer.setOrientation(Orientation.fromString(primerJson.orientation));
        primer.setPositionInReference(pftDao.findPositionInReference(primerJson.positionInReference));
        primer.setPrimerApplication(pftDao.findPrimerApplication(primerJson.primerApplication));
        primer.setProject(pftDao.findProject(primerJson.project));
        primer.setPurificationMethod(pftDao.findPurificationMethod(primerJson.purificationMethod));
        primer.setSequence(primerJson.sequence);
        primer.setSize(Size.fromString(primerJson.size));
        primer.setSondaSequence(primerJson.sondaSequence);
        primer.setStoringT(primerJson.storingT);
        primer.setSupplier(pftDao.findSupplier(primerJson.supplier));
        primer.setThreeModification(pftDao.findThreeModification(primerJson.threeModification));
        primer.setThreeQuencher(pftDao.findThreeQuencher(primerJson.threeQuencher));
        primer.setTm(primerJson.tm);
        primer.setTypeOfPrimer(pftDao.findTypeOfPrimer(primerJson.typeOfPrimer));
        primer.generateName();
        primer.generateLength();

        session.beginTransaction();

        session.merge(primer);

        session.getTransaction().commit();
        session.close();
    }

    public void updateAmountCommentAnalysisi(long id, double amountAvailable, String comment, String analysis, User user) {
        Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession();

        Primer primer = get(id);

        primer.setAmountAvailable(amountAvailable);
        primer.setComment(comment);
        primer.setAnalysis(analysis);

        session.beginTransaction();

        session.merge(primer);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(long id, User user) {
        Session session = super.currentSession().getSessionFactory().withOptions().interceptor(new AuditInterceptor(user, hDao)).openSession();
        Query query = session.createQuery("SELECT p FROM Primer p WHERE p.id = :pId");
        query.setParameter("pId", id);
        Primer primer = (Primer) query.list().get(0);
        session.beginTransaction();
        session.remove(primer);
        session.getTransaction().commit();
        session.close();
    }

    public Primer copy(long id, User user) {
        Primer p = get(id);
        Primer pCopy = new Primer(p.getName(), p.getSequence(), p.getOrientation(), p.getFreezer(), p.getDrawer(),
            p.getBox(), p.getPositionInReference(), p.getTm(), p.getOptimalTOfAnnealing(), p.getPurificationMethod(),
            p.getAmountAvailable(), p.getAmountAvailablePacks(), p.getAmountAvailablePackType(), p.getLengthOfAmplicone(),
            p.getStoringT(), p.getGCPercent(), p.getOrganism(), p.getGen(), p.getNcbiGenId(), p.getHumanGenomBuild(),
            p.getFormulation(), p.getTypeOfPrimer(), p.getSondaSequence(), p.getAssayId(), p.getSize(), p.getPrimerApplication(),
            p.getApplicationComment(), p.getFiveModification(), p.getThreeModification(), p.getConcentrationOrdered(),
            p.getConcentrationOrderedUnit(), p.isCheckSpecifityInBlast(), p.getDesignerName(), p.getDesignerPublication(),
            p.getDesignerDatabase(), p.getProject(), p.getSupplier(), p.getManufacturer(), p.getComment(), p.getDocument(),
            p.getAnalysis(), p.getOrderStatus(), p.getThreeQuencher(), p.getFiveDye(), p.getDate(), user);
        return create(pCopy, user);
    }
}
