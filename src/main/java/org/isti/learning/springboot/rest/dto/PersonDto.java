package org.isti.learning.springboot.rest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "person")
public class PersonDto {
    @JacksonXmlProperty
    public int id;
    @JacksonXmlProperty
    public String name;
}
