package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;
import dem.esteban.graphql.query.builder.utils.classexamples.nesting.simple.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLQueryTest {

    @Test
    public void testQueryWithOneVariable() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLConstants.TYPE_ID, "BR", true)
                .addFieldsStructure(Country.class)
                .build();

        assertEquals("query Query($countryCode: ID!) {name languages { code name} }", graphQLQuery.getQuery());
        assertEquals(1, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("countryCode"));
        assertEquals("BR", graphQLQuery.getVariables().get("countryCode"));
    }

    @Test
    public void testQueryWithMultipleVariables() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("city", GraphQLConstants.TYPE_STRING, "Tokio", false)
                .addVariable("age", GraphQLConstants.TYPE_INT, 21, true)
                .addVariable("retired", GraphQLConstants.TYPE_BOOLEAN, false, false)
                .addFieldsStructure(Country.class)
                .build();

        assertEquals("query Query($city: String, $age: Int!, $retired: Boolean) {name languages { code name} }", graphQLQuery.getQuery());
        assertEquals(3, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("city"));
        assertEquals("Tokio", graphQLQuery.getVariables().get("city"));

        assertTrue(graphQLQuery.getVariables().containsKey("age"));
        assertEquals(21, graphQLQuery.getVariables().get("age"));

        assertTrue(graphQLQuery.getVariables().containsKey("retired"));
        assertEquals(false, graphQLQuery.getVariables().get("retired"));
    }

    @Test
    public void testQueryWithoutDefiningVariables() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> new GraphQLQuery.GraphQLQueryBuilder()
                .addFieldsStructure(Country.class)
                .build());

        assertEquals("Fields should be defined after variables have been declared", exception.getMessage());
    }

    @Test
    public void testQueryAddingVariablesAfterDefiningFieldsStructure() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("city", GraphQLConstants.TYPE_STRING, "Tokio", false)
                .addFieldsStructure(Country.class)
                .addVariable("retired", GraphQLConstants.TYPE_BOOLEAN, false, false)
                .build());

        assertEquals("Cannot add variables after field structure definition", exception.getMessage());
    }
}