package com.example.sport_api.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonPropertyOrder({ "venueId", "name", "address", "city", "zip", "country", "open", "nickname1", "nickname2",
        "capacity", "surface", "geoLat", "geoLong" })
public class Venue {

    @Id
    private Integer VenueId;
    private String Name;
    private String Address;
    private String City;
    private String Zip;
    private String Country;
    private String Open;
    private Integer Opened;
    private String Nickname1;
    private String Nickname2;
    private Integer Capacity;
    private String Surface;
    private Integer GeoLat;
    private Integer GeoLong;

    public Venue() {
    }

    public Venue(Integer venueId, String name, String address, String city, String zip, String country, String open,
            Integer opened, String nickname1, String nickname2, Integer capacity, String surface, Integer geoLat,
            Integer geoLong) {
        VenueId = venueId;
        Name = name;
        Address = address;
        City = city;
        Zip = zip;
        Country = country;
        Open = open;
        Opened = opened;
        Nickname1 = nickname1;
        Nickname2 = nickname2;
        Capacity = capacity;
        Surface = surface;
        GeoLat = geoLat;
        GeoLong = geoLong;
    }

    public Integer getVenueId() {
        return VenueId;
    }

    public void setVenueId(Integer venueId) {
        VenueId = venueId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public Integer getOpened() {
        return Opened;
    }

    public void setOpened(Integer opened) {
        Opened = opened;
    }

    public String getNickname1() {
        return Nickname1;
    }

    public void setNickname1(String nickname1) {
        Nickname1 = nickname1;
    }

    public String getNickname2() {
        return Nickname2;
    }

    public void setNickname2(String nickname2) {
        Nickname2 = nickname2;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public String getSurface() {
        return Surface;
    }

    public void setSurface(String surface) {
        Surface = surface;
    }

    public Integer getGeoLat() {
        return GeoLat;
    }

    public void setGeoLat(Integer geoLat) {
        GeoLat = geoLat;
    }

    public Integer getGeoLong() {
        return GeoLong;
    }

    public void setGeoLong(Integer geoLong) {
        GeoLong = geoLong;
    }

}
