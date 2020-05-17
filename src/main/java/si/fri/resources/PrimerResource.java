package si.fri.resources;

import com.fasterxml.jackson.annotation.*;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.*;
import si.fri.core.primer_enums.*;
import si.fri.core.primer_foreign_tables.*;
import si.fri.db.PrimerDAO;
import si.fri.db.PrimerForeignTablesDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Path("/primers")
@Produces(MediaType.APPLICATION_JSON)
public class PrimerResource {

    private final PrimerDAO pDao;
    private final PrimerForeignTablesDAO pftDao;

    public PrimerResource(PrimerDAO pDao, PrimerForeignTablesDAO pftDao) {
        this.pDao = pDao;
        this.pftDao = pftDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List<Primer> getAll(){
        return pDao.findAll();
    }

    @GET
    @Path("/wanted")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List<Primer> getWanted(){
        return pDao.findWanted();
    }

    @GET
    @Path("/ordered")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List<Primer> getOrdered(){
        return pDao.findOrdered();
    }

    @GET
    @Path("/received")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List<Primer> getReceived(){
        return pDao.findReceived();
    }

    @POST
    @Path("/fill")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN})
    public Primer fillPrimers(@Auth User user) {

        for (int i = 0; i < 600; i++) {
            pDao.create(new Primer("COVID-19", "testsequence", Orientation.REVERSE, pftDao.findFreezer("freezer3"),
                    pftDao.findDrawer("drawer3"), pftDao.findBox("box5"), pftDao.findPositionInReference("5'-promotor"), 65.2, 22.1, pftDao.findPurificationMethod("Cartridge"),
                    42.3, 30, AmountAvailablePackType.PLATE, 30,
                    "42.2", 34.3, pftDao.findOrganism("Homo sapiens"), pftDao.findGen("gen1"), pftDao.findNcbiGenId("ncbiGenId1"), pftDao.findHumanGenomBuild("NCBI Build 36.1"),
                    pftDao.findFormulation("Resuspended in TRIS"), pftDao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, pftDao.findPrimerApplication("Sanger Sequencing"),
                    "application comment 123",  pftDao.findFiveModification("Aldehyde Modifier"), pftDao.findThreeModification("Biotin TEG"), 40.0,
                    ConcentrationOrderedUnit.NANOMOL, true, pftDao.findDesignerName("designerName1"), pftDao.findDesignerPublication("designerPublication1"),
                    pftDao.findDesignerDatabase("designerDatabase1"), pftDao.findProject("project3"), pftDao.findSupplier("Omega"), pftDao.findManufacturer("BioSearch"),
                    "Tega je pa kr velik", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                    pftDao.findThreeQuencher("TAMRA"), pftDao.findFiveDye("NED"), new Date(), user));
        }

        Primer primer = new Primer("SuperPrimer3000", "testsequence", Orientation.REVERSE, pftDao.findFreezer("freezer2"),
                pftDao.findDrawer("drawer3"), pftDao.findBox("box5"), pftDao.findPositionInReference("5'-promotor"), 65.2, 22.1, pftDao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, pftDao.findOrganism("Homo sapiens"), pftDao.findGen("gen2"), pftDao.findNcbiGenId("ncbiGenId2"), pftDao.findHumanGenomBuild("NCBI Build 36.1"),
                pftDao.findFormulation("Resuspended in TRIS"), pftDao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, pftDao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  pftDao.findFiveModification("Aldehyde Modifier"), pftDao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, pftDao.findDesignerName("designerName2"), pftDao.findDesignerPublication("designerPublication2"),
                pftDao.findDesignerDatabase("designerDatabase2"), pftDao.findProject("project3"), pftDao.findSupplier("Omega"), pftDao.findManufacturer("BioSearch"),
                "Lačen sem", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                pftDao.findThreeQuencher("TAMRA"), pftDao.findFiveDye("NED"), new Date(), user);
        pDao.create(primer);

        Primer primer2 = new Primer("MegaBestPrimer1Million", "tcidf", Orientation.REVERSE, pftDao.findFreezer("freezer1"),
                pftDao.findDrawer("drawer3"), pftDao.findBox("box5"), pftDao.findPositionInReference("5'-promotor"), 65.2, 22.1, pftDao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, pftDao.findOrganism("Homo sapiens"), pftDao.findGen("gen3"), pftDao.findNcbiGenId("ncbiGenId3"), pftDao.findHumanGenomBuild("NCBI Build 36.1"),
                pftDao.findFormulation("Resuspended in TRIS"), pftDao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, pftDao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  pftDao.findFiveModification("Aldehyde Modifier"), pftDao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, pftDao.findDesignerName("designerName3"), pftDao.findDesignerPublication("designerPublication3"),
                pftDao.findDesignerDatabase("designerDatabase3"), pftDao.findProject("project3"), pftDao.findSupplier("Omega"), pftDao.findManufacturer("BioSearch"),
                "Tega sm dobil za rojstni dan", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                pftDao.findThreeQuencher("TAMRA"), pftDao.findFiveDye("NED"), new Date(), user);
        pDao.create(primer2);

        Primer primer3 = new Primer("PleaseUseME", "banana", Orientation.REVERSE, pftDao.findFreezer("freezer3"),
                pftDao.findDrawer("drawer3"), pftDao.findBox("box5"), pftDao.findPositionInReference("5'-promotor"), 65.2, 22.1, pftDao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, pftDao.findOrganism("Homo sapiens"), pftDao.findGen("gen4"), pftDao.findNcbiGenId("ncbiGenId4"), pftDao.findHumanGenomBuild("NCBI Build 36.1"),
                pftDao.findFormulation("Resuspended in TRIS"), pftDao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, pftDao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  pftDao.findFiveModification("Aldehyde Modifier"), pftDao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, pftDao.findDesignerName("designerName4"), pftDao.findDesignerPublication("designerPublication4"),
                pftDao.findDesignerDatabase("designerDatabase4"), pftDao.findProject("project3"), pftDao.findSupplier("Omega"), pftDao.findManufacturer("BioSearch"),
                "Rad imam maline!", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                pftDao.findThreeQuencher("TAMRA"), pftDao.findFiveDye("NED"), new Date(), user);
        pDao.create(primer3);

        primer.pairWith(primer2);
        primer3.pairWith(primer);
        primer3.pairWith(primer2);

        return primer3;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Primer addPrimer(@Auth User user, PrimerJSON p) {
        Primer primer = new Primer(p.name, p.sequence, Orientation.fromString(p.orientation), pftDao.findFreezer(p.freezer),
                pftDao.findDrawer(p.drawer), pftDao.findBox(p.box), pftDao.findPositionInReference(p.positionInReference), p.Tm,
                p.optimalTOfAnnealing, pftDao.findPurificationMethod(p.purificationMethod), p.amountAvailable,
                p.amountAvailablePacks, AmountAvailablePackType.fromString(p.amountAvailablePackType), p.lengthOfAmplicone,
                p.storingT, p.GCPercent, pftDao.findOrganism(p.organism), pftDao.findGen(p.gen), pftDao.findNcbiGenId(p.ncbiGenId),
                pftDao.findHumanGenomBuild(p.humanGenomBuild), pftDao.findFormulation(p.formulation), pftDao.findTypeOfPrimer(p.typeOfPrimer),
                p.sondaSequence, p.assayId, Size.fromString(p.size), pftDao.findPrimerApplication(p.primerApplication),
                p.applicationComment, pftDao.findFiveModification(p.fiveModification), pftDao.findThreeModification(p.threeModification),
                p.concentrationOrdered, ConcentrationOrderedUnit.fromString(p.concentrationOrderedUnit), p.checkSpecifityInBlast,
                pftDao.findDesignerName(p.designerName), pftDao.findDesignerPublication(p.designerPublication),
                pftDao.findDesignerDatabase(p.designerDatabase), pftDao.findProject(p.project), pftDao.findSupplier(p.supplier),
                pftDao.findManufacturer(p.manufacturer), p.comment, p.document, p.analysis, OrderStatus.fromString(p.orderStatus),
                pftDao.findThreeQuencher(p.threeQuencher), pftDao.findFiveDye(p.fiveDye), p.date, user);
        primer = pDao.create(primer);
        return primer;
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Response deletePrimer(long id) {
        pDao.deletePrimer(id);
        return Response.ok("Successfully deleted primer.").build();
    }

    @POST
    @Path("/update")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({Roles.RESEARCHER})
    public Primer updatePrimer(@QueryParam("id") long id, PrimerJSON primerJson) {
        Primer primer = pDao.findById(id).get();
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
        primer.setGCPercent(primerJson.GCPercent);
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
        primer.setTm(primerJson.Tm);
        primer.setTypeOfPrimer(pftDao.findTypeOfPrimer(primerJson.typeOfPrimer));
        return primer;
    }

    @POST
    @Path("/pair")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Response pairPrimers(long[] idArr) {
        if (idArr[0] == idArr[1]) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot pair primer with itself.").build();
        }

        Optional<Primer> primer1 = pDao.findById(idArr[0]);
        Optional<Primer> primer2 = pDao.findById(idArr[1]);

        if (!primer1.isPresent() || !primer2.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Couldn't find primers with specified id.").build();
        }

        primer1.get().pairWith(primer2.get());
        return Response.ok("Successfully paired primers.").build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public Primer getPrimer(@PathParam("id") long id) {
        Optional<Primer> primer = pDao.findById(id);
        return primer.orElse(null);
    }

    @GET
    @Path("/get-foreign-table")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List getForeignTable(@QueryParam("table") String table) {
        switch(table) {
            case "box":
                return pftDao.findAllBox();
            case "designerdatabase":
                return pftDao.findAllDesignerDatabase();
            case "designername":
                return pftDao.findAllDesignerName();
            case "designerpublication":
                return pftDao.findAllDesignerPublication();
            case "drawer":
                return pftDao.findAllDrawer();
            case "fivedye":
                return pftDao.findAllFiveDye();
            case "fivemodification":
                return pftDao.findAllFiveModification();
            case "formulation":
                return pftDao.findAllFormulation();
            case "freezer":
                return pftDao.findAllFreezer();
            case "gen":
                return pftDao.findAllGen();
            case "humangenombuild":
                return pftDao.findAllHumanGenomBuild();
            case "ncbigenid":
                return pftDao.findAllNcbiGenId();
            case "organism":
                return pftDao.findAllOrganism();
            case "positioninreference":
                return pftDao.findAllPositionInReference();
            case "primerapplication":
                return pftDao.findAllPrimerApplication();
            case "project":
                return pftDao.findAllProject();
            case "purificationmethod":
                return pftDao.findAllPurificationMethod();
            case "supplier":
                return pftDao.findAllSupplier();
            case "threemodification":
                return pftDao.findAllThreeModification();
            case "threequencher":
                return pftDao.findAllThreeQuencher();
            case "typeofprimer":
                return pftDao.findAllTypeOfPrimer();
        }
        return null;
    }

    @GET
    @Path("/get-all-foreign-tables")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public PrimerForeignTableJSON getAllForeignTables() {
        return new PrimerForeignTableJSON(pftDao);
    }

    @GET
    @Path("/primerjson-example")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public PrimerJSON getPrimerJSONExample() {
        Primer primer = getPrimer(1);
        PrimerJSON primerJson = new PrimerJSON();

        primerJson.amountAvailable = primer.getAmountAvailable();
        primerJson.amountAvailablePacks = primer.getAmountAvailablePacks();
        primerJson.amountAvailablePackType = primer.getAmountAvailablePackType().toString();
        primerJson.analysis = primer.getAnalysis();
        primerJson.applicationComment = primer.getApplicationComment();
        primerJson.assayId = primer.getAssayId();
        primerJson.box = primer.getBox().getBox();
        primerJson.checkSpecifityInBlast = primer.isCheckSpecifityInBlast();
        primerJson.comment = primer.getComment();
        primerJson.concentrationOrdered = primer.getConcentrationOrdered();
        primerJson.concentrationOrderedUnit = primer.getConcentrationOrderedUnit().toString();
        primerJson.date = primer.getDate();
        primerJson.designerDatabase = primer.getDesignerDatabase().getDesignerDatabase();
        primerJson.designerName = primer.getDesignerName().getDesignerName();
        primerJson.designerPublication = primer.getDesignerPublication().getDesignerPublication();
        primerJson.document = primer.getDocument();
        primerJson.drawer = primer.getDrawer().getDrawer();
        primerJson.fiveDye = primer.getFiveDye().getFiveDye();
        primerJson.fiveModification = primer.getFiveModification().getFiveModification();
        primerJson.formulation = primer.getFormulation().getFormulation();
        primerJson.freezer = primer.getFreezer().getFreezer();
        primerJson.GCPercent = primer.getGCPercent();
        primerJson.gen = primer.getGen().getGen();
        primerJson.humanGenomBuild = primer.getHumanGenomBuild().getHumanGenomBuild();
        primerJson.lengthOfAmplicone = primer.getLengthOfAmplicone();
        primerJson.manufacturer = primer.getManufacturer().getManufacturer();
        primerJson.name = primer.getName();
        primerJson.ncbiGenId = primer.getNcbiGenId().getNcbiGenId();
        primerJson.optimalTOfAnnealing = primer.getOptimalTOfAnnealing();
        primerJson.orderStatus = primer.getOrderStatus().toString();
        primerJson.organism = primer.getOrganism().getOrganism();
        primerJson.orientation = primer.getOrientation().toString();
        primerJson.positionInReference = primer.getPositionInReference().getPositionInReference();
        primerJson.primerApplication = primer.getPrimerApplication().getPrimerApplication();
        primerJson.project = primer.getProject().getProject();
        primerJson.purificationMethod = primer.getPurificationMethod().getPurificationMethod();
        primerJson.sequence = primer.getSequence();
        primerJson.size = primer.getSize().toString();
        primerJson.sondaSequence = primer.getSondaSequence();
        primerJson.storingT = primer.getStoringT();
        primerJson.supplier = primer.getSupplier().getSupplier();
        primerJson.threeModification = primer.getThreeModification().getThreeModification();
        primerJson.threeQuencher = primer.getThreeQuencher().getThreeQuencher();
        primerJson.Tm = primer.getTm();
        primerJson.typeOfPrimer = primer.getTypeOfPrimer().getTypeOfPrimer();

        return primerJson;
    }

    @POST
    @Path("/add-formulation")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Formulation addFormulation(String name) {
        return pftDao.addFormulation(name);
    }


    @POST
    @Path("/add-purificationmethod")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public PurificationMethod addPurificationMethod(String name) {
        return pftDao.addPurificationMethod(name);
    }

    @POST
    @Path("/add-primerapplication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public PrimerApplication addPrimerApplication(String name) {
        return pftDao.addPrimerApplication(name);
    }

    @POST
    @Path("/add-fivemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public FiveModification addFiveModification(String name) {
        return pftDao.addFiveModification(name);
    }

    @POST
    @Path("/add-threemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public ThreeModification addThreeModification(String name) {
        return pftDao.addThreeModification(name);
    }

    @POST
    @Path("/add-project")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Project addProject(String name) {
        return pftDao.addProject(name);
    }

    @POST
    @Path("/add-supplier")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Supplier addSupplier(String name) {
        return pftDao.addSupplier(name);
    }

    @POST
    @Path("/add-manufacturer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Manufacturer addManufacturer(String name) {
        return pftDao.addManufacturer(name);
    }

    @POST
    @Path("/add-freezer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Freezer addFreezer(String name) {
        return pftDao.addFreezer(name);
    }

    @POST
    @Path("/add-box")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Box addBox(String name) {
        return pftDao.addBox(name);
    }

    @POST
    @Path("/add-drawer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Drawer addDrawer(String name) {
        return pftDao.addDrawer(name);
    }

    @POST
    @Path("/add-threequencher")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public ThreeQuencher addThreeQuencher(String name) {
        return pftDao.addThreeQuencher(name);
    }

    @POST
    @Path("/add-fivedye")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public FiveDye addFiveDye(String name) {
        return pftDao.addFiveDye(name);
    }

    @POST
    @Path("/add-organism")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Organism addOrganism(String name) {
        return pftDao.addOrganism(name);
    }

    @POST
    @Path("/add-gen")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Gen addGen(String name) {
        return pftDao.addGen(name);
    }

    @POST
    @Path("/add-ncbigenid")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public NcbiGenId addNcbiGenId(String name) {
        return pftDao.addNcbiGenId(name);
    }

    @POST
    @Path("/add-designername")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerName addDesignerName(String name) {
        return pftDao.addDesignerName(name);
    }

    @POST
    @Path("/add-designerpublication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerPublication addDesignerPublication(String name) {
        return pftDao.addDesignerPublication(name);
    }

    @POST
    @Path("/add-designerdatabase")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerDatabase addDesignerDatabase(String name) {
        return pftDao.addDesignerDatabase(name);
    }

    public static class PrimerJSON implements Serializable {
        @JsonProperty
        public String name;
        @JsonProperty
        public String sequence;
        @JsonProperty
        public String orientation;
        @JsonProperty
        public String freezer;
        @JsonProperty
        public String drawer;
        @JsonProperty
        public String box;
        @JsonProperty
        public String positionInReference;
        @JsonProperty
        public Double Tm;
        @JsonProperty
        public Double optimalTOfAnnealing;
        @JsonProperty
        public String purificationMethod;
        @JsonProperty
        public Double amountAvailable;
        @JsonProperty
        public Integer amountAvailablePacks;
        @JsonProperty
        public String amountAvailablePackType;
        @JsonProperty
        public Date date;
        @JsonProperty
        public Integer lengthOfAmplicone;
        @JsonProperty
        public String storingT;
        @JsonProperty
        public Double GCPercent;
        @JsonProperty
        public String organism;
        @JsonProperty
        public String gen;
        @JsonProperty
        public String ncbiGenId;
        @JsonProperty
        public String humanGenomBuild;
        @JsonProperty
        public String formulation;
        @JsonProperty
        public String typeOfPrimer;
        @JsonProperty
        public String sondaSequence;
        @JsonProperty
        public String assayId;
        @JsonProperty
        public String size;
        @JsonProperty
        public String primerApplication;
        @JsonProperty
        public String applicationComment;
        @JsonProperty
        public String fiveModification;
        @JsonProperty
        public String threeModification;
        @JsonProperty
        public Double concentrationOrdered;
        @JsonProperty
        public String concentrationOrderedUnit;
        @JsonProperty
        public Boolean checkSpecifityInBlast;
        @JsonProperty
        public String designerName;
        @JsonProperty
        public String designerPublication;
        @JsonProperty
        public String designerDatabase;
        @JsonProperty
        public String project;
        @JsonProperty
        public String supplier;
        @JsonProperty
        public String manufacturer;
        @JsonProperty
        public String comment;
        @JsonProperty
        public String document;
        @JsonProperty
        public String analysis;
        @JsonProperty
        public String orderStatus;
        @JsonProperty
        public String threeQuencher;
        @JsonProperty
        public String fiveDye;
    }

    public static class PrimerForeignTableJSON {
        @JsonProperty
        public List<Box> box;
        @JsonProperty
        public List<DesignerDatabase> designerDatabase;
        @JsonProperty
        public List<DesignerName> designerName;
        @JsonProperty
        public List<DesignerPublication> designerPublication;
        @JsonProperty
        public List<Drawer> drawer;
        @JsonProperty
        public List<FiveDye> fiveDye;
        @JsonProperty
        public List<FiveModification> fiveModification;
        @JsonProperty
        public List<Formulation> formulation;
        @JsonProperty
        public List<Freezer> freezer;
        @JsonProperty
        public List<Gen> gen;
        @JsonProperty
        public List<HumanGenomBuild> humanGenomBuild;
        @JsonProperty
        public List<Manufacturer> manufacturer;
        @JsonProperty
        public List<NcbiGenId> ncbiGenId;
        @JsonProperty
        public List<Organism> organism;
        @JsonProperty
        public List<PositionInReference> positionInReference;
        @JsonProperty
        public List<PrimerApplication> primerApplication;
        @JsonProperty
        public List<Project> project;
        @JsonProperty
        public List<PurificationMethod> purificationMethod;
        @JsonProperty
        public List<Supplier> supplier;
        @JsonProperty
        public List<ThreeModification> threeModification;
        @JsonProperty
        public List<ThreeQuencher> threeQuencher;
        @JsonProperty
        public List<TypeOfPrimer> typeOfPrimer;

        public PrimerForeignTableJSON(PrimerForeignTablesDAO dao) {
            box = dao.findAllBox();
            designerDatabase = dao.findAllDesignerDatabase();
            designerName = dao.findAllDesignerName();
            designerPublication = dao.findAllDesignerPublication();
            drawer = dao.findAllDrawer();
            fiveDye = dao.findAllFiveDye();
            fiveModification = dao.findAllFiveModification();
            formulation = dao.findAllFormulation();
            freezer = dao.findAllFreezer();
            gen = dao.findAllGen();
            humanGenomBuild = dao.findAllHumanGenomBuild();
            manufacturer = dao.findAllManufacturer();
            ncbiGenId = dao.findAllNcbiGenId();
            organism = dao.findAllOrganism();
            positionInReference = dao.findAllPositionInReference();
            primerApplication = dao.findAllPrimerApplication();
            project = dao.findAllProject();
            purificationMethod = dao.findAllPurificationMethod();
            supplier = dao.findAllSupplier();
            threeModification = dao.findAllThreeModification();
            threeQuencher = dao.findAllThreeQuencher();
            typeOfPrimer = dao.findAllTypeOfPrimer();
        }
    }
}