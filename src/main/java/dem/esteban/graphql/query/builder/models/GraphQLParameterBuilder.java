package dem.esteban.graphql.query.builder.models;

import dem.esteban.graphql.query.builder.constants.GraphQLTypes;

public final class GraphQLParameterBuilder {
    private String name;
    private GraphQLTypes type;
    private boolean mandatory;
    private String argument;
    private Object value;

    private GraphQLParameterBuilder() {
    }

    public static GraphQLParameterBuilder aGraphQLParameter() {
        return new GraphQLParameterBuilder();
    }

    public GraphQLParameterBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GraphQLParameterBuilder withType(GraphQLTypes type) {
        this.type = type;
        return this;
    }

    public GraphQLParameterBuilder withMandatory(boolean mandatory) {
        this.mandatory = mandatory;
        return this;
    }

    public GraphQLParameterBuilder withArgument(String argument) {
        this.argument = argument;
        return this;
    }

    public GraphQLParameterBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public GraphQLParameter build() {
        GraphQLParameter graphQLParameter = new GraphQLParameter();
        graphQLParameter.setName(name);
        graphQLParameter.setType(type);
        graphQLParameter.setMandatory(mandatory);
        graphQLParameter.setArgument(argument);
        graphQLParameter.setValue(value);
        return graphQLParameter;
    }
}
