package ee.taltech.sportsClub.member;

import ee.taltech.sportsClub.training.TrainingSession;

import java.time.LocalDateTime;
import java.util.List;

public class Member {
    private String name;
    private List<TrainingSession> scheduledSessions;
    private boolean subscriptionPaid;
    private LocalDateTime subscriptionRenewalDate;
    private LocalDateTime subscriptionCancellationDate;

    public void addTrainingSession(TrainingSession trainingSession) {
        scheduledSessions.add(trainingSession);
    }

    public String getName() {
        return name;
    }

    public List<TrainingSession> getScheduledSessions() {
        return scheduledSessions;
    }

    public boolean isSubscriptionPaid() {
        return subscriptionPaid;
    }

    public LocalDateTime getSubscriptionRenewalDate() {
        return subscriptionRenewalDate;
    }

    public LocalDateTime getSubscriptionCancellationDate() {
        return subscriptionCancellationDate;
    }
}
