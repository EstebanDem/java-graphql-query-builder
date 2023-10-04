package dem.esteban.graphql.query.builder.utils;

import dem.esteban.graphql.query.builder.utils.classexamples.nesting.multiple.ClassWithMultipleNesting;
import dem.esteban.graphql.query.builder.utils.classexamples.nesting.simple.ClassWithSimpleNesting;
import dem.esteban.graphql.query.builder.utils.classexamples.withoutnesting.ClassWithoutNesting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassToGraphQLAttributesUtilTest {

    @Test
    public void testGenerateStructureClassWithoutNesting() {
        String structure = ClassToGraphQLAttributesUtil.generateStructure(ClassWithoutNesting.class);

        assertEquals("name code available", structure);
    }

    @Test
    public void testGenerateStructureClassWithSimpleNesting() {
        String structure = ClassToGraphQLAttributesUtil.generateStructure(ClassWithSimpleNesting.class);

        assertEquals("name code available operations { name offset}", structure);
    }

    @Test
    public void testGenerateStructureClassWithMultipleNesting() {
        String structure = ClassToGraphQLAttributesUtil.generateStructure(ClassWithMultipleNesting.class);

        assertEquals(
                "name code available operations { name offset} tasks { description category details { durationInSeconds historyPoints}}",
                structure
        );
    }

}