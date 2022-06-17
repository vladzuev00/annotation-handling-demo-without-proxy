package by.bsu.validationproxy.entity;

import by.bsu.validationproxy.annotation.Pattern;

public class Entity
{
    @Pattern(regularExpression = ".+")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
