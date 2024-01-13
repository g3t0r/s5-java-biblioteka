package org.biblioteka.shared.model;

public enum Role {
    EMPLOYEE("PRACOWNIK"),
    ADMIN("ADMINISTRATOR"),
    CUSTOMER("CZYTELNIK");

    public static Role fromString(String string) {
       for(Role r : values())  {
            if(r.string.equalsIgnoreCase(string)) {
                return r;
            }
       }
       throw new IllegalArgumentException("No enum for value: " + string);
    }

    Role(String string) {
        this.string = string;
    }
    private final String string;

    @Override
    public String toString() {
        return string;
    }
}
