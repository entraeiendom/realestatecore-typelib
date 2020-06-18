package io.entraos.rec.builders;

import io.entraos.rec.domain.Floor;

public class FloorBuilder extends AbstractRealEstateCoreBuilder<FloorBuilder, Floor> {


    private String buildingUuid;

    public FloorBuilder inBuilding(String buildingUuid) {
        this.buildingUuid = buildingUuid;
        return this;
    }

    @Override
    protected Floor internalBuild() {
        Floor floor = new Floor(this.uuid, this.name, this.tag, this.buildingUuid);
        if (this.rawJson != null) {
            floor.setRawJson(this.rawJson);
        }
        return floor;
    }

}
