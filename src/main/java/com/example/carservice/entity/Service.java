package com.example.carservice.entity;

import java.util.Objects;

public class Service {
    private final long id;
    private final String name;
    private final int cost;
    private Service(Builder builder) {
        id = builder.id;
        name = builder.name;
        cost = builder.cost;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id &&
                cost == service.cost &&
                Objects.equals(name, service.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    public static class Builder {
        private long id;
        private String name;
        private int cost;
        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withCost(int cost){
            this.cost = cost;
            return this;
        }
        public Service build() {
            return new Service(this);
        }
    }
}
