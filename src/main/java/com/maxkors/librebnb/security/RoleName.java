package com.maxkors.librebnb.security;

public enum RoleName {
    ROLE_ADMIN("ADMIN"),
    ROLE_CUSTOMER("CUSTOMER");

    private String name;

    RoleName(String name) {
        this.name = name;
    }

    public String value() {
        return this.name;
    }
}
