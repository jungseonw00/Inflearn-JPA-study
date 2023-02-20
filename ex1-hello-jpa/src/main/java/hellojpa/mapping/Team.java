package hellojpa.mapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    /**
     * 양방향 매핑의 규칙: 연관관계의 주인
     * 연관관계의 주인만이 DB 연관관계와 매핑되고, 외래 키를 관리(등록, 수정, 삭제)할 수 있음.
     * 주인이 아닌 쪽은 읽기만 할 수 있다.
     * 외래 키가 있는 곳을 주인으로 정해야 한다. Member.Team
     */
    @OneToMany
    //@JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>(); // ArrayList로 초기화하는게 관례

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }


}
