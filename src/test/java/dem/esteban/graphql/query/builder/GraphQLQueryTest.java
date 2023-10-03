package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLQueryTest {

    @Test
    public void testQueryWithOneVariable() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addParameterVariable("countryCode", GraphQLConstants.TYPE_ID, true)
                .addVariable("countryCode", "BR")
                .build();

        assertEquals("query Query($countryCode: ID!) ", graphQLQuery.getQuery());
    }

    @Test
    public void testQueryWithMultipleVariables() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addParameterVariable("city", GraphQLConstants.TYPE_STRING, false)
                .addParameterVariable("age", GraphQLConstants.TYPE_INT, true)
                .addParameterVariable("retired", GraphQLConstants.TYPE_BOOLEAN, false)
                .addVariable("city", "Tokio")
                .addVariable("age", 19)
                .addVariable("retired", false)
                .build();

        assertEquals("query Query($city: String, $age: Int!, $retired: Boolean) ", graphQLQuery.getQuery());
    }
}