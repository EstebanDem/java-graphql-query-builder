package dem.esteban.graphql.query.builder.example;

import java.io.IOException;
import java.util.List;

public class MainExample {
    public static void main(String[] args) throws IOException {
        ApiCountriesClient countriesClient = new ApiCountriesClient();
        // Single country
        Country country = countriesClient.getCountry("AR");
        System.out.println(country);
        // Get all countries
        List<Country> countries = countriesClient.getCountries();
        System.out.println(countries);
    }
}
