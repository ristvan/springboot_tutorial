package org.isti.learning.springboot.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.LinkedList;
import java.util.List;

@JsonPropertyOrder(value = {"numberOfPeople", "people"})
@JacksonXmlRootElement(localName = "person")
public class GetRequestAnswer {
    @JsonProperty
    private List<PersonDto> people = new LinkedList<>();

    @JsonProperty(value = "numberOfPeople")
    public int getNumberOfPerson() {
        return people.size();
    }

    public void addPerson(PersonDto person) {
        people.add(person);
    }

}
