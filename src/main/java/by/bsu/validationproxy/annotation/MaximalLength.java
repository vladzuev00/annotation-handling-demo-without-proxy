package by.bsu.validationproxy.annotation;

import by.bsu.validationproxy.annotationhandler.MaximalLengthAnnotationHandler;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@HandledBy(handlerClass = MaximalLengthAnnotationHandler.class)
public @interface MaximalLength
{
    public int maximalLength();
    public Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass()
            default DefaultExceptionSupplier.class;

    public static final class DefaultExceptionSupplier implements Supplier<MaximalLengthValidationException>
    {
        public DefaultExceptionSupplier()
        {
            super();
        }

        @Override
        public final MaximalLengthValidationException get()
        {
            return new MaximalLengthValidationException();
        }
    }

    public static final class MaximalLengthValidationException extends ValidationException
    {
        public MaximalLengthValidationException()
        {
            super();
        }

        public MaximalLengthValidationException(final String description)
        {
            super(description);
        }

        public MaximalLengthValidationException(final Exception cause)
        {
            super(cause);
        }

        public MaximalLengthValidationException(final String description, final Exception cause)
        {
            super(description, cause);
        }
    }
}