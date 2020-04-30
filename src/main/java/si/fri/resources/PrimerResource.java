package si.fri.resources;

import com.fasterxml.jackson.annotation.*;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.*;
import si.fri.db.PrimerDAO;

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
                    42.3, 30, AmountAvailablePackSize.PLATE, 30,
                    42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                    dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                    "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                    ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                    "database123", dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                    "Tega je pa kr velik", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                    dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null));
        }

        Primer primer = new Primer("SuperPrimer3000", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "Lačen sem", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null);
        dao.create(primer);

        Primer primer2 = new Primer("MegaBestPrimer1Million", "tcidf", Orientation.REVERSE, dao.findFreezer("freezer1"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "Tega sm dobil za rojstni dan", "dokument link", "analiza 123", OrderStatus.RECEIVED,
                dao.findThreeQuencher("TAMRA"), dao.findFiveDye("NED"), new Date(), null);
        dao.create(primer2);

        Primer primer3 = new Primer("PleaseUseME", "banana", Orientation.REVERSE, dao.findFreezer("freezer3"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
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
    public Primer addPrimer(PrimerJSON p) {
        Primer primer = new Primer(p.name, p.sequence, Orientation.fromString(p.orientation), dao.findFreezer(p.freezer),
                dao.findDrawer(p.drawer), dao.findBox(p.box), dao.findPositionInReference(p.positionInReference), p.Tm,
                p.optimalTOfAnnealing, dao.findPurificationMethod(p.purificationMethod), p.amountAvailableMikroL,
                p.amountAvailablePacks, AmountAvailablePackSize.fromString(p.amountAvailablePackSize), p.lengthOfAmplicone,
                p.storingT, p.GCPercent, dao.findOrganism(p.organism), p.gen, p.ncbiGenId, dao.findHumanGenomBuild(p.humanGenomBuild),
                dao.findFormulation(p.formulation), dao.findTypeOfPrimer(p.typeOfPrimer), p.sondaSequence, p.assayId,
                Size.fromString(p.size), dao.findPrimerApplication(p.primerApplication), p.applicationComment,
                dao.findFiveModification(p.fiveModification), dao.findThreeModification(p.threeModification), p.concentrationOrdered,
                ConcentrationOrderedUnit.fromString(p.concentrationOrderedUnit), p.checkSpecifityInBlast, p.designerName,
                p.designerPublication, p.designerDatabase, dao.findProject(p.project), dao.findSupplier(p.supplier),
                dao.findManufacturer(p.manufacturer), p.comment, p.document, p.analysis, OrderStatus.fromString(p.orderStatus),
                dao.findThreeQuencher(p.threeQuencher), dao.findFiveDye(p.fiveDye), new Date(), null);
        primer = dao.create(primer);
        return primer;
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    public Response deletePrimer(long id) {
        dao.deletePrimer(id);
        return Response.ok("Successfully deleted primer.").build();
    }

    @POST
    @Path("/pair")
    @UnitOfWork
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
    public Primer getPrimer(@PathParam("id") long id) {
        Optional<Primer> primer = dao.findById(id);
        return primer.orElse(null);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
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
        public int length;
        @JsonProperty
        public String freezer;
        @JsonProperty
        public String drawer;
        @JsonProperty
        public String box;
        @JsonProperty
        public String positionInReference;
        @JsonProperty
        public double Tm;
        @JsonProperty
        public double optimalTOfAnnealing;
        @JsonProperty
        public String purificationMethod;
        @JsonProperty
        public double amountAvailableMikroL;
        @JsonProperty
        public int amountAvailablePacks;
        @JsonProperty
        public String amountAvailablePackSize;
        @JsonProperty
        public int lengthOfAmplicone;
        @JsonProperty
        public double storingT;
        @JsonProperty
        public double GCPercent;
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
        public int concentrationOrdered;
        @JsonProperty
        public String concentrationOrderedUnit;
        @JsonProperty
        public boolean checkSpecifityInBlast;
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
}