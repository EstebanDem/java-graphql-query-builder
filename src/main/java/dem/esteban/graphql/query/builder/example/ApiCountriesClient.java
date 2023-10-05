package dem.esteban.graphql.query.builder.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dem.esteban.graphql.query.builder.constants.GraphQLTypes;
import dem.esteban.graphql.query.builder.models.GraphQLQuery;
import dem.esteban.graphql.query.builder.models.GraphQLQueryBuilder;
import dem.esteban.graphql.query.builder.models.response.GraphQLResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ApiCountriesClient {

    private static final String URI = "https://countries.trevorblades.com/graphql";

    public Country getResponse(String code) throws IOException {
        //Create client
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URI);
        httpPost.setHeader("Content-Type", "application/json");

        // Transform the query into JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(buildQuery(code));
        httpPost.setEntity(new StringEntity(json));

        // Handle response
        HttpResponse response = httpClient.execute(httpPost);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        GraphQLResponse<Country> countryResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        return countryResponse.getData().get("country");
    }

    private GraphQLQuery buildQuery(String code) {
        return GraphQLQueryBuilder.aGraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLTypes.TYPE_ID, code, true, "code")
                .addFieldsStructureByClass(Country.class)
                .operationName("country")
                .build();
    }
}
