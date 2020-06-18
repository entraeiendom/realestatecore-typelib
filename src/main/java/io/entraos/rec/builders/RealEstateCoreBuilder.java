package io.entraos.rec.builders;

import io.entraos.rec.domain.RealEstateCore;

public interface RealEstateCoreBuilder<SELF extends RealEstateCoreBuilder<SELF, TTarget>,
        TTarget extends RealEstateCore> {

    SELF withUuid(String uuid);
    SELF withName(String name);
    SELF withTag(String tag);
    SELF withRawJson(String json);

    TTarget build();
}
