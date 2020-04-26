package si.fri.resources;

import com.fasterxml.jackson.annotation.*;
import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.*;
import si.fri.db.PrimerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
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

        Primer primer = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", OrderStatus.RECEIVED, null);

        primer = dao.create(primer);

        Primer primer2 = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", OrderStatus.RECEIVED, null);
        primer2 = dao.create(primer2);

        Primer primer3 = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", OrderStatus.RECEIVED, null);
        primer3 = dao.create(primer3);

        primer.pairWith(primer2);
        primer3.pairWith(primer);
        primer3.pairWith(primer2);

        return primer3;
    }

    @POST
    @Path("/add")
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
                p.designerPublication, p.designerDatabase, dao.findProject(p.project), p.orderedBy, dao.findSupplier(p.supplier),
                dao.findManufacturer(p.manufacturer), p.comment, p.document, p.analysis, OrderStatus.fromString(p.orderStatus), null);
        primer = dao.create(primer);
        return primer;
    }

    @POST
    @Path("/delete")
    @UnitOfWork
    public String deletePrimer(long id) {
        dao.deletePrimer(id);
        return "Successfully deleted primer.";
    }

    @POST
    @Path("/pair")
    @UnitOfWork
    public String pairPrimers(long[] idArr) {
        if (idArr[0] == idArr[1]) {
            return "Cannot pair primer with itself.";
        }

        Optional<Primer> primer1 = dao.findById(idArr[0]);
        Optional<Primer> primer2 = dao.findById(idArr[1]);

        if (!primer1.isPresent() || !primer2.isPresent()) {
            return "Couldn't find primers with specified id.";
        }

        primer1.get().pairWith(primer2.get());
        return "Successfully paired primers.";
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
        public String orderedBy;
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
    }
}