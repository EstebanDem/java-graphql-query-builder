package dem.esteban.graphql.query.builder.models.response;

public abstract class GraphQLResponse<T> {
    private T data;

    public T getData() {
        return data;
    }
}
