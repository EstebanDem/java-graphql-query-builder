package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.response.countryapi.GraphQLConstants;
import dem.esteban.graphql.query.builder.utils.ClassToGraphQLAttributesUtil;

import java.util.HashMap;
import java.util.Map;

public class GraphQLQuery {
    private String query;
    private Map<String, Object> variables;

    private GraphQLQuery(GraphQLQueryBuilder builder) {
        this.query = builder.query;
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
        private boolean variablesHaveBeenDefined = false;
        private boolean fieldsStructureDefined = false;

        public GraphQLQueryBuilder() {
            query = GraphQLConstants.QUERY_INITIALIZER;
            variables = new HashMap<>();
        }

        public GraphQLQueryBuilder addVariable(String key, String type, Object value, Boolean isMandatory) {
            if (fieldsStructureDefined) {
                throw new IllegalStateException("Cannot add variables after field structure definition");
            }
            variablesHaveBeenDefined = true;
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

        public GraphQLQueryBuilder addFieldsStructure(Class<?> clazz) {
            if (!variablesHaveBeenDefined) {
                throw new IllegalStateException("Fields should be defined after variables have been declared");
            }
            String fieldsStructure = ClassToGraphQLAttributesUtil.generateStructure(clazz);

            query = query
                    + GraphQLConstants.QUERY_PARAMS_END
                    + fieldsStructure
                    + GraphQLConstants.QUERY_END;
            fieldsStructureDefined = true;
            return this;
        }

        public GraphQLQuery build() {
            return new GraphQLQuery(this);
        }
    }
}
