package si.fri.resources;

import com.fasterxml.jackson.annotation.*;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.*;
import si.fri.core.primer_enums.*;
import si.fri.core.primer_foreign_tables.*;
import si.fri.db.PrimerDAO;

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

    private final PrimerDAO dao;

    public PrimerResource(PrimerDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/fill")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Primer fillPrimers() {

        for (int i = 0; i < 600; i++) {
            dao.create(new Primer("COVID-19", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer3"),
                    dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                    42.3, 30, AmountAvailablePackType.PLATE, 30,
                    "42.2", 34.3, dao.findOrganism("Homo sapiens"), dao.findGen("gen1"), dao.findNcbiGenId("ncbiGenId1"), dao.findHumanGenomBuild("NCBI Build 36.1"),
                    dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                    "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40.0,
                    ConcentrationOrderedUnit.NANOMOL, true, dao.findDesignerName("designerName1"), dao.findDesignerPublication("designerPublication1"),
                    dao.findDesignerDatabase("designerDatabase1"), dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                    "Tega je pa kr velik", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                    dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null));
        }

        Primer primer = new Primer("SuperPrimer3000", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, dao.findOrganism("Homo sapiens"), dao.findGen("gen2"), dao.findNcbiGenId("ncbiGenId2"), dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, dao.findDesignerName("designerName2"), dao.findDesignerPublication("designerPublication2"),
                dao.findDesignerDatabase("designerDatabase2"), dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "Lačen sem", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null);
        dao.create(primer);

        Primer primer2 = new Primer("MegaBestPrimer1Million", "tcidf", Orientation.REVERSE, dao.findFreezer("freezer1"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, dao.findOrganism("Homo sapiens"), dao.findGen("gen3"), dao.findNcbiGenId("ncbiGenId3"), dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, dao.findDesignerName("designerName3"), dao.findDesignerPublication("designerPublication3"),
                dao.findDesignerDatabase("designerDatabase3"), dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "Tega sm dobil za rojstni dan", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null);
        dao.create(primer2);

        Primer primer3 = new Primer("PleaseUseME", "banana", Orientation.REVERSE, dao.findFreezer("freezer3"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackType.PLATE, 30,
                "42.2", 34.3, dao.findOrganism("Homo sapiens"), dao.findGen("gen4"), dao.findNcbiGenId("ncbiGenId4"), dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40.0,
                ConcentrationOrderedUnit.NANOMOL, true, dao.findDesignerName("designerName4"), dao.findDesignerPublication("designerPublication4"),
                dao.findDesignerDatabase("designerDatabase4"), dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "Rad imam maline!", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null);
        dao.create(primer3);

        primer.pairWith(primer2);
        primer3.pairWith(primer);
        primer3.pairWith(primer2);

        return primer3;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Primer addPrimer(PrimerJSON p) {
        Primer primer = new Primer(p.name, p.sequence, Orientation.fromString(p.orientation), dao.findFreezer(p.freezer),
                dao.findDrawer(p.drawer), dao.findBox(p.box), dao.findPositionInReference(p.positionInReference), p.Tm,
                p.optimalTOfAnnealing, dao.findPurificationMethod(p.purificationMethod), p.amountAvailableMikroL,
                p.amountAvailablePacks, AmountAvailablePackType.fromString(p.amountAvailablePackType), p.lengthOfAmplicone,
                p.storingT, p.GCPercent, dao.findOrganism(p.organism), dao.findGen(p.gen), dao.findNcbiGenId(p.ncbiGenId),
                dao.findHumanGenomBuild(p.humanGenomBuild), dao.findFormulation(p.formulation), dao.findTypeOfPrimer(p.typeOfPrimer),
                p.sondaSequence, p.assayId, Size.fromString(p.size), dao.findPrimerApplication(p.primerApplication),
                p.applicationComment, dao.findFiveModification(p.fiveModification), dao.findThreeModification(p.threeModification),
                p.concentrationOrdered, ConcentrationOrderedUnit.fromString(p.concentrationOrderedUnit), p.checkSpecifityInBlast,
                dao.findDesignerName(p.designerName), dao.findDesignerPublication(p.designerPublication),
                dao.findDesignerDatabase(p.designerDatabase), dao.findProject(p.project), dao.findSupplier(p.supplier),
                dao.findManufacturer(p.manufacturer), p.comment, p.document, p.analysis, OrderStatus.fromString(p.orderStatus),
                dao.findThreeQuencher(p.threeQuencher), dao.findFiveDye(p.fiveDye), p.date, null);
        primer = dao.create(primer);
        return primer;
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Response deletePrimer(long id) {
        dao.deletePrimer(id);
        return Response.ok("Successfully deleted primer.").build();
    }

    @POST
    @Path("/pair")
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Response pairPrimers(long[] idArr) {
        if (idArr[0] == idArr[1]) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot pair primer with itself.").build();
        }

        Optional<Primer> primer1 = dao.findById(idArr[0]);
        Optional<Primer> primer2 = dao.findById(idArr[1]);

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
        Optional<Primer> primer = dao.findById(id);
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
                return dao.findAllBox();
            case "designerdatabase":
                return dao.findAllDesignerDatabase();
            case "designername":
                return dao.findAllDesignerName();
            case "designerpublication":
                return dao.findAllDesignerPublication();
            case "drawer":
                return dao.findAllDrawer();
            case "fivedye":
                return dao.findAllFiveDye();
            case "fivemodification":
                return dao.findAllFiveModification();
            case "formulation":
                return dao.findAllFormulation();
            case "freezer":
                return dao.findAllFreezer();
            case "gen":
                return dao.findAllGen();
            case "humangenombuild":
                return dao.findAllHumanGenomBuild();
            case "ncbigenid":
                return dao.findAllNcbiGenId();
            case "organism":
                return dao.findAllOrganism();
            case "positioninreference":
                return dao.findAllPositionInReference();
            case "primerapplication":
                return dao.findAllPrimerApplication();
            case "project":
                return dao.findAllProject();
            case "purificationmethod":
                return dao.findAllPurificationMethod();
            case "supplier":
                return dao.findAllSupplier();
            case "threemodification":
                return dao.findAllThreeModification();
            case "threequencher":
                return dao.findAllThreeQuencher();
            case "typeofprimer":
                return dao.findAllTypeOfPrimer();
        }
        return null;
    }

    @GET
    @Path("/get-all-foreign-tables")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public PrimerForeignTableJSON getAllForeignTables() {
        return new PrimerForeignTableJSON(dao);
    }

    @POST
    @Path("/add-formulation")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Formulation addFormulation(String name) {
        return dao.addFormulation(name);
    }


    @POST
    @Path("/add-purificationmethod")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public PurificationMethod addPurificationMethod(String name) {
        return dao.addPurificationMethod(name);
    }

    @POST
    @Path("/add-primerapplication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public PrimerApplication addPrimerApplication(String name) {
        return dao.addPrimerApplication(name);
    }

    @POST
    @Path("/add-fivemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public FiveModification addFiveModification(String name) {
        return dao.addFiveModification(name);
    }

    @POST
    @Path("/add-threemodification")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public ThreeModification addThreeModification(String name) {
        return dao.addThreeModification(name);
    }

    @POST
    @Path("/add-project")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Project addProject(String name) {
        return dao.addProject(name);
    }

    @POST
    @Path("/add-supplier")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Supplier addSupplier(String name) {
        return dao.addSupplier(name);
    }

    @POST
    @Path("/add-manufacturer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Manufacturer addManufacturer(String name) {
        return dao.addManufacturer(name);
    }

    @POST
    @Path("/add-freezer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Freezer addFreezer(String name) {
        return dao.addFreezer(name);
    }

    @POST
    @Path("/add-box")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Box addBox(String name) {
        return dao.addBox(name);
    }

    @POST
    @Path("/add-drawer")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Drawer addDrawer(String name) {
        return dao.addDrawer(name);
    }

    @POST
    @Path("/add-threequencher")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public ThreeQuencher addThreeQuencher(String name) {
        return dao.addThreeQuencher(name);
    }

    @POST
    @Path("/add-fivedye")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public FiveDye addFiveDye(String name) {
        return dao.addFiveDye(name);
    }

    @POST
    @Path("/add-organism")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Organism addOrganism(String name) {
        return dao.addOrganism(name);
    }

    @POST
    @Path("/add-gen")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public Gen addGen(String name) {
        return dao.addGen(name);
    }

    @POST
    @Path("/add-ncbigenid")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public NcbiGenId addNcbiGenId(String name) {
        return dao.addNcbiGenId(name);
    }

    @POST
    @Path("/add-designername")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerName addDesignerName(String name) {
        return dao.addDesignerName(name);
    }

    @POST
    @Path("/add-designerpublication")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerPublication addDesignerPublication(String name) {
        return dao.addDesignerPublication(name);
    }

    @POST
    @Path("/add-designerdatabase")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.RESEARCHER})
    public DesignerDatabase addDesignerDatabase(String name) {
        return dao.addDesignerDatabase(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed({Roles.ADMIN, Roles.TECHNICIAN, Roles.RESEARCHER, Roles.GUEST})
    public List<Primer> getAll(){
        return dao.findAll();
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
        public Double amountAvailableMikroL;
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

        public PrimerForeignTableJSON(PrimerDAO dao) {
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