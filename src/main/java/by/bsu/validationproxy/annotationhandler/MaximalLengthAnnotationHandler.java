package by.bsu.validationproxy.annotationhandler;

import by.bsu.validationproxy.annotation.MaximalLength;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.util.Objects;
import java.util.function.Supplier;

public final class MaximalLengthAnnotationHandler extends ValidationAnnotationHandler<MaximalLength>
{
    public MaximalLengthAnnotationHandler(final MaximalLength maximalLengthHandledAnnotation)
    {
        super(maximalLengthHandledAnnotation);
    }

    @Override
    public final void handle(final Object newValueOfFieldObject)
            throws ValidationException
    {
        final MaximalLength maximalLengthHandledAnnotation = super.getHandledAnnotation();
        final int maximalAllowableLength = maximalLengthHandledAnnotation.maximalLength();
        final String newValueOfField = (String)newValueOfFieldObject;
        final int lengthOfNewValueOfField = newValueOfField.length();
        if(lengthOfNewValueOfField > maximalAllowableLength)
        {
            final Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass
                    = maximalLengthHandledAnnotation.exceptionSupplierClass();
            super.throwValidationException(exceptionSupplierClass);
        }
    }
}