package io.entraos.rec.domain;

public class Room extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/core/Room";

    private String locatedInUuid; //UUID to BuildingComponent/Storey/Floor
    private String floorUuid;

    public Room(String uuid, String name, String tag, String locatedInUuid, String floorUuid) {
        super(REC_CLASS, uuid, name, tag);
        this.locatedInUuid = locatedInUuid;
        this.floorUuid = floorUuid;
    }

    public String getLocatedInUuid() {
        return locatedInUuid;
    }

    public String getFloorUuid() {
        return floorUuid;
    }

    public String findRoomName() {
        String roomName = null;
        if (getName() == null) {
            roomName = getTag();
        } else {
            roomName = getName();
        }
        return roomName;
    }
}
