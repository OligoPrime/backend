package si.fri.resources;

import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.health.HealthCheck.Result;
import com.codahale.metrics.health.HealthCheckRegistry;

@Produces(MediaType.APPLICATION_JSON)
@Path("/healthcheck")
public class HealthCheckResource {
    private HealthCheckRegistry registry;

    public HealthCheckResource(HealthCheckRegistry registry) {
        this.registry = registry;
    }

    @GET
    @Path("/raw")
    public Set<Entry<String, Result>> getStatus(){
        return registry.runHealthChecks().entrySet();
    }

    @GET
    public Response htmlDocument() {
        StringBuilder stringBuilder = new StringBuilder(htmlDocumentFirstPart());

        Set<Entry<String, Result>> healthChecks = registry.runHealthChecks().entrySet();
        for (Entry<String, Result> healthCheck : healthChecks) {

            String name = healthCheck.getKey().equals("hibernate") ? "Database" : healthCheck.getKey();
            String healthy = healthCheck.getValue().isHealthy() ? "<td style=\"background-color:#28a745\">" + healthCheck.getValue().isHealthy() + "</td>" : "<td style=\"background-color:#dc3545\">" + healthCheck.getValue().isHealthy() + "</td>";

            stringBuilder.append("<tr>");
            stringBuilder.append("<td>").append(name).append("</td>");
            stringBuilder.append(healthy);
            stringBuilder.append("<td>").append(healthCheck.getValue().getMessage()).append("</td>");
            stringBuilder.append("<td>").append(healthCheck.getValue().getError()).append("</td>");
            stringBuilder.append("<td>").append(healthCheck.getValue().getDetails()).append("</td>");
            stringBuilder.append("<td>").append(healthCheck.getValue().getTime()).append("</td>");
            stringBuilder.append("<td>").append(healthCheck.getValue().getDuration()).append("</td>");
            stringBuilder.append("<td>").append(healthCheck.getValue().getTimestamp()).append("</td>");
            stringBuilder.append("</tr>");

            System.out.println("SEM TU " + healthCheck.getValue());
        }

        stringBuilder.append(htmlDocumentSecondPart());

        return Response.ok(stringBuilder.toString()).type(MediaType.TEXT_HTML).build();
    }

    private String htmlDocumentFirstPart() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>OligoPrime Health Checks</h2>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Healthy</th>\n" +
                "    <th>Message</th>\n" +
                "    <th>Error</th>\n" +
                "    <th>Details</th>\n" +
                "    <th>Time</th>\n" +
                "    <th>Duration</th>\n" +
                "    <th>Timestamp</th>\n" +
                "  </tr>\n";
    }

    private String htmlDocumentSecondPart() {
        return "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}