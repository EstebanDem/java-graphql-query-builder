package dem.esteban.graphql.query.builder.constants;

public final class GraphQLConstants {
    private GraphQLConstants() {
    }

    public static String QUERY_WITH_PARAMETERS_FORMAT ="query Query(%s) { %s }";

    /**
     * String format of non-mandatory variable
     * For example: $varName: ID
     */
    public static String QUERY_VARIABLE_TEMPLATE = "$%s: %s";

    /**
     * String format of mandatory variable
     * For example: $varName: ID!
     */
    public static String QUERY_MANDATORY_VARIABLE_TEMPLATE = "$%s: %s!";

    public static String QUERY_PARAM_VARIABLES_SEPARATOR = ", ";
}
