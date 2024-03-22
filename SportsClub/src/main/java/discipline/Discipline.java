package src.discipline;

public abstract class Discipline {
    DisciplineName name;
    String description;

    public Discipline(DisciplineName name) {
        this.name = name;
    }

    public enum DisciplineName {
        Yoga,
        Tennis,
        Stretching
    }
}
