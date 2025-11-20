package com.fitness;

import java.util.UUID;

public class Client {
    public enum MembershipType { FULL, DAY, MORNING }

    private final String id;
    private String name;
    private MembershipType membershipType;

    public Client(String name, MembershipType membershipType) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.membershipType = membershipType;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public MembershipType getMembershipType() { return membershipType; }

    @Override
    public String toString() {
        return String.format("Client{id=%s, name='%s', membership=%s}", id, name, membershipType);
    }
}