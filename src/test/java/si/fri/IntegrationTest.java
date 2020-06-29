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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import si.fri.core.Hello;
import si.fri.core.Primer;
import si.fri.core.primer_enums.*;
import si.fri.resources.PrimerResource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("dev-config.yml");
    public static final DropwizardAppExtension<BackendConfiguration> RULE = new DropwizardAppExtension<>(
            BackendApplication.class, CONFIG_PATH
    );

    private final ObjectMapper mapper = new ObjectMapper();
    private PrimerResource.PrimerJSON primerJSON1, primerJSON2;

    @BeforeEach
    public void setup() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("31/12/2019");
        Date date2 = format.parse("4/20/1969");

        primerJSON1 = new PrimerResource.PrimerJSON();
        primerJSON1.name = "testname";
        primerJSON1.sequence = "testsequence";
        primerJSON1.orientation = "reverse";
        primerJSON1.freezer = "freezer2";
        primerJSON1.drawer = "drawer3";
        primerJSON1.box = "box5";
        primerJSON1.positionInReference = "5'-promotor";
        primerJSON1.tm = 65.2;
        primerJSON1.optimalTOfAnnealing = 22.1;
        primerJSON1.purificationMethod = "Cartridge";
        primerJSON1.amountAvailable = 42.3;
        primerJSON1.amountAvailablePacks = 30;
        primerJSON1.amountAvailablePackType = "plate";
        primerJSON1.date = date;
        primerJSON1.lengthOfAmplicone = 30;
        primerJSON1.storingT = "42.2";
        primerJSON1.gcpercent = 34.3;
        primerJSON1.organism = "Homo sapiens";
        primerJSON1.gen = "gen123";
        primerJSON1.ncbiGenId = "ncbigenid123";
        primerJSON1.humanGenomBuild = "NCBI Build 36.1";
        primerJSON1.formulation = "Resuspended in TRIS";
        primerJSON1.typeOfPrimer = "M13/pUC primer";
        primerJSON1.sondaSequence = "sondaseq123";
        primerJSON1.assayId = "assayid123";
        primerJSON1.size = "M";
        primerJSON1.primerApplication = "Sanger Sequencing";
        primerJSON1.applicationComment = "application comment 123";
        primerJSON1.fiveModification = "Aldehyde Modifier";
        primerJSON1.threeModification = "Biotin TEG";
        primerJSON1.concentrationOrdered = 40.0;
        primerJSON1.concentrationOrderedUnit = "nmol";
        primerJSON1.checkSpecifityInBlast = true;
        primerJSON1.designerName = "designer123";
        primerJSON1.designerPublication = "publication123";
        primerJSON1.designerDatabase = "database123";
        primerJSON1.project = "project3";
        primerJSON1.supplier = "Omega";
        primerJSON1.manufacturer = "BioSearch";
        primerJSON1.comment = "komentar";
        primerJSON1.document = "dokument link";
        primerJSON1.analysis = "analiza 123";
        primerJSON1.orderStatus = "received";
        primerJSON1.threeQuencher = "TAMRA";
        primerJSON1.fiveDye = "NED";

        primerJSON2 = new PrimerResource.PrimerJSON();
        primerJSON2.name = "newname";
        primerJSON2.sequence = "newsequence";
        primerJSON2.orientation = "forward";
        primerJSON2.freezer = "freezer3";
        primerJSON2.drawer = "drawer2";
        primerJSON2.box = "box1";
        primerJSON2.positionInReference = "5'-untranslated region";
        primerJSON2.tm = 35.3;
        primerJSON2.optimalTOfAnnealing = 12.4;
        primerJSON2.purificationMethod = "Desalted";
        primerJSON2.amountAvailable = 32.4;
        primerJSON2.amountAvailablePacks = 52;
        primerJSON2.amountAvailablePackType = "tube";
        primerJSON2.date = date2;
        primerJSON2.lengthOfAmplicone = 35;
        primerJSON2.storingT = "32.6";
        primerJSON2.gcpercent = 23.4;
        primerJSON2.organism = "Escherichia coli TG1";
        primerJSON2.gen = "gen124";
        primerJSON2.ncbiGenId = "ncbigenid124";
        primerJSON2.humanGenomBuild = "NCBI Build 34";
        primerJSON2.formulation = "Lyophilized";
        primerJSON2.typeOfPrimer = "GAPDH primer";
        primerJSON2.sondaSequence = "sondaseq124";
        primerJSON2.assayId = "assayid124";
        primerJSON2.size = "XS";
        primerJSON2.primerApplication = "Genotyping";
        primerJSON2.applicationComment = "application comment 124";
        primerJSON2.fiveModification = "FAM";
        primerJSON2.threeModification = "Amino Linker C7";
        primerJSON2.concentrationOrdered = 24.5;
        primerJSON2.concentrationOrderedUnit = "nM";
        primerJSON2.checkSpecifityInBlast = false;
        primerJSON2.designerName = "designer124";
        primerJSON2.designerPublication = "publication124";
        primerJSON2.designerDatabase = "database124";
        primerJSON2.project = "project2";
        primerJSON2.supplier = "Kemomed";
        primerJSON2.manufacturer = "Biocompare";
        primerJSON2.comment = "komentar 42";
        primerJSON2.document = "dokument link 42";
        primerJSON2.analysis = "analiza 124";
        primerJSON2.orderStatus = "wanted";
        primerJSON2.threeQuencher = "MGBNFQ";
        primerJSON2.fiveDye = "GFAM";
    }

    private void fixEnums(ObjectNode expectedPrimer) {
        expectedPrimer.put("amountAvailablePackType", Objects.requireNonNull(AmountAvailablePackType.fromString(expectedPrimer.get("amountAvailablePackType").textValue())).name());
        expectedPrimer.put("concentrationOrderedUnit", Objects.requireNonNull(ConcentrationOrderedUnit.fromString(expectedPrimer.get("concentrationOrderedUnit").textValue())).name());
        expectedPrimer.put("orderStatus", Objects.requireNonNull(OrderStatus.fromString(expectedPrimer.get("orderStatus").textValue())).name());
        expectedPrimer.put("orientation", Objects.requireNonNull(Orientation.fromString(expectedPrimer.get("orientation").textValue())).name());
        expectedPrimer.put("size", Objects.requireNonNull(Size.fromString(expectedPrimer.get("size").textValue())).name());
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
    public void testAddUpdateGetDeletePrimer() throws JSONException {
        ObjectNode loginRequest = mapper.createObjectNode();
        loginRequest.put("username", "test");
        loginRequest.put("password", "t");
        Response loginResponse = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/auth/login")
                .request()
                .post(Entity.json(loginRequest), Response.class);
        String jwt = loginResponse.readEntity(String.class);

        JsonNode primer1 = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/add")
                .request()
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.json(primerJSON1), JsonNode.class);

        ObjectNode expectedPrimer1 = mapper.valueToTree(primerJSON1);
        fixEnums(expectedPrimer1);

        // add fields not present in primerJSON
        int primer1Id = primer1.get("id").intValue();
        expectedPrimer1.put("id", primer1Id);
        expectedPrimer1.put("generatedName", "R-HS-ncbigenid123-" + primer1Id);
        expectedPrimer1.put("length", 12);
        expectedPrimer1.put("user", "test");
        expectedPrimer1.put("pairs", mapper.convertValue(Collections.emptyList(), JsonNode.class));

        JSONAssert.assertEquals(primer1.toString(), expectedPrimer1.toString(), false);

        JsonNode primer2 = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/update")
                .queryParam("id", primer1Id)
                .request()
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.json(primerJSON2), JsonNode.class);

        ObjectNode expectedPrimer2 = mapper.valueToTree(primerJSON2);
        fixEnums(expectedPrimer2);

        // add fields not present in primerJSON
        int primer2Id = primer2.get("id").intValue();
        expectedPrimer2.put("id", primer2Id);
        expectedPrimer2.put("generatedName", "F-G1-ncbigenid124-" + primer2Id);
        expectedPrimer2.put("length", 11);
        expectedPrimer2.put("user", "test");
        expectedPrimer2.put("pairs", mapper.convertValue(Collections.emptyList(), JsonNode.class));

        JSONAssert.assertEquals(primer2.toString(), expectedPrimer2.toString(), false);

        primer2 = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/get/" + primer2Id)
                .request()
                .header("Authorization", "Bearer " + jwt)
                .get(JsonNode.class);

        JSONAssert.assertEquals(primer2.toString(), expectedPrimer2.toString(), false);

        Response deleteResponse = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/delete")
                .request()
                .header("Authorization", "Bearer " + jwt)
                .post(Entity.json(primer2Id), Response.class);

        assertThat(deleteResponse.readEntity(String.class)).isEqualTo("Successfully deleted primer.");

        Primer deletedPrimer = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/primers/get/" + primer2Id)
                .request()
                .header("Authorization", "Bearer " + jwt)
                .get(Primer.class);

        assertThat(deletedPrimer).isNull();
    }

}
