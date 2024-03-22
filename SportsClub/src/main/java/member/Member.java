package src.member;

import session.TrainingSession;

import java.time.LocalDateTime;
import java.util.List;

public class Member {
    String name;
    List<TrainingSession> scheduledSessions;
    LocalDateTime subscriptionRenewalDate;
    LocalDateTime subscriptionCancellationDate;
}
