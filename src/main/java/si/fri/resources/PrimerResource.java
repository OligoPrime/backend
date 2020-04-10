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


        Primer primer = new Primer("testname", "testsequence", Orientation.REVERSE, calculateLength("testsequence"), dao.findFreezer("freezer2"),
                dao.findDrawer("drawer3"), dao.findBox("box5"), dao.findPositionInReference("5'-promotor"), 65.2, 22.1, dao.findPurificationMethod("Cartridge"),
                42.3, 30, AmountAvailablePackSize.PLATE, newDate, 30,
                42.2, 34.3, dao.findOrganism("Homo sapiens"), "gen123", "ncbigenid123", dao.findHumanGenomBuild("NCBI Build 36.1"),
                dao.findFormulation("Resuspended in TRIS"), dao.findTypeOfPrimer("M13/pUC primer"), "sondaseq123", "assayid123", Size.M, dao.findPrimerApplication("Sanger Sequencing"),
                "application comment 123", generateName(Orientation.REVERSE, dao.findOrganism("Homo sapiens"), "EcoRI"),
                dao.findFiveModification("Aldehyde Modifier"), dao.findThreeModification("Biotin TEG"), 40,
                ConcentrationOrderedUnit.NANOMOL, true, "designer123", "publication123",
                "database123", dao.findProject("project3"), "miha", dao.findSupplier("Omega"), dao.findManufacturer("BioSearch"),
                "komentar", "dokument link", "analiza 123", null);
        return dao.create(primer);
    }

    @GET
    @UnitOfWork
    public List<Primer> getAll(){
        return dao.findAll();
    }

    private static String generateName(Orientation orientation, Organism organism, String ncbiGenId) {

        String delimiter = "-";
        String generatedName = "";

        if (orientation == Orientation.REVERSE) {
            generatedName += "R";
        }
        else if (orientation == Orientation.FORWARD) {
            generatedName += "F";
        }
        else if (orientation == null) {
            generatedName += "X";
        }
        else {
            return null;
        }
        generatedName += delimiter;

        String organismName = organism.getOrganism();
        if (organismName.equals("Escherichia coli TG1")) {
            generatedName += "G1";
        }
        else if (organismName.equals("Escherichia coli WK6")) {
            generatedName += "K6";
        }
        else if (organismName.equals("Homo sapiens")) {
            generatedName += "HS";
        }
        else if (organismName.equals("Mus musculus")) {
            generatedName += "MM";
        }
        else if (organismName.equals("Rattus norvegicus domestica")) {
            generatedName += "RN";
        }
        else {
            generatedName += "XX";
        }
        generatedName += delimiter;

        generatedName += ncbiGenId;
        generatedName += delimiter;

        // TODO: implement number assignment
        generatedName += "00";

        return generatedName;
    }

    private static int calculateLength(String sequence) {
        return sequence.length();
    }
}