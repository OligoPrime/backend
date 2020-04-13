package si.fri.resources;

import io.dropwizard.hibernate.UnitOfWork;
import si.fri.core.*;
import si.fri.db.PrimerDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("/primers")
@Produces(MediaType.APPLICATION_JSON)
public class PrimerResource {

    private final PrimerDAO dao;

    public PrimerResource(PrimerDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/fill")
    @UnitOfWork
    public Primer fillPrimers() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date newDate = null;
        try {
            newDate = format.parse("31/12/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Primer primer = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, newDate, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", OrderStatus.RECEIVED, null);

        primer = dao.create(primer);

        Primer primer2 = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, newDate, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123",  dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", OrderStatus.RECEIVED, null);
        primer2 = dao.create(primer2);

        Primer primer3 = new Primer("testname", "testsequence", Orientation.REVERSE, dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, newDate, 30,
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

    @GET
    @UnitOfWork
    public List<Primer> getAll(){
        return dao.findAll();
    }
}