package io.entraos.rec.domain;

public class Building extends RealEstateCore {
    public static final String CLASS = "https://w3id.org/rec/core/Building";

    private String realestateUuid; //UUID to RealEstate


    public Building() {
        super(CLASS);
    }

    public Building(String uuid, String name, String tag, String realestateUuid) {
        super(CLASS, uuid, name, tag);
        this.realestateUuid = realestateUuid;
    }

    public Building locatedIn(String realestateUuid) {
        this.realestateUuid = realestateUuid;
        return this;
    }

    public String getRealestateUuid() {
        return realestateUuid;
    }

    public String getLocatedInRealestateUuid() {
        return realestateUuid;
    }

}
