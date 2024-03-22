package ee.taltech.sportsClub.coach;

import ee.taltech.sportsClub.discipline.Discipline;
import ee.taltech.sportsClub.training.TrainingSession;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    String name;
    List<TrainingSession> trainingSessions;
    List<Discipline> disciplines;

    public Coach(String name) {
        this.name = name;
        trainingSessions = new ArrayList<>();
        disciplines = new ArrayList<>();
    }

    public void addTrainingSession(TrainingSession trainingSession) {
        trainingSessions.add(trainingSession);
    }

    public void addDiscipline(Discipline discipline) {
        disciplines.add(discipline);
    }
}
