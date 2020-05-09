package si.fri.resources;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.core.Primer;
import si.fri.core.primer_enums.*;
import si.fri.db.PrimerDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/csv")
@Produces(MediaType.APPLICATION_JSON)
public class CsvResource {

    private final PrimerDAO dao;

    public CsvResource(PrimerDAO dao) {
        this.dao = dao;
    }

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @UnitOfWork
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        String fileName = fileDetail.getFileName();

        // check that file extension is csv
        if (!FilenameUtils.getExtension(fileName).equalsIgnoreCase("csv")) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        }

        CsvToBean<PrimerCSV> csvToBean = new CsvToBeanBuilder(new InputStreamReader(uploadedInputStream))
                .withType(PrimerCSV.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();

        List<PrimerCSV> PrimerCSVList = new ArrayList<>();

        for (PrimerCSV primerCsv : csvToBean) {
            if (primerCsv.typeOfPrimer.equals("TaqProbe")) {
                if (primerCsv.sequence == null) {
                    throw new IllegalArgumentException("Attribute 'sequence' must not be empty!");
                }
                else if (primerCsv.sequence.isEmpty()) {
                    throw new IllegalArgumentException("Attribute 'sequence' must not be empty!");
                }
                if (primerCsv.assayId == null) {
                    throw new IllegalArgumentException("Attribute 'assayId' must not be empty!");
                }
                else if (primerCsv.assayId.isEmpty()) {
                    throw new IllegalArgumentException("Attribute 'assayId' must not be empty!");
                }
                if (primerCsv.size == null) {
                    throw new IllegalArgumentException("Attribute 'size' must not be empty!");
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                format.parse(primerCsv.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            PrimerCSVList.add(primerCsv);
        }

        for (PrimerCSV p : PrimerCSVList) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            try {
                date = format.parse(p.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

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
                    dao.findThreeQuencher(p.threeQuencher), dao.findFiveDye(p.fiveDye), date, null);
            dao.create(primer);
        }

        return Response.ok("Successfully uploaded primers from CSV file.").build();
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }

    // read uploaded file into a String
    private String writeToString(InputStream uploadedInputStream) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(uploadedInputStream, stringWriter, "UTF-8");
        return stringWriter.toString();
    }

    //@GET("export")
    //@UnitOfWork
    // TODO

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
        @CsvBindByName(column = "Tm (°C)")
        public Double Tm;
        @CsvBindByName(column = "Optimal T of Annealing (°C)")
        public Double optimalTOfAnnealing;
        @CsvBindByName(column = "Purification Method", required = true)
        public String purificationMethod;
        @CsvBindByName(column = "Amount Available (µl)")
        public Double amountAvailableMikroL;
        @CsvBindByName(column = "Amount Available (Packs)")
        public Integer amountAvailablePacks;
        @CsvBindByName(column = "Amount Available (Pack Type)")
        public String amountAvailablePackType;
        @CsvBindByName(column = "Date")
        public String date;
        @CsvBindByName(column = "Length of Amplicone")
        public Integer lengthOfAmplicone;
        @CsvBindByName(column = "Storing T (°C)")
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
