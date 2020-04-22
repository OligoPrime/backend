package si.fri.resources;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/csv")
@Produces(MediaType.APPLICATION_JSON)
public class CsvResource {

    public CsvResource() {

    }

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {

        String fileName = fileDetail.getFileName();

        // check that file extension is csv
        if (!FilenameUtils.getExtension(fileName).equalsIgnoreCase("csv")) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        }

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(uploadedInputStream)).withCSVParser(csvParser).build();

        String[] nextRecord;
        
        // prints csv in console
        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }

        return Response.ok("CSV file successfully uploaded.").build();
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
}
