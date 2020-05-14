package io.entraos.rec.domain;

public class Room extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/core/Room";

    private String uuid;
    private String name;  //Eg 10104
    private String tag;
    private String locatedInUuid; //UUID to BuildingComponent/Storey/Floor
    private String floorUuid;

    public Room(String uuid, String name, String tag, String locatedInUuid, String floorUuid) {
        super(REC_CLASS, uuid, name, tag);
        this.locatedInUuid = locatedInUuid;
        this.floorUuid = floorUuid;
    }

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

    public String getLocatedInUuid() {
        return locatedInUuid;
    }

    public String getFloorUuid() {
        return floorUuid;
    }
}
