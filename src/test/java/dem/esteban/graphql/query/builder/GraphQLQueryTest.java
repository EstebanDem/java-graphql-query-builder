package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.constants.GraphQLConstants;
import dem.esteban.graphql.query.builder.constants.GraphQLTypes;
import dem.esteban.graphql.query.builder.utils.classexamples.nesting.simple.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLQueryTest {

    @Test
    public void testQueryWithOneVariable() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLTypes.TYPE_ID, "BR", true, "code")
                .addFieldsStructureByClass(Country.class)
                .operationName("country")
                .build();

        assertEquals("query Query($countryCode: ID!) { country(code: $countryCode) { name languages { code name} }}", graphQLQuery.getQuery());
        assertEquals(1, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("countryCode"));
        assertEquals("BR", graphQLQuery.getVariables().get("countryCode"));
    }

    @Test
    public void testQueryWithMultipleVariables() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("city", GraphQLTypes.TYPE_STRING, "Tokio", false, "argCity")
                .addVariable("age", GraphQLTypes.TYPE_INT, 21, true, "argAge")
                .addVariable("retired", GraphQLTypes.TYPE_BOOLEAN, false, false, "argRetired")
                .addFieldsStructureByClass(Country.class)
                .operationName("cityInformation")
                .build();

        assertEquals("query Query($city: String, $age: Int!, $retired: Boolean) { cityInformation(argCity: $city, argAge: $age, argRetired: $retired) { name languages { code name} }}", graphQLQuery.getQuery());
        assertEquals(3, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("city"));
        assertEquals("Tokio", graphQLQuery.getVariables().get("city"));

        assertTrue(graphQLQuery.getVariables().containsKey("age"));
        assertEquals(21, graphQLQuery.getVariables().get("age"));

        assertTrue(graphQLQuery.getVariables().containsKey("retired"));
        assertEquals(false, graphQLQuery.getVariables().get("retired"));
    }

    @Test
    public void testQueryWithOneVariableWithCustomQueryName() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .customQueryName("getCountryByCode")
                .addVariable("countryCode", GraphQLTypes.TYPE_ID, "BR", true, "code")
                .addFieldsStructureByClass(Country.class)
                .operationName("country")
                .build();

        assertEquals("query getCountryByCode($countryCode: ID!) { country(code: $countryCode) { name languages { code name} }}", graphQLQuery.getQuery());
        assertEquals(1, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("countryCode"));
        assertEquals("BR", graphQLQuery.getVariables().get("countryCode"));
    }
}