package dem.esteban.graphql.query.builder;

public class Main {

    public static void main(String[] args) {
        GraphQLQuery q = new GraphQLQuery.GraphQLQueryBuilder()
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .addVariable("key1", "value1")
                .build();
    }
}
