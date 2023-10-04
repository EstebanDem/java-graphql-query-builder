package dem.esteban.graphql.query.builder.models;

import dem.esteban.graphql.query.builder.constants.GraphQLConstants;
import dem.esteban.graphql.query.builder.constants.GraphQLTypes;
import dem.esteban.graphql.query.builder.utils.ClassToGraphQLAttributesUtil;

import java.util.HashMap;
import java.util.Map;

public class GraphQLQueryBuilder {
    private String fullQueryExpression;
    private String queryName;
    private String queryArguments;
    private String operationName;
    private String parameterVariables;
    private String fieldsStructure;
    private Map<String, Object> variables = new HashMap<>();

    // Flow Control variables
    private boolean operationNameDefined;
    private int parameterVariablesCount = 0;

    private GraphQLQueryBuilder() {
        parameterVariables = "";
        fieldsStructure = "";
        queryArguments = "";
        queryName = GraphQLConstants.QUERY_DEFAULT_NAME;
        operationName = "";
        variables = new HashMap<>();
    }

    public String getFullQueryExpression() {
        return fullQueryExpression;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public static GraphQLQueryBuilder aGraphQLQueryBuilder() {
        return new GraphQLQueryBuilder();
    }

    public GraphQLQueryBuilder customQueryName(String queryName) {
        this.queryName = queryName;
        return this;
    }

    public GraphQLQueryBuilder addVariable(String name, GraphQLTypes type, Object value, Boolean isMandatory, String argument) {
        processNewVariable(new GraphQLParameter(name, type, isMandatory, argument, value));
        return this;
    }
    public GraphQLQueryBuilder addVariable(GraphQLParameter graphQLParameter) {
        processNewVariable(graphQLParameter);
        return this;
    }

    private void processNewVariable(GraphQLParameter graphQLParameter) {
        variables.put(graphQLParameter.getName(), graphQLParameter.getValue());

        if (parameterVariablesCount > 0) {
            parameterVariables += GraphQLConstants.QUERY_PARAM_VARIABLES_SEPARATOR;
            queryArguments += GraphQLConstants.QUERY_PARAM_VARIABLES_SEPARATOR;
        }
        parameterVariablesCount++;

        String variableTemplate = graphQLParameter.isMandatory()
                ? GraphQLConstants.QUERY_MANDATORY_VARIABLE_TEMPLATE
                : GraphQLConstants.QUERY_VARIABLE_TEMPLATE;

        String newVariable = String.format(variableTemplate, graphQLParameter.getName(), graphQLParameter.getType().getValue());
        queryArguments += String.format(GraphQLConstants.ARGUMENT_TEMPLATE, graphQLParameter.getArgument(), graphQLParameter.getName());
        parameterVariables += newVariable;
    }

    public GraphQLQueryBuilder operationName(String operationName) {
        operationNameDefined = true;
        this.operationName = operationName;
        return this;
    }

    public GraphQLQueryBuilder addFieldsStructureByClass(Class<?> clazz) {
        fieldsStructure += ClassToGraphQLAttributesUtil.generateStructure(clazz);
        return this;
    }

    public GraphQLQuery build() {
        fullQueryExpression = String.format(
                GraphQLConstants.QUERY_WITH_PARAMETERS_FORMAT,
                queryName,
                parameterVariables,
                operationName,
                queryArguments,
                fieldsStructure
        );
        return new GraphQLQuery(this);
    }


}
