package by.bsu.validationproxy.annotation;

import by.bsu.validationproxy.annotationhandler.MinimalLengthAnnotationHandler;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@HandledBy(handlerClass = MinimalLengthAnnotationHandler.class)
public @interface MinimalLength
{
    public int minimalLength();
    public Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass()
            default DefaultExceptionSupplier.class;

    public static final class DefaultExceptionSupplier implements Supplier<MinimalLengthValidationException>
    {
        public DefaultExceptionSupplier()
        {
            super();
        }

        @Override
        public final MinimalLengthValidationException get()
        {
            return new MinimalLengthValidationException();
        }
    }

    public static final class MinimalLengthValidationException extends ValidationException
    {
        public MinimalLengthValidationException()
        {
            super();
        }

        public MinimalLengthValidationException(final String description)
        {
            super(description);
        }

        public MinimalLengthValidationException(final Exception cause)
        {
            super(cause);
        }

        public MinimalLengthValidationException(final String description, final Exception cause)
        {
            super(description, cause);
        }
    }
}
