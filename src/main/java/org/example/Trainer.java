package com.fitness;

import java.util.UUID;

public class Trainer {
    private final String id;
    private String name;
    private String specialization;

    public Trainer(String name, String specialization) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return String.format("Trainer{id=%s, name='%s', specialization='%s'}", id, name, specialization);
    }
}