package si.fri.resources;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.core.Primer;
import si.fri.core.Roles;
import si.fri.core.User;
import si.fri.core.primer_enums.*;
import si.fri.db.PrimerDAO;
import si.fri.db.PrimerForeignTablesDAO;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/csv")
@Produces(MediaType.APPLICATION_JSON)
public class CsvResource {

    private final PrimerDAO pDao;
    private final PrimerForeignTablesDAO pftDao;

    public CsvResource(PrimerDAO pDao, PrimerForeignTablesDAO pftDao) {
        this.pDao = pDao;
        this.pftDao = pftDao;
    }

    @POST
    @Path("/import")
    @RolesAllowed({Roles.RESEARCHER, Roles.ADMIN})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @UnitOfWork
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @Auth User user) {

        String fileName = fileDetail.getFileName();

        // check that file extension is csv
        if (!FilenameUtils.getExtension(fileName).equalsIgnoreCase("csv")) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        }

        CsvToBean<PrimerCSV> csvToBean = new CsvToBeanBuilder(new InputStreamReader(uploadedInputStream))
                .withType(PrimerCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(',')
                .build();

        for (PrimerCSV p : csvToBean.parse()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = format.parse(p.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Primer primer = new Primer(p.name, p.sequence, Orientation.fromString(p.orientation), pftDao.findFreezer(p.freezer),
                    pftDao.findDrawer(p.drawer), pftDao.findBox(p.box), pftDao.findPositionInReference(p.positionInReference), p.Tm,
                    p.optimalTOfAnnealing, pftDao.findPurificationMethod(p.purificationMethod), p.amountAvailableMikroL,
                    p.amountAvailablePacks, AmountAvailablePackType.fromString(p.amountAvailablePackType), p.lengthOfAmplicone,
                    p.storingT, p.GCPercent, pftDao.findOrganism(p.organism), pftDao.findGen(p.gen), pftDao.findNcbiGenId(p.ncbiGenId),
                    pftDao.findHumanGenomBuild(p.humanGenomBuild), pftDao.findFormulation(p.formulation), pftDao.findTypeOfPrimer(p.typeOfPrimer),
                    p.sondaSequence, p.assayId, Size.fromString(p.size), pftDao.findPrimerApplication(p.primerApplication),
                    p.applicationComment, pftDao.findFiveModification(p.fiveModification), pftDao.findThreeModification(p.threeModification),
                    p.concentrationOrdered, ConcentrationOrderedUnit.fromString(p.concentrationOrderedUnit), p.checkSpecifityInBlast,
                    pftDao.findDesignerName(p.designerName), pftDao.findDesignerPublication(p.designerPublication),
                    pftDao.findDesignerDatabase(p.designerDatabase), pftDao.findProject(p.project), pftDao.findSupplier(p.supplier),
                    pftDao.findManufacturer(p.manufacturer), p.comment, p.document, p.analysis, OrderStatus.fromString(p.orderStatus),
                    pftDao.findThreeQuencher(p.threeQuencher), pftDao.findFiveDye(p.fiveDye), date, user);
            pDao.create(primer, user);
        }

        return Response.ok("Successfully uploaded primers from CSV file.").build();
    }

    @GET
    @Path("/sample")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile() {
        File sampleCsv = new File(ClassLoader.getSystemResource("sample.csv").getFile());
        return Response.ok(sampleCsv, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + sampleCsv.getName() + "\"")
                .build();
    }

    public static class PrimerCSV {
        @CsvBindByName(column = "Name of Primer", required = true)
        public String name;
        @CsvBindByName(column = "Sequence")
        public String sequence;
        @CsvBindByName(column = "Orientation")
        public String orientation;
        @CsvBindByName(column = "Freezer", required = true)
        public String freezer;
        @CsvBindByName(column = "Drawer", required = true)
        public String drawer;
        @CsvBindByName(column = "Box", required = true)
        public String box;
        @CsvBindByName(column = "Position in Reference", required = true)
        public String positionInReference;
        @CsvBindByName(column = "Tm (degree Celsius)")
        public Double Tm;
        @CsvBindByName(column = "Optimal T of Annealing (degree Celsius)")
        public Double optimalTOfAnnealing;
        @CsvBindByName(column = "Purification Method", required = true)
        public String purificationMethod;
        @CsvBindByName(column = "Amount Available (microliter)")
        public Double amountAvailableMikroL;
        @CsvBindByName(column = "Amount Available (Packs)")
        public Integer amountAvailablePacks;
        @CsvBindByName(column = "Amount Available (Pack Type)")
        public String amountAvailablePackType;
        @CsvBindByName(column = "Date")
        public String date;
        @CsvBindByName(column = "Length of Amplicone")
        public Integer lengthOfAmplicone;
        @CsvBindByName(column = "Storing T (degree Celsius)")
        public String storingT;
        @CsvBindByName(column = "GC (%)")
        public Double GCPercent;
        @CsvBindByName(column = "Organism", required = true)
        public String organism;
        @CsvBindByName(column = "Gen", required = true)
        public String gen;
        @CsvBindByName(column = "Ncbi Gen ID")
        public String ncbiGenId;
        @CsvBindByName(column = "Human Genom Build")
        public String humanGenomBuild;
        @CsvBindByName(column = "Formulation", required = true)
        public String formulation;
        @CsvBindByName(column = "Type of Primer", required = true)
        public String typeOfPrimer;
        @CsvBindByName(column = "Sonda Sequence")
        public String sondaSequence;
        @CsvBindByName(column = "Assay ID")
        public String assayId;
        @CsvBindByName(column = "Size")
        public String size;
        @CsvBindByName(column = "Application", required = true)
        public String primerApplication;
        @CsvBindByName(column = "Application Comment")
        public String applicationComment;
        @CsvBindByName(column = "5' Modification", required = true)
        public String fiveModification;
        @CsvBindByName(column = "3' Modification", required = true)
        public String threeModification;
        @CsvBindByName(column = "Concentration Ordered")
        public Double concentrationOrdered;
        @CsvBindByName(column = "Concentration Ordered Unit")
        public String concentrationOrderedUnit;
        @CsvBindByName(column = "Did You Check Specifity in Blast?")
        public Boolean checkSpecifityInBlast;
        @CsvBindByName(column = "Designer Name")
        public String designerName;
        @CsvBindByName(column = "Designer Publication")
        public String designerPublication;
        @CsvBindByName(column = "Designer Database")
        public String designerDatabase;
        @CsvBindByName(column = "Project")
        public String project;
        @CsvBindByName(column = "Supplier")
        public String supplier;
        @CsvBindByName(column = "Manufacturer")
        public String manufacturer;
        @CsvBindByName(column = "Comment")
        public String comment;
        @CsvBindByName(column = "Document")
        public String document;
        @CsvBindByName(column = "Analysis")
        public String analysis;
        @CsvBindByName(column = "Order Status")
        public String orderStatus;
        @CsvBindByName(column = "3' Quencher")
        public String threeQuencher;
        @CsvBindByName(column = "5' Dye")
        public String fiveDye;
    }
}
