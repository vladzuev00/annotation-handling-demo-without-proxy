package by.bsu.validationproxy.validator;

import by.bsu.validationproxy.annotation.HandledBy;
import by.bsu.validationproxy.annotationhandler.ValidationAnnotationHandler;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;
import by.bsu.validationproxy.fieldsofclassfounder.FieldsOfClassFounder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public final class Validator<TypeOfValidatedObject>
{
    private final FieldsOfClassFounder fieldsOfClassFounder;

    public Validator()
    {
        super();
        this.fieldsOfClassFounder = FieldsOfClassFounder.createFieldsOfClassFounder();
    }

    public final void validate(final TypeOfValidatedObject validatedObject)
            throws ValidationException
    {
        try{
            final Class<?> typeOfValidatedObject = validatedObject.getClass();
            final Set<Field> fieldsOfValidatedObject = this.fieldsOfClassFounder.findFieldsOfType(typeOfValidatedObject);

            for(final Field validatedField : fieldsOfValidatedObject)
            {
                final Annotation[] validatedFieldAnnotations = validatedField.getAnnotations();
                for(final Annotation validatedFieldAnnotation : validatedFieldAnnotations)
                {
                    final Class<? extends Annotation> annotationType = validatedFieldAnnotation.annotationType();
                    final HandledBy handledByAnnotation = annotationType.getAnnotation(HandledBy.class);
                    final Class<? extends ValidationAnnotationHandler<? extends Annotation>> classOfValidationAnnotationHandler
                            = handledByAnnotation.handlerClass();
                    final Constructor<? extends ValidationAnnotationHandler<? extends Annotation>> defaultConstructor
                            = classOfValidationAnnotationHandler.getConstructor(annotationType);
                    final ValidationAnnotationHandler<? extends Annotation> validationAnnotationHandler
                            = defaultConstructor.newInstance(validatedFieldAnnotation);

                    validatedField.setAccessible(true);
                    final Object valueOfField;
                    try
                    {
                        valueOfField = validatedField.get(validatedObject);
                    }
                    finally
                    {
                        validatedField.setAccessible(false);
                    }
                    validationAnnotationHandler.handle(valueOfField);
                }
            }
        }
        catch(final NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException cause)
        {
            throw new RuntimeException(cause);    //TODO: replace exception
        }
    }
}
