package src.session;

import member.Member;
import room.Room;
import training.Training;

import java.time.LocalDateTime;
import java.util.List;

public class TrainingSession extends Training {
    Training training;
    Room room;
    LocalDateTime eventDateTime;
    List<Member> members;
    int maxMembers;
}
