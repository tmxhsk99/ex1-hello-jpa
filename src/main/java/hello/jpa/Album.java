package hello.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{
    private String atrist;

    public String getAtrist() {
        return atrist;
    }

    public void setAtrist(String atrist) {
        this.atrist = atrist;
    }
}
