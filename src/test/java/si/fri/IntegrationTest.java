package si.fri;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import si.fri.core.Hello;
import si.fri.core.primer_enums.*;
import si.fri.resources.PrimerResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("dev-config.yml");
    public static final DropwizardAppExtension<BackendConfiguration> RULE = new DropwizardAppExtension<>(
            BackendApplication.class, CONFIG_PATH
    );

    private final ObjectMapper mapper = new ObjectMapper();

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date = format.parse("31/12/2019");

    public IntegrationTest() throws ParseException {
    }

    @Test
    public void testHello() throws JSONException {
        Hello saying = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/hello")
                .queryParam("name", "Doctor Robotnik")
                .request()
                .post(Entity.json(null), Hello.class);
        assertThat(saying.getContent()).isEqualTo("Hello, this is Doctor Robotnik!");

        saying = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/hello")
                .queryParam("name", "Thomas A. Anderson")
                .request()
                .post(Entity.json(null), Hello.class);
        assertThat(saying.getContent()).isEqualTo("Hello, this is Thomas A. Anderson!");

        saying = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/hello")
                .request()
                .post(Entity.json(null), Hello.class);
        assertThat(saying.getContent()).isEqualTo("Hello, this is backend!");

        ObjectNode loginRequest = mapper.createObjectNode();
        loginRequest.put("username", "test");
        loginRequest.put("password", "t");
        Response loginResponse = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/auth/login")
                .request()
                .post(Entity.json(loginRequest), Response.class);
        String jwt = loginResponse.readEntity(String.class);

        JsonNode hellos = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/hello")
                .request()
                .header("Authorization", "Bearer " + jwt)
                .get(JsonNode.class);

        ObjectNode hello1 = mapper.createObjectNode();
        hello1.put("id", 1);
        hello1.put("content", "Hello, this is Doctor Robotnik!");
        ObjectNode hello2 = mapper.createObjectNode();
        hello2.put("id", 2);
        hello2.put("content", "Hello, this is Thomas A. Anderson!");
        ObjectNode hello3 = mapper.createObjectNode();
        hello3.put("id", 3);
        hello3.put("content", "Hello, this is backend!");

        ArrayNode helloArray = mapper.createArrayNode();
        helloArray.add(hello1);
        helloArray.add(hello2);
        helloArray.add(hello3);

        JSONAssert.assertEquals(helloArray.toString(), hellos.toString(), false);
    }

    @Test
    public void testAddPrimer() throws JsonProcessingException, JSONException {
        ObjectNode loginRequest = mapper.createObjectNode();
        loginRequest.put("username", "test");
        loginRequest.put("password", "t");
        Response loginResponse = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/auth/login")
                .request()
                .post(Entity.json(loginRequest), Response.class);
        String jwt = loginResponse.readEntity(String.class);

        PrimerResource.PrimerJSON primerJSON = new PrimerResource.PrimerJSON();
        primerJSON.name = "testname";
        primerJSON.sequence = "testsequence";
        primerJSON.orientation = "reverse";
        primerJSON.freezer = "freezer2";
        primerJSON.drawer = "drawer3";
        primerJSON.box = "box5";
        primerJSON.positionInReference = "5'-promotor";
        primerJSON.tm = 65.2;
        primerJSON.optimalTOfAnnealing = 22.1;
        primerJSON.purificationMethod = "Cartridge";
        primerJSON.amountAvailable = 42.3;
        primerJSON.amountAvailablePacks = 30;
        primerJSON.amountAvailablePackType = "plate";
        primerJSON.date = date;
        primerJSON.lengthOfAmplicone = 30;
        primerJSON.storingT = "42.2";
        primerJSON.gcpercent = 34.3;
        primerJSON.organism = "Homo sapiens";
        primerJSON.gen = "gen123";
        primerJSON.ncbiGenId = "ncbigenid123";
        primerJSON.humanGenomBuild = "NCBI Build 36.1";
        primerJSON.formulation = "Resuspended in TRIS";
        primerJSON.typeOfPrimer = "M13/pUC primer";
        primerJSON.sondaSequence = "sondaseq123";
        primerJSON.assayId = "assayid123";
        primerJSON.size = "M";
        primerJSON.primerApplication = "Sanger Sequencing";
        primerJSON.applicationComment = "application comment 123";
        primerJSON.fiveModification = "Aldehyde Modifier";
        primerJSON.threeModification = "Biotin TEG";
        primerJSON.concentrationOrdered = 40.0;
        primerJSON.concentrationOrderedUnit = "nmol";
        primerJSON.checkSpecifityInBlast = true;
        primerJSON.designerName = "designer123";
        primerJSON.designerPublication = "publication123";
        primerJSON.designerDatabase = "database123";
        primerJSON.project = "project3";
        primerJSON.supplier = "Omega";
        primerJSON.manufacturer = "BioSearch";
        primerJSON.comment = "komentar";
        primerJSON.document = "dokument link";
        primerJSON.analysis = "analiza 123";
        primerJSON.orderStatus = "received";
        primerJSON.threeQuencher = "TAMRA";
        primerJSON.fiveDye = "NED";

        JsonNode primer = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/add")
                .request()
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.json(primerJSON), JsonNode.class);

        ObjectNode expectedPrimer = mapper.valueToTree(primerJSON);

        // fix enum values
        expectedPrimer.put("amountAvailablePackType", AmountAvailablePackType.fromString(expectedPrimer.get("amountAvailablePackType").textValue()).name());
        expectedPrimer.put("concentrationOrderedUnit", ConcentrationOrderedUnit.fromString(expectedPrimer.get("concentrationOrderedUnit").textValue()).name());
        expectedPrimer.put("orderStatus", OrderStatus.fromString(expectedPrimer.get("orderStatus").textValue()).name());
        expectedPrimer.put("orientation", Orientation.fromString(expectedPrimer.get("orientation").textValue()).name());
        expectedPrimer.put("size", Size.fromString(expectedPrimer.get("size").textValue()).name());

        // add fields not present in primerJSON
        int primerId = primer.get("id").intValue();
        expectedPrimer.put("id", primerId);
        expectedPrimer.put("generatedName", "R-HS-ncbigenid123-" + primerId);
        expectedPrimer.put("length", 12);
        expectedPrimer.put("user", "test");
        expectedPrimer.put("pairs", mapper.convertValue(Arrays.asList(), JsonNode.class));

        JSONAssert.assertEquals(primer.toString(), expectedPrimer.toString(), false);
    }
}
