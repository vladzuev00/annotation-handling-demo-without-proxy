package by.bsu.validationproxy.annotationhandler;

import by.bsu.validationproxy.annotation.Pattern;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.util.function.Supplier;

public final class PatternAnnotationHandler extends ValidationAnnotationHandler<Pattern>
{
    public PatternAnnotationHandler(final Pattern patternHandledAnnotation)
    {
        super(patternHandledAnnotation);
    }

    @Override
    public final void handle(final Object newValueOfFieldObject)
            throws ValidationException
    {
        final Pattern patternHandledAnnotation = super.getHandledAnnotation();
        final String regularExpression = patternHandledAnnotation.regularExpression();
        final String newValueOfField = (String)newValueOfFieldObject;
        if(!newValueOfField.matches(regularExpression))
        {
            final Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass
                    = patternHandledAnnotation.exceptionSupplierClass();
            super.throwValidationException(exceptionSupplierClass);
        }
    }
}