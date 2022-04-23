package uz.gullbozor.gullbozor.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "announce")
public class Announce extends BaseEntity {

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<Attach> attachList;

    @Column(name = "price")
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "title")
    private String title;

    @OneToMany
    private List<Attach> attachIdList;

    private Long attachContactId;
    private Long shopId;
    private double height;
    private double diameter;
    private double weight;
    private boolean isWithPot;
    private boolean isWithFertilizer;
    private String description;


}
