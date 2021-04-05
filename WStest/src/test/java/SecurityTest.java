import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SecurityTest {
    static String restServicePath = "http://localhost:8080/order/rest/login";
    protected static String getToken(Client client, String usr, String pass){
        try{
            String token = Files.readString(Paths.get(System.getProperty("user.dir"), "/src/test/java/", "token.tkn"));
            if (token == null || token.isEmpty()) return "0";
            WebTarget webTarget = client.target(restServicePath).path("check");
            Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = builder.header(HttpHeaders.AUTHORIZATION, token).get();
            if (response.getStatus() == 200) return token;
            else {
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(usr , pass);
                webTarget = client.register(feature).target(restServicePath).path("token");
                builder = webTarget.request();
                response = builder.get();
                token = response.getHeaderString(HttpHeaders.AUTHORIZATION);
                Files.writeString(Paths.get(System.getProperty("user.dir"), "/src/test/java/", "token.tkn"), token);
                return token;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
            return "0";
        }
    }
}
