package ee.taltech.sportsClub.training;

import ee.taltech.sportsClub.coach.Coach;
import ee.taltech.sportsClub.discipline.Discipline;
import ee.taltech.sportsClub.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public class Training {
    private String name;
    private String description;
    private Discipline discipline;
    private Coach coach;
    private Room room;
    private List<TrainingSession> trainingSessions;


    public Training(String name, String description, Discipline discipline, Coach coach, Room room) {
        this.name = name;
        this.description = description;
        this.discipline = discipline;
        this.coach = coach;
        this.room = room;
    }

    public void createTrainingSession(LocalDateTime dateTime, int maxMembers) {
        trainingSessions.add(
                new TrainingSession(this, dateTime, maxMembers)
        );
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }
}
