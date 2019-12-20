package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicantDetails {
    @JsonProperty
    private String name;

    @JsonProperty
    private String street;

    @JsonProperty
    private String town;

    @JsonProperty
    private String city;

    @JsonProperty
    private String postcode;

    public ApplicantDetails() {
    }

    public ApplicantDetails(String name, String street, String town, String city, String postcode) {
        this.name = name;
        this.street = street;
        this.town = town;
        this.city = city;
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
