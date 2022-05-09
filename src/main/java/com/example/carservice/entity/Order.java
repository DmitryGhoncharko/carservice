package com.example.carservice.entity;

import java.util.Objects;

public class Order {
    private final long id;
    private final Service service;
    private final User user;
    private final boolean finished;

    public Order(Builder builder) {
        id = builder.id;
        service = builder.service;
        user = builder.user;
        finished = builder.finished;
    }

    public long getId() {
        return id;
    }

    public Service getService() {
        return service;
    }

    public User getUser() {
        return user;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                finished == order.finished &&
                Objects.equals(service, order.service) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, service, user, finished);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", service=" + service +
                ", user=" + user +
                ", finished=" + finished +
                '}';
    }

    public static class Builder {
        private long id;
        private Service service;
        private User user;
        private boolean finished;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withService(Service service) {
            this.service = service;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withIsFinished(boolean finished) {
            this.finished = finished;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
