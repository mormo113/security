package updater.sonar.Equals;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import tools.Counter;
import tools.LoggerScren;

/**
 * Created by b010cli on 20/06/2016.
 */
public class UpdaterEquals extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterEquals.class.getName());

    public UpdaterEquals() {
    }

    public boolean visit(MethodInvocation declarator) {
        try {
            //LA FLEME de le faire
            if ((declarator.toString().matches(".*\\.equals\\(\".*\"\\)") || declarator.toString().matches(".*\\.equals\\(\".*\"\\)"))
                && declarator.arguments().size() == 1 && declarator.arguments().get(0) instanceof StringLiteral && declarator
                .getExpression() instanceof SimpleName) {
                LOGGER.info("equal trouver ligne : " + declarator.getStartPosition());
                //            StringLiteral stringLiteral= (StringLiteral) declarator.arguments().get(0);
                StringLiteral stringLiteral = declarator.getAST().newStringLiteral();
                String s = ((StringLiteral) declarator.arguments().get(0)).getEscapedValue();
                stringLiteral.setEscapedValue(s);
                SimpleName simpleName = (SimpleName) declarator.getExpression();
                declarator.setExpression(stringLiteral);
                declarator.arguments().clear();
                declarator.arguments().add(simpleName);
                Counter.increase();
                LOGGER.info("equal trouver ligne : " + declarator.getStartPosition());
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}
