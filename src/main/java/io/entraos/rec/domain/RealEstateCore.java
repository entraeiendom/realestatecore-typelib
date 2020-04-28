package io.entraos.rec.domain;

abstract class RealEstateCore {

    private final String recClass;
    private String uuid;
    private String name;  //Eg 10104
    private String locatedInUuid; //UUID to BuildingComponent/Storey/Floor
    private String tag;

    protected RealEstateCore(String recClass) {
        this.recClass = recClass;
    }

    public RealEstateCore(String recClass, String uuid, String name, String tag) {
        this.recClass = recClass;
        this.uuid = uuid;
        this.name = name;
        this.tag = tag;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocatedInUuid() {
        return locatedInUuid;
    }

    public void setLocatedInUuid(String locatedInUuid) {
        this.locatedInUuid = locatedInUuid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRecClass() {
        return recClass;
    }
}
