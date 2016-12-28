package updater.sonar.ComparSize;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.LoggerScren;

;

/**
 * Created by b010cli on 20/06/2016.
 */
public class UpdaterComparSwitch extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterComparSwitch.class.getName());

    /*
    Probléme est que si deux if se suivent ba ça cree une erreur dans sonar
     */

    public boolean visit(InfixExpression declarator) {
        try {

            //pour le if
            if (declarator.getLeftOperand() instanceof NumberLiteral && declarator.getRightOperand() instanceof MethodInvocation && (
                ">".equals(declarator.getOperator().toString()) || InfixExpression.Operator.LESS
                    .equals(declarator.getOperator().toString()))) {
                if (InfixExpression.Operator.GREATER.equals(declarator.getOperator().toString())) {
                    declarator.setOperator(InfixExpression.Operator.LESS);
                } else if (InfixExpression.Operator.LESS.equals(declarator.getOperator().toString())) {
                    declarator.setOperator(InfixExpression.Operator.GREATER);
                }
                NumberLiteral numberLiteral = (NumberLiteral) declarator.getLeftOperand();
                MethodInvocation methodInvocation = (MethodInvocation) declarator.getRightOperand();
                MethodInvocation m = declarator.getAST().newMethodInvocation();
                m.setName(declarator.getAST().newSimpleName(methodInvocation.getName().toString()));
                Expression expression = methodInvocation.getExpression();
                expression.delete();
                m.setExpression(expression);
                declarator.setRightOperand(declarator.getAST().newNumberLiteral(numberLiteral.toString()));
                declarator.setLeftOperand(m);
                LOGGER.info("Switch done");
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

}

