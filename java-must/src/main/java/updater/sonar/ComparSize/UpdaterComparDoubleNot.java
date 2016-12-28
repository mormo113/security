package updater.sonar.ComparSize;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.LoggerScren;

;

/**
 * Created by b010cli on 20/06/2016.
 */
public class UpdaterComparDoubleNot extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterComparDoubleNot.class.getName());

    public boolean visit(InfixExpression declarator) {
        try{
        //pour le if
        if (isValide(declarator.getLeftOperand().toString())) {
            if (declarator.getLeftOperand() instanceof PrefixExpression) {
                MethodInvocation expressionReturn = traitementPrefixExpression(declarator.getLeftOperand());
                if (expressionReturn != null) {
                    Expression expression=expressionReturn.getExpression();
                    expression.delete();
                    MethodInvocation methodDeclaration = declarator.getAST().newMethodInvocation();
                    methodDeclaration.setExpression(expression);
                    methodDeclaration.setName(declarator.getAST().newSimpleName(expressionReturn.getName().toString()));
                    declarator.setLeftOperand(methodDeclaration);
                }
            } else if (declarator.getLeftOperand() instanceof ParenthesizedExpression) {
                ParenthesizedExpression parenthesizedExpression = (ParenthesizedExpression) declarator.getLeftOperand();
                if (parenthesizedExpression.getExpression() instanceof PrefixExpression) {
                    MethodInvocation expressionReturn = traitementPrefixExpression(parenthesizedExpression.getExpression());
                    if (expressionReturn != null) {
                        Expression expression=expressionReturn.getExpression();
                        expression.delete();
                        MethodInvocation methodDeclaration = declarator.getAST().newMethodInvocation();
                        methodDeclaration.setExpression(expression);
                        methodDeclaration.setName(declarator.getAST().newSimpleName(expressionReturn.getName().toString()));
                        declarator.setLeftOperand(methodDeclaration);
                    }
                }
            }
        }
        if (isValide(declarator.getRightOperand().toString())) {
            if (declarator.getRightOperand() instanceof PrefixExpression) {
                MethodInvocation expressionReturn = traitementPrefixExpression(declarator.getRightOperand());

                if (expressionReturn != null) {
                    Expression expression=expressionReturn.getExpression();
                    expression.delete();
                    MethodInvocation methodDeclaration = declarator.getAST().newMethodInvocation();
                    methodDeclaration.setExpression(expression);
                    methodDeclaration.setName(declarator.getAST().newSimpleName(expressionReturn.getName().toString()));
                    declarator.setRightOperand(methodDeclaration);
                }
            } else if (declarator.getRightOperand() instanceof ParenthesizedExpression) {
                ParenthesizedExpression parenthesizedExpression = (ParenthesizedExpression) declarator.getRightOperand();
                if (parenthesizedExpression.getExpression() instanceof PrefixExpression) {
                    MethodInvocation expressionReturn = traitementPrefixExpression(parenthesizedExpression.getExpression());
                    if (expressionReturn != null) {
                        Expression expression=expressionReturn.getExpression();
                        expression.delete();
                        MethodInvocation methodDeclaration = declarator.getAST().newMethodInvocation();
                        methodDeclaration.setExpression(expression);
                        methodDeclaration.setName(declarator.getAST().newSimpleName(expressionReturn.getName().toString()));
                        declarator.setRightOperand(methodDeclaration);
                    }
                }
            }
        }
        return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(),exception);
        }
    }

    private MethodInvocation traitementPrefixExpression(Expression expression) {
        PrefixExpression prefixExpression = (PrefixExpression) expression;
        PrefixExpression sousPefixExpression;
        if (prefixExpression.getOperand() instanceof ParenthesizedExpression) {
            ParenthesizedExpression parenthesizedExpression = (ParenthesizedExpression) prefixExpression.getOperand();
            sousPefixExpression = (PrefixExpression) parenthesizedExpression.getExpression();
        } else {
            sousPefixExpression = (PrefixExpression) prefixExpression.getOperand();
        }
        MethodInvocation exp = (MethodInvocation) sousPefixExpression.getOperand();
        return exp;
    }

    private boolean isValide(String chaine) {
        return chaine.matches(".*\\!.*\\!.*isEmpty.*") && !chaine.matches(".*&&.*") && !chaine.matches(".*\\|\\|.*");
    }

}

