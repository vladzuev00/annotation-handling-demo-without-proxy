package by.bsu.validationproxy.entity;

import by.bsu.validationproxy.annotation.MaximalLength;
import by.bsu.validationproxy.annotation.MinimalLength;
import by.bsu.validationproxy.annotation.Pattern;
import by.bsu.validationproxy.annotation.Valid;

import java.util.Objects;

public class Person extends Entity
{
    @Pattern(regularExpression = "[a-zA-Z ]+")
    @MinimalLength(minimalLength = 3)
    @MaximalLength(maximalLength = 256)
    private String name;

    @Pattern(regularExpression = "[a-zA-Z ]+")
    @MinimalLength(minimalLength = 3)
    @MaximalLength(maximalLength = 256)
    private String surname;

    @Pattern(regularExpression = "[a-zA-Z0-9_.]+@[a-zA-Z]+\\.((ru)|(com)|(by))")
    @MinimalLength(minimalLength = 15)
    @MaximalLength(maximalLength = 256)
    private String email;

    public Person()
    {
        super();
        this.name = Person.VALUE_OF_NOT_DEFINED_NAME;
        this.surname = Person.VALUE_OF_NOT_DEFINED_SURNAME;
        this.email = Person.VALUE_OF_NOT_DEFINED_EMAIL;
    }

    private static final String VALUE_OF_NOT_DEFINED_NAME = "not defined";
    private static final String VALUE_OF_NOT_DEFINED_SURNAME = "not defined";
    private static final String VALUE_OF_NOT_DEFINED_EMAIL = "not defined";

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setSurname(final String surname)
    {
        this.surname = surname;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    @Override
    public boolean equals(final Object otherObject)
    {
        if(this == otherObject)
        {
            return true;
        }
        if(otherObject == null)
        {
            return false;
        }
        if(this.getClass() != otherObject.getClass())
        {
            return false;
        }
        final Person other = (Person)otherObject;
        return     Objects.equals(this.name, other.name)
                && Objects.equals(this.surname, other.surname)
                && Objects.equals(this.email, other.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.name, this.surname, this.email);
    }

    @Override
    public String toString()
    {
        return this.getClass().getName() + "[name = " + this.name + ", surname = " + this.surname
                + ", email = " + this.email + "]";
    }
}
