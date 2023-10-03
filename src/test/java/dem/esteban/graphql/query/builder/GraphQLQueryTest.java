package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLQueryTest {

    @Test
    public void testQueryWithOneVariable() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("countryCode", GraphQLConstants.TYPE_ID, "BR", true)
                .build();

        assertEquals("query Query($countryCode: ID!) ", graphQLQuery.getQuery());
    }

    @Test
    public void testQueryWithMultipleVariables() {
        GraphQLQuery graphQLQuery = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("city", GraphQLConstants.TYPE_STRING, "Tokio", false)
                .addVariable("age", GraphQLConstants.TYPE_INT, 21, true)
                .addVariable("retired", GraphQLConstants.TYPE_BOOLEAN, false, false)

                .build();

        assertEquals("query Query($city: String, $age: Int!, $retired: Boolean) ", graphQLQuery.getQuery());
    }
}