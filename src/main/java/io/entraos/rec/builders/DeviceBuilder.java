package io.entraos.rec.builders;

import io.entraos.rec.domain.Device;

public class DeviceBuilder extends AbstractRealEstateCoreBuilder<DeviceBuilder, Device> {


    private String mountedInBuildingComponentUuid;
    private String factoryId; //Mac address

    public DeviceBuilder mountedInBuildingComponent(String mountedInBuildingComponentUuid) {
        this.mountedInBuildingComponentUuid = mountedInBuildingComponentUuid;
        return this;
    }

    public DeviceBuilder withFactoryId(String factoryId) {
        this.factoryId = factoryId;
        return this;
    }

    @Override
    protected Device internalBuild() {
        return new Device(this.uuid, this.name, this.tag, this.mountedInBuildingComponentUuid);
    }

}
