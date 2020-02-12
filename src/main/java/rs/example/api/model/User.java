package rs.example.api.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO to represent a user entity
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "username", "email", "address", "phone", "website", "company"})
public class User {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("website")
    private String website;

    @JsonProperty("company")
    private Company company;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("company")
    public Company getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(Company company) {
        this.company = company;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"street", "suite", "city", "zipcode", "geo"})
    public static class Address {

        @JsonProperty("street")
        private String street;

        @JsonProperty("suite")
        private String suite;

        @JsonProperty("city")
        private String city;

        @JsonProperty("zipcode")
        private String zipcode;

        @JsonProperty("geo")
        private Geo geo;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<>();

        @JsonProperty("street")
        public String getStreet() {
            return street;
        }

        @JsonProperty("street")
        public void setStreet(String street) {
            this.street = street;
        }

        @JsonProperty("suite")
        public String getSuite() {
            return suite;
        }

        @JsonProperty("suite")
        public void setSuite(String suite) {
            this.suite = suite;
        }

        @JsonProperty("city")
        public String getCity() {
            return city;
        }

        @JsonProperty("city")
        public void setCity(String city) {
            this.city = city;
        }

        @JsonProperty("zipcode")
        public String getZipcode() {
            return zipcode;
        }

        @JsonProperty("zipcode")
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        @JsonProperty("geo")
        public Geo getGeo() {
            return geo;
        }

        @JsonProperty("geo")
        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"name", "catchPhrase", "bs"})
    public static class Company {

        @JsonProperty("name")
        private String name;

        @JsonProperty("catchPhrase")
        private String catchPhrase;

        @JsonProperty("bs")
        private String bs;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<>();

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("catchPhrase")
        public String getCatchPhrase() {
            return catchPhrase;
        }

        @JsonProperty("catchPhrase")
        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        @JsonProperty("bs")
        public String getBs() {
            return bs;
        }

        @JsonProperty("bs")
        public void setBs(String bs) {
            this.bs = bs;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"lat", "lng"})
    public static class Geo {

        @JsonProperty("lat")
        private String lat;

        @JsonProperty("lng")
        private String lng;

        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<>();

        @JsonProperty("lat")
        public String getLat() {
            return lat;
        }

        @JsonProperty("lat")
        public void setLat(String lat) {
            this.lat = lat;
        }

        @JsonProperty("lng")
        public String getLng() {
            return lng;
        }

        @JsonProperty("lng")
        public void setLng(String lng) {
            this.lng = lng;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
