package io.entraos.rec.domain;

public class Floor extends RealEstateCore{
    public static final String REC_CLASS = "https://w3id.org/rec/building/Storey";

    private String uuid;
    private String name;  //Eg 10
    private String locatedInUuid; //UUID to BuildingComponent or Building

    public Floor(String uuid, String name, String tag, String locatedInUuid) {
        super(REC_CLASS, uuid, name, tag);
        this.locatedInUuid = locatedInUuid;
    }

    public String getLocatedInUuid() {
        return locatedInUuid;
    }
}
