package io.entraos.rec.domain;

public class BuildingBuilder implements RealEstateCoreBuilder {
    String name;

    /*
    public T withUuid(String uuid) {
        this.uuid = uuid;
        return t;
    }

    public T withName(String name) {
        this.name = name;
        return t;
    }

    public T withTag(String tag) {
        this.tag = tag;
        return t;
    }
     */

    @Override
    public RealEstateCoreBuilder<Building> withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Building build() {
        return new Building(null,name, null);
    }
}
