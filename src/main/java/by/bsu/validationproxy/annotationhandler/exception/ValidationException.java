package by.bsu.validationproxy.annotationhandler.exception;

public class ValidationException extends Exception
{
    public ValidationException()
    {
        super();
    }

    public ValidationException(final String description)
    {
        super(description);
    }

    public ValidationException(final Exception cause)
    {
        super(cause);
    }

    public ValidationException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
