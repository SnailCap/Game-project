package ee.taltech.sportsClub.training;

import ee.taltech.sportsClub.exceptions.SubscriptionNotPaidException;
import ee.taltech.sportsClub.exceptions.TrainingSessionFullException;
import ee.taltech.sportsClub.member.Member;

import java.time.LocalDateTime;
import java.util.List;

public class TrainingSession {
    private Training training;
    private LocalDateTime eventDateTime;
    private List<Member> members;
    private int maxMembers;

    public TrainingSession(Training training, LocalDateTime eventDateTime, int maxMembers) {
        this.training = training;
        this.eventDateTime = eventDateTime;
        this.maxMembers = maxMembers;
    }

    public void registerMember(Member member) throws TrainingSessionFullException, SubscriptionNotPaidException {
        if (members.size() < maxMembers)
            throw new TrainingSessionFullException();
        if (!member.isSubscriptionPaid())
            throw new SubscriptionNotPaidException();

        members.add(member);
        member.addTrainingSession(this);
    }

    public List<Member> getMembers() {
        return members;
    }
}
