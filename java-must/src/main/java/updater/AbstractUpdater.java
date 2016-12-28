package updater;

import parameters.Parameter;
import org.eclipse.jdt.core.dom.*;

/**
 * Created by b010cli on 12/07/2016.
 */
public class AbstractUpdater extends ASTVisitor {

    public static final String LOGGER_DEFAULT_NAME = "LOGGER";

    private CompilationUnit compilationUnit;

    private Parameter parameter = new Parameter();

    public AbstractUpdater() {

    }
    public CompilationUnit getCompilationUnit() {
        return compilationUnit;
    }

    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    public MethodInvocation creatNewMethodeWithoutArgument(AST ast, String vName, String fName) {
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        methodInvocation.setExpression(ast.newSimpleName(vName));
        methodInvocation.setName(ast.newSimpleName(fName));
        return methodInvocation;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
