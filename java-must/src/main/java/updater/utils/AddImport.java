package updater.utils;

import org.eclipse.jdt.core.dom.*;

/**
 * Created by b010cli on 05/10/2016.
 */
public class AddImport {

    public static void add(CompilationUnit cu, String... argument) {
        ImportDeclaration importDeclaration = creatNewImport(cu.getAST(), argument);
        cu.imports().add(importDeclaration);
    }

    private static ImportDeclaration creatNewImport(AST ast, String... argument) {
        int compt = 0;
        QualifiedName qualifiedName = null;
        SimpleName simpleName = null;
        ImportDeclaration importDeclaration = ast.newImportDeclaration();
        for (String s : argument) {
            if (compt == 0) {
                simpleName = ast.newSimpleName(s);
            } else if (compt == 1) {
                SimpleName sn = ast.newSimpleName(s);
                qualifiedName = ast.newQualifiedName(simpleName, sn);
            } else {
                simpleName = ast.newSimpleName(s);
                qualifiedName = ast.newQualifiedName(qualifiedName, simpleName);
            }
            compt++;
        }
        importDeclaration.setName(qualifiedName);
        return importDeclaration;
    }


}
