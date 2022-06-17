package by.bsu.validationproxy.fieldsofclassfounder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class FieldsOfClassFounder
{
    public static FieldsOfClassFounder createFieldsOfClassFounder()
    {
        if(FieldsOfClassFounder.fieldsOfClassFounder == null)
        {
            synchronized(FieldsOfClassFounder.class)
            {
                if(FieldsOfClassFounder.fieldsOfClassFounder == null)
                {
                    FieldsOfClassFounder.fieldsOfClassFounder = new FieldsOfClassFounder();
                }
            }
        }
        return FieldsOfClassFounder.fieldsOfClassFounder;
    }

    private static FieldsOfClassFounder fieldsOfClassFounder = null;

    private FieldsOfClassFounder()
    {
        super();
    }

    public final Set<Field> findFieldsOfType(final Class<?> type)
    {
        final Set<Field> fieldsOfType = new LinkedHashSet<Field>();
        Class<?> runnerType = type;
        Field[] fieldsOfCurrentRunnerType;
        while(runnerType != Object.class)
        {
            fieldsOfCurrentRunnerType = runnerType.getDeclaredFields();
            fieldsOfType.addAll(Arrays.asList(fieldsOfCurrentRunnerType));
            runnerType = runnerType.getSuperclass();
        }
        return fieldsOfType;
    }
}