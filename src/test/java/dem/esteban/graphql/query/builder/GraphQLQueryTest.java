package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphQLQueryTest {

    @Test
    public void testQueryWithOneVariable() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLConstants.TYPE_ID, "BR", true)
                .build();

        assertEquals("query Query($countryCode: ID!) ", graphQLQuery.getQuery());
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

                .build();

        assertEquals("query Query($city: String, $age: Int!, $retired: Boolean) ", graphQLQuery.getQuery());
        assertEquals(3, graphQLQuery.getVariables().size());

        assertTrue(graphQLQuery.getVariables().containsKey("city"));
        assertEquals("Tokio", graphQLQuery.getVariables().get("city"));

        assertTrue(graphQLQuery.getVariables().containsKey("age"));
        assertEquals(21, graphQLQuery.getVariables().get("age"));

        assertTrue(graphQLQuery.getVariables().containsKey("retired"));
        assertEquals(false, graphQLQuery.getVariables().get("retired"));
    }
}