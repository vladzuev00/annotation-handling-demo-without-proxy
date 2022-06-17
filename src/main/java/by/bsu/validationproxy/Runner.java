package by.bsu.validationproxy;

import by.bsu.validationproxy.annotationhandler.exception.ValidationException;
import by.bsu.validationproxy.entity.Person;
import by.bsu.validationproxy.validator.Validator;

public class Runner
{
    public static void main(String[] args)
            throws ValidationException
    {
        final Person person = new Person();
        person.setDescription("description");
        person.setName("name");
        person.setSurname("surname");
        person.setEmail("vladzuev.00@mail.ru");

        final Validator<Person> validator = new Validator<>();
        validator.validate(person);
    }
}
