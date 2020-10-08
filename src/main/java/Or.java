import com.github.javaparser.ast.Node;

import java.util.Arrays;
import java.util.List;

public class Or extends Condition {

    private List<Condition> conditionList;

    private Or(){

    }

    public static Or of(Condition ... conditions){
        Or o = new Or();
        o.conditionList = Arrays.asList(conditions);
        return o;
    }

    public boolean isValid(Node ast){
        for (Condition c : conditionList){
            if (c.isValid(ast)) return true;
        }
        return false;
    }

    public void action(Node ast) {
        for (Condition c : conditionList) {
            if (c.isValid(ast))
            c.action(ast);
        }
    }

    public boolean isNotValid(Node ast){
        return !isValid(ast);
    }
}
