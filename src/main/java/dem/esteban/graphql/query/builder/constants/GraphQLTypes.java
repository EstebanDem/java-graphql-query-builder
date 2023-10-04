package dem.esteban.graphql.query.builder.constants;

public enum GraphQLTypes {
    TYPE_STRING("String"),
    TYPE_INT("Int"),
    TYPE_FLOAT("Float"),
    TYPE_BOOLEAN("Boolean"),
    TYPE_ID("ID");

    private final String value;
    GraphQLTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
