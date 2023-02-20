package hellojpa.mapping;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    private long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
