package updater.sonar.Logger;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * Created by b010cli on 18/03/2016.
 */
public class UpdaterFindLogger extends AbstractUpdater {

    private static Logger LOGGER = Logger.getLogger(UpdaterFindLogger.class);

    public UpdaterFindLogger() {
        super();
    }

    @Override
    public boolean visit(FieldDeclaration declarator) {
        try {
            String type = declarator.getType().toString();
            if (type.equals("Logger") && declarator.fragments() != null &&
					(declarator.fragments().toString().contains("LoggerFactory") || declarator.fragments().toString().contains("getLogger") )) {
                String nameVar = ((VariableDeclarationFragment) declarator.fragments().get(0)).getName().getIdentifier();
                getParameter().setNameLogger(nameVar);
                LOGGER.info("Logger Find");
                return false;
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}
