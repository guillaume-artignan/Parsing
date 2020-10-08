import com.github.javaparser.ast.Node;

import java.util.Arrays;
import java.util.List;

public class And extends Condition {

    private List<Condition> conditionList;

    private And(){

    }

    public static And of(Condition ... conditions){
        And o = new And();
        o.conditionList = Arrays.asList(conditions);
        return o;
    }

    public boolean isValid(Node ast){
        for (Condition c : conditionList){
            if (c.isNotValid(ast)) return false;
        }

        return true;
    }

    public void action(Node ast) {
        for (Condition c : conditionList) {
            c.action(ast);
        }
    }

    public boolean isNotValid(Node ast){
        return !isValid(ast);
    }
}
