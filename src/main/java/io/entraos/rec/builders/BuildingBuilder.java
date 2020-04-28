package io.entraos.rec.builders;

import io.entraos.rec.domain.Building;

public class BuildingBuilder extends AbstractRealEstateCoreBuilder<BuildingBuilder, Building> {


    private String realestateUuid;

    public BuildingBuilder locatedIn(String realestateUuid) {
        this.realestateUuid = realestateUuid;
        return this;
    }

    @Override
    protected Building internalBuild() {
        return new Building(this.uuid, this.name, this.tag, this.realestateUuid);
    }

}
