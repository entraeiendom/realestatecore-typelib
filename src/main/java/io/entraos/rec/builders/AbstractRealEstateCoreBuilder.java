package io.entraos.rec.builders;

import io.entraos.rec.domain.RealEstateCore;

public abstract class AbstractRealEstateCoreBuilder<SELF extends RealEstateCoreBuilder<SELF, TTarget>,
        TTarget extends RealEstateCore> implements RealEstateCoreBuilder<SELF, TTarget> {

    String uuid;
    String name;  //Eg 10104
    String tag;

    @Override
    public SELF withName(String name) {
        this.name = name;
        return self();
    }

    @Override
    public SELF withUuid(String uuid) {
        this.uuid = uuid;
        return self();
    }


    @Override
    public SELF withTag(String tag) {
        this.tag = tag;
        return self();
    }

    public TTarget build() {
        return internalBuild();
    }

    protected abstract TTarget internalBuild();

    private SELF self() {
        return (SELF) this;
    }
}
