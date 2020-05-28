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
import java.util.stream.Collectors;

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
                    pftDao.findThreeQuencher("TAMRA"), pftDao.findFiveDye("NED"), new Date(), user), user);
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
        pDao.create(primer, user);

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
        pDao.create(primer2, user);

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
        pDao.create(primer3, user);

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
        primer = pDao.create(primer, user);
        return primer;
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Response deletePrimer(@Auth User user, long id) {
        pDao.delete(id, user);
        return Response.ok("Successfully deleted primer.").build();
    }

    @POST
    @Path("/update")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Primer updatePrimer(@Auth User user, @QueryParam("id") long id, PrimerJSON primerJson) {
        pDao.update(id, primerJson, user);
        return pDao.findById(id).get();
    }

    @POST
    @Path("/pair")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
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
        return Response.ok("Successfully paired primers.").entity(idArr).build();
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

    // TODO: maybe remove methods for adding if not needed

    @POST
    @Path("/add-formulation")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Formulation addFormulation(String name) {
        return pftDao.addFormulation(name);
    }


    @POST
    @Path("/add-purificationmethod")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public PurificationMethod addPurificationMethod(String name) {
        return pftDao.addPurificationMethod(name);
    }

    @POST
    @Path("/add-primerapplication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public PrimerApplication addPrimerApplication(String name) {
        return pftDao.addPrimerApplication(name);
    }

    @POST
    @Path("/add-fivemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public FiveModification addFiveModification(String name) {
        return pftDao.addFiveModification(name);
    }

    @POST
    @Path("/add-threemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public ThreeModification addThreeModification(String name) {
        return pftDao.addThreeModification(name);
    }

    @POST
    @Path("/add-project")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Project addProject(String name) {
        return pftDao.addProject(name);
    }

    @POST
    @Path("/add-supplier")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Supplier addSupplier(String name) {
        return pftDao.addSupplier(name);
    }

    @POST
    @Path("/add-manufacturer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Manufacturer addManufacturer(String name) {
        return pftDao.addManufacturer(name);
    }

    @POST
    @Path("/add-freezer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Freezer addFreezer(String name) {
        return pftDao.addFreezer(name);
    }

    @POST
    @Path("/add-box")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Box addBox(String name) {
        return pftDao.addBox(name);
    }

    @POST
    @Path("/add-drawer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Drawer addDrawer(String name) {
        return pftDao.addDrawer(name);
    }

    @POST
    @Path("/add-threequencher")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public ThreeQuencher addThreeQuencher(String name) {
        return pftDao.addThreeQuencher(name);
    }

    @POST
    @Path("/add-fivedye")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public FiveDye addFiveDye(String name) {
        return pftDao.addFiveDye(name);
    }

    @POST
    @Path("/add-organism")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Organism addOrganism(String name) {
        return pftDao.addOrganism(name);
    }

    @POST
    @Path("/add-gen")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public Gen addGen(String name) {
        return pftDao.addGen(name);
    }

    @POST
    @Path("/add-ncbigenid")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public NcbiGenId addNcbiGenId(String name) {
        return pftDao.addNcbiGenId(name);
    }

    @POST
    @Path("/add-designername")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public DesignerName addDesignerName(String name) {
        return pftDao.addDesignerName(name);
    }

    @POST
    @Path("/add-designerpublication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    public DesignerPublication addDesignerPublication(String name) {
        return pftDao.addDesignerPublication(name);
    }

    @POST
    @Path("/add-designerdatabase")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
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
        public List<String> box;
        @JsonProperty
        public List<String> designerDatabase;
        @JsonProperty
        public List<String> designerName;
        @JsonProperty
        public List<String> designerPublication;
        @JsonProperty
        public List<String> drawer;
        @JsonProperty
        public List<String> fiveDye;
        @JsonProperty
        public List<String> fiveModification;
        @JsonProperty
        public List<String> formulation;
        @JsonProperty
        public List<String> freezer;
        @JsonProperty
        public List<String> gen;
        @JsonProperty
        public List<String> humanGenomBuild;
        @JsonProperty
        public List<String> manufacturer;
        @JsonProperty
        public List<String> ncbiGenId;
        @JsonProperty
        public List<String> organism;
        @JsonProperty
        public List<String> positionInReference;
        @JsonProperty
        public List<String> primerApplication;
        @JsonProperty
        public List<String> project;
        @JsonProperty
        public List<String> purificationMethod;
        @JsonProperty
        public List<String> supplier;
        @JsonProperty
        public List<String> threeModification;
        @JsonProperty
        public List<String> threeQuencher;
        @JsonProperty
        public List<String> typeOfPrimer;

        public PrimerForeignTableJSON(PrimerForeignTablesDAO dao) {
            box = dao.findAllBox().stream().map(Box::getBox).collect(Collectors.toList());
            designerDatabase = dao.findAllDesignerDatabase().stream().map(DesignerDatabase::getDesignerDatabase).collect(Collectors.toList());
            designerName = dao.findAllDesignerName().stream().map(DesignerName::getDesignerName).collect(Collectors.toList());
            designerPublication = dao.findAllDesignerPublication().stream().map(DesignerPublication::getDesignerPublication).collect(Collectors.toList());
            drawer = dao.findAllDrawer().stream().map(Drawer::getDrawer).collect(Collectors.toList());
            fiveDye = dao.findAllFiveDye().stream().map(FiveDye::getFiveDye).collect(Collectors.toList());
            fiveModification = dao.findAllFiveModification().stream().map(FiveModification::getFiveModification).collect(Collectors.toList());
            formulation = dao.findAllFormulation().stream().map(Formulation::getFormulation).collect(Collectors.toList());
            freezer = dao.findAllFreezer().stream().map(Freezer::getFreezer).collect(Collectors.toList());
            gen = dao.findAllGen().stream().map(Gen::getGen).collect(Collectors.toList());
            humanGenomBuild = dao.findAllHumanGenomBuild().stream().map(HumanGenomBuild::getHumanGenomBuild).collect(Collectors.toList());
            manufacturer = dao.findAllManufacturer().stream().map(Manufacturer::getManufacturer).collect(Collectors.toList());
            ncbiGenId = dao.findAllNcbiGenId().stream().map(NcbiGenId::getNcbiGenId).collect(Collectors.toList());
            organism = dao.findAllOrganism().stream().map(Organism::getOrganism).collect(Collectors.toList());
            positionInReference = dao.findAllPositionInReference().stream().map(PositionInReference::getPositionInReference).collect(Collectors.toList());
            primerApplication = dao.findAllPrimerApplication().stream().map(PrimerApplication::getPrimerApplication).collect(Collectors.toList());
            project = dao.findAllProject().stream().map(Project::getProject).collect(Collectors.toList());
            purificationMethod = dao.findAllPurificationMethod().stream().map(PurificationMethod::getPurificationMethod).collect(Collectors.toList());
            supplier = dao.findAllSupplier().stream().map(Supplier::getSupplier).collect(Collectors.toList());
            threeModification = dao.findAllThreeModification().stream().map(ThreeModification::getThreeModification).collect(Collectors.toList());
            threeQuencher = dao.findAllThreeQuencher().stream().map(ThreeQuencher::getThreeQuencher).collect(Collectors.toList());
            typeOfPrimer = dao.findAllTypeOfPrimer().stream().map(TypeOfPrimer::getTypeOfPrimer).collect(Collectors.toList());
        }
    }
}