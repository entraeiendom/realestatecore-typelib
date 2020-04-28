package io.entraos.rec.domain;

public interface RealEstateCoreBuilder<T extends RealEstateCore> {

    RealEstateCoreBuilder<T> withName(String name);
    T build();
}
