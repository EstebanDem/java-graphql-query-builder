package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;

import java.util.HashMap;
import java.util.Map;

public class GraphQLQuery {
    private String query;
    private Map<String, Object> variables;

    private GraphQLQuery(GraphQLQueryBuilder builder) {
        this.query = builder.query + GraphQLConstants.QUERY_PARAMS_END;
        this.variables = builder.variables;
    }

    public String getQuery() {
        return query;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public static class GraphQLQueryBuilder {
        private String query;
        private Map<String, Object> variables;

        // Control variables
        private int parameterVariablesCount = 0;

        public GraphQLQueryBuilder() {
            query = GraphQLConstants.QUERY_INITIALIZER;
            variables = new HashMap<>();
        }

        public GraphQLQueryBuilder addVariable(String key, String type, Object value, Boolean isMandatory) {
            variables.put(key, value);

            if (parameterVariablesCount > 0) {
                query += GraphQLConstants.QUERY_PARAM_VARIABLES_SEPARATOR;
            }
            parameterVariablesCount++;

            String variableTemplate = isMandatory
                    ? GraphQLConstants.QUERY_MANDATORY_VARIABLE_TEMPLATE
                    : GraphQLConstants.QUERY_VARIABLE_TEMPLATE;

            String newVariable = String.format(variableTemplate, key, type);
            query += newVariable;

            return this;
        }

        public GraphQLQuery build() {
            return new GraphQLQuery(this);
        }
    }
}
