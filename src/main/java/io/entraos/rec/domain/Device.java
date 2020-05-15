package io.entraos.rec.domain;

public class Device extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/core/Device";

    private String mountedInBuildingComponentUuid; //UUID to BuildingComponent/Room


    public Device(String uuid, String name, String tag, String mountedInBuildingComponentUuid) {
        super(REC_CLASS, uuid, name, tag);
        this.mountedInBuildingComponentUuid = mountedInBuildingComponentUuid;
    }
}
