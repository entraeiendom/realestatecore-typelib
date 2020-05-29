package io.entraos.rec.domain;

public class RealEstate extends RealEstateCore {
    public static final String REC_CLASS = "https://w3id.org/rec/core/RealEstate";

    public RealEstate(String uuid, String name, String tag) {
        super(REC_CLASS, uuid, name, tag);
    }
}
