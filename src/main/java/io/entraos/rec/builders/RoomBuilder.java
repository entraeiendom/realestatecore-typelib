package io.entraos.rec.builders;

import io.entraos.rec.domain.Room;

public class RoomBuilder extends AbstractRealEstateCoreBuilder<RoomBuilder, Room> {


    private String realestateUuid;
    private String floorUuid;

    public RoomBuilder inBuilding(String realestateUuid) {
        this.realestateUuid = realestateUuid;
        return this;
    }

    public RoomBuilder atFloor(String floorUuid) {
        this.floorUuid = floorUuid;
        return this;
    }

    @Override
    protected Room internalBuild() {
        return new Room(this.uuid, this.name, this.tag, this.realestateUuid, floorUuid);
    }

}
