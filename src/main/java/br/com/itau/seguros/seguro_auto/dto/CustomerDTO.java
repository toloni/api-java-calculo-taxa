package br.com.itau.seguros.seguro_auto.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private String name;
    private String document;
    private LocalDate birthday;
    private String location;
    private String vehicle_value;
    private String value;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String document, LocalDate birthday, String location, String vehicle_value,
                       String value) {
        this.name = name;
        this.document = document;
        this.birthday = birthday;
        this.location = location;
        this.vehicle_value = vehicle_value;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicle_value() {
        return vehicle_value;
    }

    public void setVehicle_value(String vehicle_value) {
        this.vehicle_value = vehicle_value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
