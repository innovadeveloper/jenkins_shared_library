package com.shared.library.models

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PomModel {
    private Map<String, JsonNode> elements = new HashMap<>();

    @JsonAnySetter
    public void addElement(String name, JsonNode value) {
        elements.put(name, value);
    }

    // Resto del código de la clase

    public Map<String, JsonNode> getElements() {
        return elements;
    }

    public void setElements(Map<String, JsonNode> elements) {
        this.elements = elements;
    }

    @JacksonXmlProperty(isAttribute = true)
    private String schemaLocation;

    @JacksonXmlProperty(localName = "version")
    private String version;

    // Resto del código de la clase

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    @JacksonXmlProperty(localName = "version")
//    public String version;
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
}