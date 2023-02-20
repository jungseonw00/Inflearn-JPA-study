package hellojpa.inheritence;

import javax.persistence.*;

@Entity
// 상속관계 매핑 전략
// InheritanceType.SINGLE_TABLE 전략은 @DiscriminatorColumn을 사용하지 않아도 DTYPE이 적용된다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// 부모 테이블에 자식 Entity명이 들어간다.
@DiscriminatorColumn
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
