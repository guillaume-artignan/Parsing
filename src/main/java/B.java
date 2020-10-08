import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
@Entity
public class B {

    @OneToMany()
    private A toto;

    @OneToMany(mappedBy = "A")
    private A toto2;

}
