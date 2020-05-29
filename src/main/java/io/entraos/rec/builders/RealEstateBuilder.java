package io.entraos.rec.builders;

import io.entraos.rec.domain.RealEstate;

public class RealEstateBuilder extends AbstractRealEstateCoreBuilder<RealEstateBuilder, RealEstate> {



    @Override
    protected RealEstate internalBuild() {
        return new RealEstate(this.uuid, this.name, this.tag);
    }

}
