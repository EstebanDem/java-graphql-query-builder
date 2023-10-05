package dem.esteban.graphql.query.builder.models.request;

import dem.esteban.graphql.query.builder.constants.GraphQLTypes;

public class GraphQLParameter {
    private String name;
    private GraphQLTypes type;
    private boolean mandatory;
    private String argument;
    private Object value;

    public GraphQLParameter(String name, GraphQLTypes type, boolean mandatory, String argument, Object value) {
        this.name = name;
        this.type = type;
        this.mandatory = mandatory;
        this.argument = argument;
        this.value = value;
    }

    public GraphQLParameter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphQLTypes getType() {
        return type;
    }

    public void setType(GraphQLTypes type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
