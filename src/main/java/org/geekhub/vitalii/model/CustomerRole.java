package org.geekhub.vitalii.model;

public enum CustomerRole {
    ROLE_USER(1),
    ROLE_COMPANY(2);

    private final Integer role;

    CustomerRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }
}
