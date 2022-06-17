package by.bsu.validationproxy.annotation;

import by.bsu.validationproxy.annotationhandler.ValidationAnnotationHandler;

import java.lang.annotation.*;

@Target(value = {ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HandledBy
{
    public Class<? extends ValidationAnnotationHandler<? extends Annotation>> handlerClass();
}