package org.isti.learning.springboot.rest;

import org.isti.learning.springboot.rest.dto.ApplicationVersion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Version {
    @GetMapping(value = "/version", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<ApplicationVersion> version() {
        return new ResponseEntity<>(new ApplicationVersion("0.1.0-SNAPSHOT"), HttpStatus.OK);
    }
}
