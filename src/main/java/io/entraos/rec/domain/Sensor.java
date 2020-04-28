package io.entraos.rec.domain;

public class Sensor {
    public static final String CLASS = "https://w3id.org/rec/device/Sensor";

    private String uuid;
    private String name;  //Eg T01
    private String mountedOnDeviceUuid;
    private String tag; //Eg the Norwegian TFM code
    private String factoryId; //Mac address

    public Sensor withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    public Sensor withName(String name) {
        this.name = name;
        return this;
    }

    public Sensor withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Sensor onDevice(String deviceUuid) {
        this.mountedOnDeviceUuid = deviceUuid;
        return this;
    }

    public Sensor withFactoryId(String factoryId) {
        this.factoryId = factoryId;
        return this;
    }



}
