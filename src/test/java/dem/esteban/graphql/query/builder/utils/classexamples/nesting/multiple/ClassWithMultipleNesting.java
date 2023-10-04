package dem.esteban.graphql.query.builder.utils.classexamples.nesting.multiple;

import dem.esteban.graphql.query.builder.utils.classexamples.nesting.Operation;

import java.util.Set;

public class ClassWithMultipleNesting {
    private String name;
    private Integer code;
    private boolean available;
    private Set<Operation> operations;
    private Set<Task> tasks;

}
