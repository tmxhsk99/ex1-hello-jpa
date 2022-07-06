package hello.jpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    //객체 지향적이지 않은 설계
    //@Column(name="TEAM_ID")
    //private Long teamId;

    @ManyToOne //현재 객체(Member)의 입장에서 생각을 해야한다. 멤버가 여러명에 팀이 1개 이므로 ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

