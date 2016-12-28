package updater.sonar.Logger;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;

/**
 * Created by b010cli on 06/04/2016.
 */
public class UpdaterCorrectionFieldAccess extends AbstractUpdater {

    private static Logger LOGGER = Logger.getLogger(UpdaterCorrectionFieldAccess.class.getName());

    /*
     * Correction des acces au Logger
     * correction avec le nouveau nom
     * this.LOGGER devient LOGGER
     * nomDelaClasse.LOGGER devient LOGGER
     */
    public boolean visit(ExpressionStatement declarator) {
        try {

            if (declarator.getExpression() instanceof MethodInvocation) {
                MethodInvocation methodInvocation = (MethodInvocation) declarator.getExpression();
                if (methodInvocation.toString().contains(getParameter().getNameLogger()) && isValidMethodName(methodInvocation) && (
                    methodInvocation.getExpression() instanceof QualifiedName || methodInvocation.getExpression() instanceof FieldAccess
                        || methodInvocation.getExpression() instanceof SimpleName)) {
                    //cas de appel au logger avec le nom de la classe
                    SimpleName simpleName = declarator.getAST().newSimpleName(LOGGER_DEFAULT_NAME);
                    methodInvocation.setExpression(simpleName);
                    getParameter().setClassEdited(true);
                    LOGGER.info("Logger Modify");
                    Counter.increase();
                }
            }

            //conditio, special pour CORBA
            if (declarator.getExpression() instanceof MethodInvocation) {
                MethodInvocation methodInvocation = (MethodInvocation) declarator.getExpression();
                if (methodInvocation.toString().contains("AbstractCorbaService.LOG")) {
                    //cas de appel au logger avec le nom de la classe
                    SimpleName simpleName = declarator.getAST().newSimpleName(LOGGER_DEFAULT_NAME);
                    methodInvocation.setExpression(simpleName);
                    getParameter().setClassEdited(true);
                    LOGGER.info("Logger Modify");
                    Counter.increase();
                }
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

	private boolean isValidMethodName(MethodInvocation methodInvocation) {
		if(methodInvocation.getName() instanceof SimpleName) {
			SimpleName methodName = methodInvocation.getName();
			if ("trace".equalsIgnoreCase(methodName.toString()) ||
					"debug".equalsIgnoreCase(methodName.toString()) ||
					"info".equalsIgnoreCase(methodName.toString()) ||
					"warn".equalsIgnoreCase(methodName.toString()) ||
					"error".equalsIgnoreCase(methodName.toString()) ||
					"fatal".equalsIgnoreCase(methodName.toString())) {
				return true;
			}
		}
		return false;
	}
}
