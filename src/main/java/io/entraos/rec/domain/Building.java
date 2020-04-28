package io.entraos.rec.domain;

public class Building extends RealEstateCore {
    public static final String CLASS = "https://w3id.org/rec/core/Building";

    private String uuid;
    private String name;  //Eg Building A
    private String realestateUuid; //UUID to RealEstate


    public Building() {
        super(CLASS);
    }

    public Building(String uuid, String name, String tag) {
        super(CLASS, uuid, name, tag);
    }

    public Building locatedIn(String realestateUuid) {
        this.realestateUuid = realestateUuid;
        return this;
    }
}
