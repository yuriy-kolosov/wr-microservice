package pro.sky.wr_m.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UzerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<SubscriptionEntity> subscriptionList;

    public UzerEntity() {
    }

    public UzerEntity(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UzerEntity(Long id, String email, String password, List<SubscriptionEntity> subscriptionList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.subscriptionList = subscriptionList;
    }

}
