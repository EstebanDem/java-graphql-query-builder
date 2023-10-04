package dem.esteban.graphql.query.builder.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class ClassToGraphQLAttributesUtil {

    private ClassToGraphQLAttributesUtil() {

    }

    public static String generateStructure(Class<?> clazz) {
        String structure = generateFieldStructure(clazz);
        return structure;
    }

    private static String generateFieldStructure(Class<?> clazz) {
        StringBuilder result = new StringBuilder();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            result.append("").append(field.getName()).append(" ");

            if (field.getType().getTypeParameters().length > 0) {
                result.append("{ ");
                Type fieldType = field.getGenericType();

                if (fieldType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) fieldType;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

                    if (actualTypeArguments.length > 0) {
                        Class<?> elementType = (Class<?>) actualTypeArguments[0];
                        String nestedStructure = generateFieldStructure(elementType);
                        result.append(nestedStructure);
                    }
                }

                result.append("} ");
            }
        }

        return result.toString().trim();
    }
}
