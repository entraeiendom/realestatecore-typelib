package io.entraos.rec.domain;

public class Room {
    public static final String CLASS = "https://w3id.org/rec/core/Room";

    private String uuid;
    private String name;  //Eg 10104
    private String locatedInUuid; //UUID to BuildingComponent/Storey/Floor
    private String tag;

    public Room withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Room withName(String name) {
        this.name = name;
        return this;
    }

    public Room withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Room locatedIn(String buildingComponentUuid) {
        this.locatedInUuid = buildingComponentUuid;
        return this;
    }
}
