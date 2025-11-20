package org.example;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingSession {
    private final String id;
    private String name;
    private Trainer trainer;
    private LocalDateTime startTime;

    public TrainingSession(String name, Trainer trainer, LocalDateTime startTime) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.trainer = trainer;
        this.startTime = startTime;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Trainer getTrainer() { return trainer; }
    public LocalDateTime getStartTime() { return startTime; }

    @Override
    public String toString() {
        return String.format("Session{name='%s', trainer=%s, time=%s}", name, trainer.getName(), startTime);
    }
}