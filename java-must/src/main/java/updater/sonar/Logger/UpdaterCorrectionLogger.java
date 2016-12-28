package updater.sonar.Logger;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import parameters.Parameter;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import static org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.FINAL_KEYWORD;
import static org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.PRIVATE_KEYWORD;
import static org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.STATIC_KEYWORD;

/**
 * Created by b010cli on 18/03/2016.
 */
@Deprecated
public class UpdaterCorrectionLogger extends AbstractUpdater {

    private static Logger LOGGER = Logger.getLogger(UpdaterCorrectionLogger.class.getName());


    //TODO b010cli
    public void visit(FieldDeclaration declarator, Object arg) {
        try {
            Parameter p = (Parameter) arg;
            String type = declarator.getType().toString();
            if (type.equals("Logger") && declarator.fragments() != null && declarator.fragments().toString().contains("LoggerFactory")
                && !Modifier.isProtected(declarator.getModifiers()) && p.getNameLogger() == null) {

                String nameVar = ((VariableDeclarationFragment) declarator.fragments().get(0)).getName().getIdentifier();

                //verification si s'apple sinon ajout
                if (!nameVar.equals("LOGGER")) {
                    p.setNameLogger(nameVar);
                    LOGGER.info("Le logger ne respecte pas la norme de developpement : " + nameVar);
                    ((VariableDeclarationFragment) declarator.fragments().get(0)).getName().setIdentifier("LOGGER");
                    LOGGER.info("Correction du nom du logger pour : LOGGER");
                }

                //verification si final sinon ajout
                if (!Modifier.isFinal(declarator.getModifiers())) {
                    p.setClassEdited(true);
                    LOGGER.info("Le logger ne possede pas de : FINAL");
                    declarator.modifiers().add(declarator.getAST().newModifier(FINAL_KEYWORD));
                    LOGGER.info("FINAL Ajouter ");
                }

                //verification si STATIC sinon ajout
                if (!Modifier.isStatic(declarator.getModifiers())) {
                    p.setClassEdited(true);
                    LOGGER.info("Le logger ne possede pas de : STATIC");
                    declarator.modifiers().add(declarator.getAST().newModifier(STATIC_KEYWORD));
                    LOGGER.info("STATIC Ajouter");
                }

                //verification si Private sinon ajout
                if (!Modifier.isPrivate(declarator.getModifiers()) && !Modifier.isPublic(declarator.getModifiers()) && !Modifier
                    .isProtected(declarator.getModifiers())) {
                    p.setClassEdited(true);
                    LOGGER.info("Le logger ne possede pas de : PRIVATE");
                    declarator.modifiers().add(declarator.getAST().newModifier(PRIVATE_KEYWORD));
                    LOGGER.info("PRIVATE Ajouter");
                }
            }
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}
