package dem.esteban.graphql.query.builder.response.countryapi;

public interface GraphQLConstants {
    String QUERY_INITIALIZER = "query Query(";
    String QUERY_PARAMS_END = ") ";

    /**
     * String format of non-mandatory variable
     * For example: $varName: ID
     */
    String QUERY_VARIABLE_TEMPLATE = "$%s: %s";

    /**
     * String format of mandatory variable
     * For example: $varName: ID!
     */
    String QUERY_MANDATORY_VARIABLE_TEMPLATE = "$%s: %s!";

    String QUERY_PARAM_VARIABLES_SEPARATOR = ", ";

    // Types
    String TYPE_STRING = "String";
    String TYPE_INT = "Int";
    String TYPE_FLOAT = "Float";
    String TYPE_BOOLEAN = "Boolean";
    String TYPE_ID = "ID";
    String TYPE_MANDATORY = "!";
}
