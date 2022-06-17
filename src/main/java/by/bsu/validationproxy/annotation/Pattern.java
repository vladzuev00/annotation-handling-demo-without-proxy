package by.bsu.validationproxy.annotation;

import by.bsu.validationproxy.annotationhandler.PatternAnnotationHandler;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@HandledBy(handlerClass = PatternAnnotationHandler.class)
public @interface Pattern
{
    public String regularExpression();
    public Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass()
            default DefaultExceptionSupplier.class;

    public static final class DefaultExceptionSupplier implements Supplier<PatternValidationException>
    {
        public DefaultExceptionSupplier()
        {
            super();
        }

        @Override
        public final PatternValidationException get()
        {
            return new PatternValidationException();
        }
    }

    public static final class PatternValidationException extends ValidationException
    {
        public PatternValidationException()
        {
            super();
        }

        public PatternValidationException(final String description)
        {
            super(description);
        }

        public PatternValidationException(final Exception cause)
        {
            super(cause);
        }

        public PatternValidationException(final String description, final Exception cause)
        {
            super(description, cause);
        }
    }
}