package com.marc.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class InQueryRequest {

    @JsonProperty("firstNames")
    private List<String> firstNames;

    public List<String> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(List<String> firstNames) {
        this.firstNames = firstNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InQueryRequest that)) return false;
        return Objects.equals(firstNames, that.firstNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNames);
    }

    @Override
    public String toString() {
        return "InQueryRequest{" +
                "firstNames=" + firstNames +
                '}';
    }
}
