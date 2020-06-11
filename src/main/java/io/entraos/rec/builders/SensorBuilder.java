package io.entraos.rec.builders;

import io.entraos.rec.domain.Sensor;

public class SensorBuilder extends AbstractRealEstateCoreBuilder<SensorBuilder, Sensor> {


    private String mountedOnDeviceUuid;
    private String moundedInBuildingComponent;
    private String factoryId; //Mac address

    public SensorBuilder onDevice(String deviceUuid) {
        this.mountedOnDeviceUuid = deviceUuid;
        return this;
    }

    public SensorBuilder inBuildingComponent(String buildingComponentUuid) {
        this.moundedInBuildingComponent = buildingComponentUuid;
        return this;
    }

    public SensorBuilder withFactoryId(String factoryId) {
        this.factoryId = factoryId;
        return this;
    }

    @Override
    protected Sensor internalBuild() {
        return new Sensor(this.uuid, this.name, this.tag, this.mountedOnDeviceUuid, this.moundedInBuildingComponent, this.factoryId);
    }

}
