import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

public class C {

    @OneToMany()
    private A toto;

    @OneToMany(mappedBy = "A")
    private A toto2;

}
