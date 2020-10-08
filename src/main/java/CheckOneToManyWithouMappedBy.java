import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class CheckOneToManyWithouMappedBy extends Condition {


    private List<FieldDeclaration> liste;

    public CheckOneToManyWithouMappedBy(){

    }

    public boolean isValid(Node ast) {

        if (ast instanceof ClassOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration)ast;
            List<FieldDeclaration> liste = classe.getFields().stream() .filter(f->!f.isStatic())
                                        .filter(f->f.isAnnotationPresent(OneToMany.class))
                                        .filter(f-> !(f.getAnnotationByClass(OneToMany.class)
                                                            .get().toString().contains("mappedBy"))
                                                    )
                                        .collect(Collectors.toList());
            this.liste = liste;
            return liste.size()>0;
        }
        return false;
    }

    public void action(Node ast) {
        ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration)ast;
        System.out.println(classe.getName()+" Verifier @OneToMany => "+liste.stream().map(s->s.getVariable(0).getName()).collect(Collectors.toList()));
    }
}
