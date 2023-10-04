package dem.esteban.graphql.query.builder;

import dem.esteban.graphql.query.builder.constants.GraphQLConstants;
import dem.esteban.graphql.query.builder.constants.GraphQLTypes;
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
        private String queryName = GraphQLConstants.QUERY_DEFAULT_NAME;
        private String parameterVariables;
        private String fieldsStructure;
        private final Map<String, Object> variables;

        // Flow Control variables
        private int parameterVariablesCount = 0;

        public GraphQLQueryBuilder() {
            parameterVariables = "";
            fieldsStructure = "";
            variables = new HashMap<>();
        }

        public GraphQLQueryBuilder customQueryName(String queryName) {
            this.queryName = queryName;
            return this;
        }

        public GraphQLQueryBuilder addVariable(String key, GraphQLTypes type, Object value, Boolean isMandatory) {
            variables.put(key, value);

            if (parameterVariablesCount > 0) {
                parameterVariables += GraphQLConstants.QUERY_PARAM_VARIABLES_SEPARATOR;
            }
            parameterVariablesCount++;

            String variableTemplate = isMandatory
                    ? GraphQLConstants.QUERY_MANDATORY_VARIABLE_TEMPLATE
                    : GraphQLConstants.QUERY_VARIABLE_TEMPLATE;

            String newVariable = String.format(variableTemplate, key, type.getValue());
            parameterVariables += newVariable;

            return this;
        }

        public GraphQLQueryBuilder addFieldsStructureByClass(Class<?> clazz) {
            fieldsStructure += ClassToGraphQLAttributesUtil.generateStructure(clazz);
            return this;
        }

        public GraphQLQuery build() {
            query = String.format(GraphQLConstants.QUERY_WITH_PARAMETERS_FORMAT, queryName, parameterVariables, fieldsStructure);
            return new GraphQLQuery(this);
        }
    }
}
