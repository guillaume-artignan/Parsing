import com.github.javaparser.ast.Node;

public abstract class Condition {


    public void check(Node ast){
        if (isValid(ast)){
            action(ast);
        }
    }

    public abstract boolean isValid(Node ast);

    public abstract void action(Node ast);

    public boolean isNotValid(Node ast){
        return !isValid(ast);
    }
}
