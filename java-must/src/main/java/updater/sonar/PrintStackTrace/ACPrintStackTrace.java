package updater.sonar.PrintStackTrace;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import tools.Counter;
import tools.LoggerScren;

/**
 * Created by b010cli on 20/06/2016.
 */
public class ACPrintStackTrace extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(ACPrintStackTrace.class.getName());

    public ACPrintStackTrace() {
        super();
    }

    public boolean visit(CatchClause declarator) {
        try {
            if (declarator.getBody().statements().size() > 0) {
                for (Object expr : declarator.getBody().statements()) {
                    if (expr.toString().contains(".printStackTrace(") && expr instanceof ExpressionStatement) {
                        LOGGER.info("printStackTrace  trouver : " + declarator.getStartPosition());
                        ExpressionStatement expressionStatement = (ExpressionStatement) expr;
                        MethodInvocation methodInvocation = creatNewMethodeWithoutArgument(declarator.getAST(),
								getParameter().getNameLogger() != null ? getParameter().getNameLogger() : "LOGGER", "error");
                        SimpleName simpleName = (SimpleName) ((MethodInvocation) expressionStatement.getExpression()).getExpression();
                        SimpleName sn = declarator.getAST().newSimpleName(simpleName.getIdentifier());
                        MethodInvocation m = declarator.getAST().newMethodInvocation();
                        m.setExpression(sn);
                        m.setName(declarator.getAST().newSimpleName("getMessage"));
                        methodInvocation.arguments().add(m);
                        methodInvocation.arguments().add(declarator.getAST().newSimpleName(simpleName.getIdentifier()));
                        ExpressionStatement ext = declarator.getAST().newExpressionStatement(methodInvocation);
						declarator.getBody().statements().set(declarator.getBody().statements().indexOf(expr),ext);
                        LOGGER.info("printStackTrace  corriger : ");
                        getParameter().setClassEdited(true);
                        Counter.increase();
                    }
                }
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}

