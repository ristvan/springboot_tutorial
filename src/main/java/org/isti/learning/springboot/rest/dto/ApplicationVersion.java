package org.isti.learning.springboot.rest.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "application")
public class ApplicationVersion {
    public String name = "SpringBoot Learning";

    @JacksonXmlProperty
    public String version;

    public ApplicationVersion(String version) {
        this.version = version;
    }
}
