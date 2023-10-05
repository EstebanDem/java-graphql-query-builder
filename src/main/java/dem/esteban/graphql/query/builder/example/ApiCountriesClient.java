package dem.esteban.graphql.query.builder.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dem.esteban.graphql.query.builder.constants.GraphQLTypes;
import dem.esteban.graphql.query.builder.models.request.GraphQLQuery;
import dem.esteban.graphql.query.builder.models.request.GraphQLQueryBuilder;
import dem.esteban.graphql.query.builder.models.response.GraphQLResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class ApiCountriesClient {

    private static final String URI = "https://countries.trevorblades.com/graphql";

    public Country getCountry(String code) throws IOException {
        //Create client
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URI);
        httpPost.setHeader("Content-Type", "application/json");

        // Transform the query into JSON
        ObjectMapper objectMapper = new ObjectMapper();
        GraphQLQuery graphQLQuery = GraphQLQueryBuilder.aGraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLTypes.TYPE_ID, code, true, "code")
                .addFieldsStructureByClass(Country.class)
                .operationName("country")
                .build();
        String json = objectMapper.writeValueAsString(graphQLQuery);
        httpPost.setEntity(new StringEntity(json));

        // Handle response
        HttpResponse response = httpClient.execute(httpPost);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        GraphQLResponse<Country> countryResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        return countryResponse.getData().get("country");
    }

    public List<Country> getCountries() throws IOException {
        //Create client
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URI);
        httpPost.setHeader("Content-Type", "application/json");

        // Transform the query into JSON
        ObjectMapper objectMapper = new ObjectMapper();
        GraphQLQuery graphQLQuery = GraphQLQueryBuilder.aGraphQLQueryBuilder()
                .addFieldsStructureByClass(Country.class)
                .operationName("countries")
                .build();
        String json = objectMapper.writeValueAsString(graphQLQuery);
        httpPost.setEntity(new StringEntity(json));

        // Handle response
        HttpResponse response = httpClient.execute(httpPost);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        GraphQLResponse<List<Country>> countryResponse = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
        return countryResponse.getData().get("countries");
    }


}
