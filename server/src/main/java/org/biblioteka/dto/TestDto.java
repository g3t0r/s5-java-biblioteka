package org.biblioteka.dto;

public class TestDto {
    public String name = "Test Dto";
    public Integer integer = 14;

    @Override
    public String toString() {
        return "TestDto{" +
                "name='" + name + '\'' +
                ", integer=" + integer +
                '}';
    }
}
