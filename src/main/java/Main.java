import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args){

        String dir = "D:/Téléchargements/Parsing/src/main/java";
        File fDir = new File(dir);

        Or o = Or.of(new CheckAnnotationOnClass(MappedSuperclass.class),
                     new CheckAnnotationOnClass(Entity.class));

        Or o2 = Or.of(new ExistAttributeWithoutTransient(),
                        new CheckOneToManyWithouMappedBy());

        analyze(fDir,o,o2);

    }

    private static void analyze(File f, Condition ... conditions){

        if (!f.isDirectory()) {
            if (!f.getName().endsWith(".java")) return;

            try {
                CompilationUnit cunit = StaticJavaParser.parse(f);
                ClassOrInterfaceDeclaration classe = cunit.getClassByName(f.getName().split(".java")[0]).get();
                And operator = And.of(conditions);
                operator.check(classe);
            } catch (FileNotFoundException e) {
                System.err.println("Impossible d'analyser le fichier : " + f.getName());
            }
        }
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                analyze(child, conditions);
            }
        }


    }
}