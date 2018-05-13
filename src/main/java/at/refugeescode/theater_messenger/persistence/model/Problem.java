package at.refugeescode.theater_messenger.persistence.model;


import javax.persistence.*;

@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Actor actor;

    public Problem() {
    }

    public Problem(String name, Actor actor) {
        this.name = name;
        this.actor = actor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", actor=" + actor +
                '}';
    }
}
