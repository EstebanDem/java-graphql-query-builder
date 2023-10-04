package dem.esteban.graphql.query.builder.models;

import java.util.Map;

public class GraphQLQuery {
    private final String query;
    private final Map<String, Object> variables;

    public GraphQLQuery(GraphQLQueryBuilder builder) {
        this.query = builder.getFullQueryExpression();
        this.variables = builder.getVariables();
    }

    public String getQuery() {
        return query;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
