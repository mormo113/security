package updater.sonar.Logger;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import updater.AbstractUpdater;
import updater.execption.UpdaterException;

/**
 * Created by b010cli on 18/03/2016.
 */
public class UpdaterChangeLogger extends AbstractUpdater {

    private static Logger LOGGER = Logger.getLogger(UpdaterChangeLogger.class.getName());

    public boolean visit(FieldDeclaration declarator) {
        try {
            String nameVar = ((VariableDeclarationFragment) declarator.fragments().get(0)).getName().getIdentifier();
            if (nameVar != null && nameVar.equals(getParameter().getNameLogger())) {
                ((VariableDeclarationFragment) declarator.fragments().get(0)).getName().setIdentifier(LOGGER_DEFAULT_NAME);
                getParameter().setClassEdited(true);
                LOGGER.info("Logger Modify");
                Counter.increase();
                return false;
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

	@Override
	public boolean visit(IfStatement declarator) {
		try {

			if(declarator.getExpression() instanceof MethodInvocation){
				MethodInvocation methodInvocation = (MethodInvocation) declarator.getExpression();
				if(methodInvocation.toString().contains(getParameter().getNameLogger()+".")){
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

}
