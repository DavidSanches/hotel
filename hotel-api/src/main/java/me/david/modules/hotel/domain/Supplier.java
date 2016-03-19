package me.david.modules.hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * This ENtiry represents a party.
 * Person
 * Created by rdas on 14/03/2016.
 */
@Entity
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String partyTinNumber;


    @Column(nullable = false)
    private String mainEmail;


    @Column(nullable = false)
    private String alternativeEmail;


    @Column(nullable = false)
    private String person;


    @Column(nullable = false)
    private String alternativePerson;


    @Column(nullable = false)
    private String mainPhone;


    @Column(nullable = false)
    private String alternativePhone;


    @Column(nullable = false)
    private String address;


    @Column(nullable = false)
    private String city;


    @Column(nullable = false)
    private String state;


    @Column(nullable = false)
    private String country;


    @Column(nullable = false)
    private String termsAndConditions;

    public Supplier() {
        super();
    }

    public Supplier(String name,
                    String partyTinNumber,
                    String mainEmail,
                    String alternativeEmail,
                    String person,
                    String alternativePerson,
                    String mainPhone,
                    String alternativePhone,
                    String address,
                    String city,
                    String state,
                    String country,
                    String termsAndConditions) {
        super();
        this.name = name;
        this.partyTinNumber = partyTinNumber;
        this.mainEmail = mainEmail;
        this.alternativeEmail = alternativeEmail;
        this.person = person;
        this.alternativePerson = alternativePerson;
        this.mainPhone = mainPhone;
        this.alternativePhone = alternativePhone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.termsAndConditions = termsAndConditions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyTinNumber() {
        return partyTinNumber;
    }

    public void setPartyTinNumber(String partyTinNumber) {
        this.partyTinNumber = partyTinNumber;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public String getAlternativeEmail() {
        return alternativeEmail;
    }

    public void setAlternativeEmail(String alternativeEmail) {
        this.alternativeEmail = alternativeEmail;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAlternativePerson() {
        return alternativePerson;
    }

    public void setAlternativePerson(String alternativePerson) {
        this.alternativePerson = alternativePerson;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAlternativePhone() {
        return alternativePhone;
    }

    public void setAlternativePhone(String alternativePhone) {
        this.alternativePhone = alternativePhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;
        if (partyTinNumber != null ? !partyTinNumber.equals(supplier.partyTinNumber) : supplier.partyTinNumber != null)
            return false;
        if (mainEmail != null ? !mainEmail.equals(supplier.mainEmail) : supplier.mainEmail != null) return false;
        if (alternativeEmail != null ? !alternativeEmail.equals(supplier.alternativeEmail) : supplier.alternativeEmail != null)
            return false;
        if (person != null ? !person.equals(supplier.person) : supplier.person != null) return false;
        if (alternativePerson != null ? !alternativePerson.equals(supplier.alternativePerson) : supplier.alternativePerson != null)
            return false;
        if (mainPhone != null ? !mainPhone.equals(supplier.mainPhone) : supplier.mainPhone != null) return false;
        if (alternativePhone != null ? !alternativePhone.equals(supplier.alternativePhone) : supplier.alternativePhone != null)
            return false;
        if (address != null ? !address.equals(supplier.address) : supplier.address != null) return false;
        if (city != null ? !city.equals(supplier.city) : supplier.city != null) return false;
        if (state != null ? !state.equals(supplier.state) : supplier.state != null) return false;
        if (country != null ? !country.equals(supplier.country) : supplier.country != null) return false;
        return !(termsAndConditions != null ? !termsAndConditions.equals(supplier.termsAndConditions) : supplier.termsAndConditions != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (partyTinNumber != null ? partyTinNumber.hashCode() : 0);
        result = 31 * result + (mainEmail != null ? mainEmail.hashCode() : 0);
        result = 31 * result + (alternativeEmail != null ? alternativeEmail.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (alternativePerson != null ? alternativePerson.hashCode() : 0);
        result = 31 * result + (mainPhone != null ? mainPhone.hashCode() : 0);
        result = 31 * result + (alternativePhone != null ? alternativePhone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (termsAndConditions != null ? termsAndConditions.hashCode() : 0);
        return result;
    }
}
