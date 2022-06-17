package by.bsu.validationproxy.annotationhandler;

import by.bsu.validationproxy.annotation.MinimalLength;
import by.bsu.validationproxy.annotationhandler.exception.ValidationException;

import java.util.function.Supplier;

public final class MinimalLengthAnnotationHandler extends ValidationAnnotationHandler<MinimalLength>
{
    public MinimalLengthAnnotationHandler(final MinimalLength minimalLengthHandledAnnotation)
    {
        super(minimalLengthHandledAnnotation);
    }

    @Override
    public final void handle(final Object newValueOfFieldObject)
            throws ValidationException
    {
        final MinimalLength minimalLengthHandledAnnotation = super.getHandledAnnotation();
        final int minimalAllowableLength = minimalLengthHandledAnnotation.minimalLength();
        final String newValueOfField = (String)newValueOfFieldObject;
        final int lengthOfNewValueOfField = newValueOfField.length();
        if(lengthOfNewValueOfField < minimalAllowableLength)
        {
            final Class<? extends Supplier<? extends ValidationException>> exceptionSupplierClass
                    = minimalLengthHandledAnnotation.exceptionSupplierClass();
            super.throwValidationException(exceptionSupplierClass);
        }
    }
}