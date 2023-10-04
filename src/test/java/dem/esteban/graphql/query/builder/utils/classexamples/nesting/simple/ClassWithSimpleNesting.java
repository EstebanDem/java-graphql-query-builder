package dem.esteban.graphql.query.builder.utils.classexamples.nesting.simple;

import dem.esteban.graphql.query.builder.utils.classexamples.nesting.Operation;

import java.util.Set;

public class ClassWithSimpleNesting {
    private String name;
    private Integer code;
    private boolean available;
    private Set<Operation> operations;
}
