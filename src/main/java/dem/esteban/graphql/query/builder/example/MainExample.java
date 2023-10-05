package dem.esteban.graphql.query.builder.example;

import java.io.IOException;

public class MainExample {

    public static void main(String[] args) throws IOException {
        ApiCountriesClient countriesClient = new ApiCountriesClient();
        Country country = countriesClient.getResponse("AR");
        System.out.println(country);
    }
}
