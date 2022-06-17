package by.bsu.validationproxy.annotationhandler;

import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public abstract class ValidationAnnotationHandler<TypeOfAnnotation extends Annotation>
{
    private final TypeOfAnnotation handledAnnotation;

    public ValidationAnnotationHandler(final TypeOfAnnotation handledAnnotation)
    {
        super();
        this.handledAnnotation = handledAnnotation;
    }

    public final TypeOfAnnotation getHandledAnnotation()
    {
        return this.handledAnnotation;
    }

    public final void throwValidationException(
            final Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass)
            throws ValidationException
    {
        try
        {
            final Constructor<? extends Supplier<? extends ValidationException>> defaultConstructorExceptionSupplierClass
                    = exceptionSupplierClass.getConstructor();
            final Supplier<? extends ValidationException> exceptionSupplier
                    = defaultConstructorExceptionSupplierClass.newInstance();
            throw exceptionSupplier.get();
        }
        catch(final NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException cause)
        {
            throw new ValidationException(cause);
        }
    }

    public abstract void handle(final Object newValueOfObject)
            throws ValidationException;
}