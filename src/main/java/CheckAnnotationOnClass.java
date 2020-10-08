import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import javax.persistence.MappedSuperclass;

public class CheckAnnotationOnClass extends Condition {

    private final Class annotation;

    public CheckAnnotationOnClass(Class c){
        this.annotation = c;
    }

    public boolean isValid(Node ast) {

        if (ast instanceof ClassOrInterfaceDeclaration){
            ClassOrInterfaceDeclaration classe = (ClassOrInterfaceDeclaration)ast;
            return classe.getAnnotationByClass(annotation).isPresent();
        }
        return false;
    }

    public void action(Node ast) { }
}
