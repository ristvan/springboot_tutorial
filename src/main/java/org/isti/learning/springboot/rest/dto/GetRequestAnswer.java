package org.isti.learning.springboot.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.LinkedList;
import java.util.List;

@JsonPropertyOrder(value = {"numberOfPeople", "people"})
@JacksonXmlRootElement(localName = "answer")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetRequestAnswer {
    @JsonProperty
    @JacksonXmlElementWrapper(localName = "people")
    @JacksonXmlProperty(localName = "person")
    private List<PersonDto> people = new LinkedList<>();

    @JsonProperty(value = "numberOfPeople")
    public int getNumberOfPerson() {
        return people.size();
    }

    public void addPerson(PersonDto person) {
        people.add(person);
    }

}
