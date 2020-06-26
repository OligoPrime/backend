package si.fri;

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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("dev-config.yml");
    public static final DropwizardAppExtension<BackendConfiguration> RULE = new DropwizardAppExtension<>(
            BackendApplication.class, CONFIG_PATH
    );

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testHello() {
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

        try {
            JSONAssert.assertEquals(helloArray.toString(), hellos.toString(), false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
