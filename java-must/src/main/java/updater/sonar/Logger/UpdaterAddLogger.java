package updater.sonar.Logger;

import updater.AbstractUpdater;
import updater.utils.AddImport;
import updater.execption.UpdaterException;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

/**
 * Created by b010cli on 24/06/2016.
 */
public class UpdaterAddLogger extends AbstractUpdater {

    private static Logger LOGGER = Logger.getLogger(UpdaterAddLogger.class.getName());

    public static void job(CompilationUnit cu) {
        try {
            //addImport
            if (importDontExist(cu.imports(), "Logger")) {
                LOGGER.info("ajout import : Logger");
                AddImport.add(cu, "org", "slf4j", "Logger");
            }
            if (importDontExist(cu.imports(), "LoggerFactory")) {
                LOGGER.info("ajout import : LoggerFactory");
                AddImport.add(cu, "org", "slf4j", "LoggerFactory");
            }
            TypeDeclaration typeDeclaration = (TypeDeclaration) cu.types().get(0);
			TypeDeclaration t = (TypeDeclaration) cu.types().get(0);
            SimpleName s = cu.getAST().newSimpleName(t.getName().toString());
            SimpleType simpleType = cu.getAST().newSimpleType(s);
            TypeLiteral typeLiteral = cu.getAST().newTypeLiteral();
            typeLiteral.setType(simpleType);
            MethodInvocation methodInvocation_2 = cu.getAST().newMethodInvocation();
            methodInvocation_2.setExpression(cu.getAST().newSimpleName("LoggerFactory"));
            methodInvocation_2.setName(cu.getAST().newSimpleName("getLogger"));
            methodInvocation_2.arguments().add(typeLiteral);
            VariableDeclarationFragment variableDeclarationFragment = cu.getAST().newVariableDeclarationFragment();
            variableDeclarationFragment.setName(cu.getAST().newSimpleName("LOGGER"));
            variableDeclarationFragment.setInitializer(methodInvocation_2);
            FieldDeclaration fieldDeclaration = cu.getAST().newFieldDeclaration(variableDeclarationFragment);
            fieldDeclaration.setType(cu.getAST().newSimpleType(cu.getAST().newName("Logger")));
            fieldDeclaration.modifiers().add(cu.getAST().newModifier(org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.PRIVATE_KEYWORD));
            fieldDeclaration.modifiers().add(cu.getAST().newModifier(org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.FINAL_KEYWORD));
            fieldDeclaration.modifiers().add(cu.getAST().newModifier(org.eclipse.jdt.core.dom.Modifier.ModifierKeyword.STATIC_KEYWORD));
            typeDeclaration.bodyDeclarations().add(0, fieldDeclaration);
            LOGGER.info("Logger Add");
        } catch (UpdaterException exception) {
            throw new UpdaterException("Probleme lors de l'ajout des import pour logger", exception);
        }

    }

    private static boolean importDontExist(List<ImportDeclaration> listImport, String nameClass) {
        if (listImport != null) {
            for (ImportDeclaration imp : listImport) {
                if (imp.toString().contains(nameClass)) {
                    LOGGER.info(" / Have Logger import");
                    return false;

                }
            }
        }
        LOGGER.info(" / don't have Logger import");
        return true;
    }

}
