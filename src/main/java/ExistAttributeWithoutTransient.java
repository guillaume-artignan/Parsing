import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExistAttributeWithoutTransient extends Condition {


    private List<FieldDeclaration> liste;

    public ExistAttributeWithoutTransient(){

    }

    public boolean isValid(Node ast) {

        if (ast instanceof ClassOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration)ast;
            List<FieldDeclaration> liste = classe.getFields().stream() .filter(f->!f.isStatic())
                                        .filter(f->!f.isAnnotationPresent(Column.class))
                                        .filter(f->!f.isAnnotationPresent(Id.class))
                                        .filter(f->!f.isAnnotationPresent(OneToMany.class))
                                        .filter(f->!f.isAnnotationPresent(ManyToOne.class))
                                        .filter(f->!f.isAnnotationPresent(ManyToMany.class))
                                        .filter(f->!f.isAnnotationPresent(Embedded.class))
                                        .filter(f->!f.isAnnotationPresent(Transient.class)).collect(Collectors.toList());
            this.liste = liste;
            return liste.size()>0;
        }
        return false;
    }

    public void action(Node ast) {
        ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration)ast;
        System.out.println(classe.getName()+" Verifier @Transient => "+liste.stream().map(s->s.getVariable(0).getName()).collect(Collectors.toList()));
    }
}
