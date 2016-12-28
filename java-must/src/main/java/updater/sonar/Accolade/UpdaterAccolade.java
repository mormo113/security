package updater.sonar.Accolade;

;
import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import tools.LoggerScren;

/**
 * Created by b010cli on 20/06/2016.
 */
public class UpdaterAccolade extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterAccolade.class.getName());

    /*
    Probléme est que si deux if se suivent ba ça cree une erreur dans sonar
     */

    public boolean visit(IfStatement declarator) {
        try {

            //pour le if
            if (isValide(declarator.getThenStatement())) {
                //traitement
                LOGGER.info("Accolade  trouve : " + declarator.getStartPosition());
                Block block = declarator.getAST().newBlock();
                ASTNode ex = declarator.getThenStatement();
                declarator.setThenStatement(block);
                block.statements().add(ex);
                Counter.increase();
                LOGGER.info("Accolade  ajouter sur then");
            }
            //pour le else
            if (isValide(declarator.getElseStatement()) && !(declarator.getElseStatement() instanceof IfStatement)) {
                //traitement
                LOGGER.info("Ajout accolade  trouve : " + declarator.getStartPosition());
                Block block = declarator.getAST().newBlock();
                ASTNode ex = declarator.getElseStatement();
                declarator.setElseStatement(block);
                block.statements().add(ex);
                Counter.increase();
                LOGGER.info("Accolade  ajouter sur else");
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

    public boolean visit(ForStatement declarator) {
        try {

            //pour le if
            if (isValide(declarator.getBody())) {
                LOGGER.info("Ajout accolade  trouve : " + declarator.getStartPosition());
                Block block = declarator.getAST().newBlock();
                ASTNode ex = declarator.getBody();
                declarator.setBody(block);
                block.statements().add(ex);
                Counter.increase();
                LOGGER.info("Accolade  ajouter sur FOR");
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

    public boolean visit(WhileStatement declarator) {
        try {
            //pour le if
            if (isValide(declarator.getBody())) {
                LOGGER.info("Ajout accolade  trouve : " + declarator.getStartPosition());
                Block block = declarator.getAST().newBlock();
                ASTNode ex = declarator.getBody();
                declarator.setBody(block);
                block.statements().add(ex);
                Counter.increase();
                LOGGER.info("Accolade  ajouter sur FOR");
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

    private boolean isValide(Object o) {
        return o != null && (o instanceof ExpressionStatement || o instanceof Statement) && !(o instanceof Block);
    }

}

