package updater.sonar.Catch;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import tools.LoggerScren;

/**
 * Created by b010cli on 17/06/2016.
 */
public class UpdaterCatch extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterCatch.class.getName());

    public UpdaterCatch() {
        super();
    }

    public boolean visit(CatchClause declarator) {
        try {
            boolean loggerNotFound = true;
            String exception = declarator.getException().getName().toString();
            if (declarator.getBody().statements().size() > 0) {
                for (Object expr : declarator.getBody().statements()) {
                    if (expr.toString().contains(".error(")) {
                        loggerNotFound = false;
                    } else if (expr.toString().contains(".info(")) {
                        loggerNotFound = false;
                    } else if (expr.toString().contains(".debug(")) {
                        loggerNotFound = false;
                    } else if (expr.toString().contains(".warn(")) {
                        loggerNotFound = false;
                    } else if (expr.toString().contains("throw")) {
                        loggerNotFound = false;
                        try {
                            if (expr instanceof ThrowStatement) {
                                ThrowStatement ts = (ThrowStatement) expr;
                                if (ts.getExpression() instanceof ClassInstanceCreation) {
                                    ClassInstanceCreation cc = (ClassInstanceCreation) ts.getExpression();
                                    for (Object a : cc.arguments()) {
                                        if (a.toString().equals(exception)) {
                                            loggerNotFound = false;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            loggerNotFound = false;
                        }

                    } else if (expr.toString().contains("print")) {
                        loggerNotFound = false;
                    }
                }
            }
            if (loggerNotFound || declarator.getBody().statements().size() == 0) {
                LOGGER.info("Aucune info trouver pour la catch : " + declarator.getStartPosition());
                MethodInvocation methodInvocation = creatNewMethodeWithoutArgument(declarator.getAST(), "LOGGER", "error");
                methodInvocation.arguments().add(creatNewMethodeWithoutArgument(declarator.getAST(), exception, "getMessage"));
                methodInvocation.arguments().add(declarator.getAST().newSimpleName(declarator.getException().getName().toString()));
                ExpressionStatement expressionStatement = declarator.getAST().newExpressionStatement(methodInvocation);
                declarator.getBody().statements().add(0, expressionStatement);
                LOGGER.info("Log(trace) Ajouter");
                Counter.increase();
                getParameter().setClassEdited(true);
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}
