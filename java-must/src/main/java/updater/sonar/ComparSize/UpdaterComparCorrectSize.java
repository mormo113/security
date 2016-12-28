package updater.sonar.ComparSize;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import tools.LoggerScren;

;

/**
 * Created by b010cli on 20/06/2016.
 */
public class UpdaterComparCorrectSize extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterComparCorrectSize.class.getName());

    /*
    Probléme est que si deux if se suivent ba ça cree une erreur dans sonar
     */

    public boolean visit(IfStatement declarator) {
        try {
            //pour le if
            if (declarator.getExpression().toString().matches(".*\\.size().*>.*0.*")) {
                Expression expression = declarator.getExpression();
                Expression expressionReturn = traitementExpression(expression);
                if (expressionReturn != null) {
                    declarator.setExpression(expressionReturn);
                }
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(), exception);
        }
    }

    private Expression traitementExpression(Expression expression) {
        if (expression instanceof InfixExpression) {
            InfixExpression infixExpression = (InfixExpression) expression;
            if (infixExpression.getOperator().equals(InfixExpression.Operator.GREATER) && infixExpression
                .getRightOperand() instanceof NumberLiteral) {
                PrefixExpression prefixExpression = expression.getAST().newPrefixExpression();
                prefixExpression.setOperator(PrefixExpression.Operator.NOT);
                MethodInvocation methodInvocation = expression.getAST().newMethodInvocation();
                MethodInvocation methodInvocationExpression = (MethodInvocation) infixExpression.getLeftOperand();
                Expression e = methodInvocationExpression.getExpression();
                e.delete();
                methodInvocation.setExpression(e);
                methodInvocation.setName(expression.getAST().newSimpleName("isEmpty"));
                prefixExpression.setOperand(methodInvocation);
                LOGGER.info("Remplacemetent size() : ");
                Counter.increase();
                return prefixExpression;
            }

            if (infixExpression.getLeftOperand().toString().matches(".*\\.size().*>.*0.*")) {
                Expression expressionReturn = traitementExpression(infixExpression.getLeftOperand());
                if (expressionReturn != null) {
                    infixExpression.setLeftOperand(expressionReturn);
                }
                return null;
            }
            if (infixExpression.getRightOperand().toString().matches(".*\\.size().*>.*0.*")) {
                Expression expressionReturn = traitementExpression(infixExpression.getRightOperand());
                if (expressionReturn != null) {
                    infixExpression.setRightOperand(expressionReturn);
                }
                return null;
            }

        } else if (expression instanceof PrefixExpression) {
            PrefixExpression exp = ((PrefixExpression) expression);
            Expression expressionReturn = traitementExpression(exp.getOperand());
            if (expressionReturn != null) {
                exp.setOperand(expressionReturn);
            }
            return null;
        } else if (expression instanceof ParenthesizedExpression) {
            ParenthesizedExpression exp = ((ParenthesizedExpression) expression);
            Expression expressionReturn = traitementExpression(exp.getExpression());
            if (expressionReturn != null) {
                exp.setExpression(expressionReturn);
            }
            return null;
        }
        return null;
    }

}

