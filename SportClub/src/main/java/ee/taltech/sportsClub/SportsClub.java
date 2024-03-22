package ee.taltech.sportsClub;

import ee.taltech.sportsClub.member.Member;
import ee.taltech.sportsClub.training.Training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SportsClub {
    static int SUBSCRIPTION_FEE = 50;
    static List<Member> members;
    static List<Training> trainings;

    public SportsClub() {
        members = new ArrayList<>();
        trainings = new ArrayList<>();
    }

    static void addTraining(Training training) {
        trainings.add(training);
    }

    static void orderTrainingsByMembersAmount() {
        // Create a Comparator to compare training sessions by the total number of participants
        Comparator<Training> comparator = Comparator.<Training>comparingInt(training ->
                        training.getTrainingSessions().stream()
                                .mapToInt(session -> session.getMembers().size())
                                .sum())
                .reversed() // Reverse the order to get the largest number of participants first
                .thenComparingInt(training -> training.getTrainingSessions().size()); // Then compare by the number of sessions

        // Sort the trainings using the comparator
        trainings.sort(comparator);
    }
}
