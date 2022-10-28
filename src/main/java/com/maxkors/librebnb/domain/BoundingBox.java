package com.maxkors.librebnb.domain;

public class BoundingBox {

    private Double NELat;
    private Double NELng;
    private Double SWLat;
    private Double SWLng;

    public BoundingBox(Double NELat, Double NELng, Double SWLat, Double SWLng) {
        this.NELat = NELat;
        this.NELng = NELng;
        this.SWLat = SWLat;
        this.SWLng = SWLng;
    }

    public Double getNELat() {
        return NELat;
    }

    public void setNELat(Double NELat) {
        this.NELat = NELat;
    }

    public Double getNELng() {
        return NELng;
    }

    public void setNELng(Double NELng) {
        this.NELng = NELng;
    }

    public Double getSWLat() {
        return SWLat;
    }

    public void setSWLat(Double SWLat) {
        this.SWLat = SWLat;
    }

    public Double getSWLng() {
        return SWLng;
    }

    public void setSWLng(Double SWLng) {
        this.SWLng = SWLng;
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "NELat=" + NELat +
                ", NELng=" + NELng +
                ", SWLat=" + SWLat +
                ", SWLng=" + SWLng +
                '}';
    }
}
