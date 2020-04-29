package io.entraos.rec.domain;

public class Building extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/core/Building";

    private String realestateUuid; //UUID to RealEstate

    public Building() {
        super(REC_CLASS);
    }

    public Building(String uuid, String name, String tag, String realestateUuid) {
        super(REC_CLASS, uuid, name, tag);
        this.realestateUuid = realestateUuid;
    }
    public String getRealestateUuid() {
        return realestateUuid;
    }

    public String getLocatedInRealestateUuid() {
        return realestateUuid;
    }

}
