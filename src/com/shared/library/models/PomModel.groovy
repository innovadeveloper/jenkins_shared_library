package com.shared.library.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PomModel {
    @JacksonXmlProperty(localName = "version")
    public String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    String toString(){
        def objectMapper = new ObjectMapper()
        def jsonString = objectMapper.writeValueAsString(this)
        return jsonString
    }
}