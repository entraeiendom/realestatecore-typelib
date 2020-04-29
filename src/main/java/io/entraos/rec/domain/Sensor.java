package io.entraos.rec.domain;

public class Sensor extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/device/Sensor";

    private String mountedOnDeviceUuid;
    private String factoryId; //Mac address

    public Sensor() {
        super(REC_CLASS);
    }

    public Sensor(String uuid, String name, String tag, String mountedOnDeviceUuid, String factoryId) {
        super(REC_CLASS, uuid, name, tag);
        this.mountedOnDeviceUuid = mountedOnDeviceUuid;
        this.factoryId = factoryId;
    }

    public Sensor onDevice(String deviceUuid) {
        this.mountedOnDeviceUuid = deviceUuid;
        return this;
    }

    public Sensor withFactoryId(String factoryId) {
        this.factoryId = factoryId;
        return this;
    }

    public String getMountedOnDeviceUuid() {
        return mountedOnDeviceUuid;
    }

    public void setMountedOnDeviceUuid(String mountedOnDeviceUuid) {
        this.mountedOnDeviceUuid = mountedOnDeviceUuid;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }
}
