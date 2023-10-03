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

        public GraphQLQueryBuilder addParameterVariable(String paramVariable, String type, Boolean isMandatory) {
            if (parameterVariablesCount > 0) {
                query += GraphQLConstants.QUERY_PARAM_VARIABLES_SEPARATOR;
            }

            String variableTemplate = isMandatory
                    ? GraphQLConstants.QUERY_MANDATORY_VARIABLE_TEMPLATE
                    : GraphQLConstants.QUERY_VARIABLE_TEMPLATE;

            String newVariable = String.format(variableTemplate, paramVariable, type);
            query += newVariable;
            parameterVariablesCount++;

            return this;
        }

        public GraphQLQueryBuilder addVariable(String key, Object value) {
            variables.put(key, value);
            return this;
        }

        public GraphQLQuery build() {
            validateParamsAndVariablesMatch();
            return new GraphQLQuery(this);
        }

        private void validateParamsAndVariablesMatch() {
            if (variables.size() != parameterVariablesCount) {
                // clearer
                throw new RuntimeException("There are " + variables.size() + " variables, " +
                        "but " + parameterVariablesCount + " have been defined.");
            }
        }


    }
}
