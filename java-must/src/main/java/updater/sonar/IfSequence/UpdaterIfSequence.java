package updater.sonar.IfSequence;

import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import updater.AbstractUpdater;

/**
 * Created by b010cli on 29/11/2016.
 */
/*
    Updater pour le 1314
 */

public class UpdaterIfSequence extends AbstractUpdater {


    /*
     * ON TRANSFORME :
     *
     *  if(c1){
     *          if(C2){
     *              ....
     *                  if(Cn){
     *                      Instruction;
     *                   }
     *           }
     *   }
     *
     *   ou bien
     *
     *    if(c1)
     *          if(C2)
     *              ....
     *                  if(Cn)
     *                      Instruction;
     *
     *  ou bien
     *
     *   if(c1){
     *          if(C2)
     *              ....
     *                  if(Cn){
     *                      Instruction;
     *                   }
     *   }
     *
     *
     * EN :
     *
     *  if (c1 && c2 && ... Cn) {
     *      Instruction;
     *  }
     *
     *
     */

    /*
    * on viste tous les if de notre classe
    * Doc IfStatement : http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/dom/IfStatement.html
    */
    public boolean visit(IfStatement node) {
        //appel recursif de notre traitement
        traitement(node);
        return true;
    }

    public void traitement(IfStatement ifPrincipale) {

        //on verifie qu'il n'y a pas de else
        if (ifPrincipale.getElseStatement() == null) {
            /*
              on recupere toutes les instructions à executer si la condition est remplie
            */
            Statement statement = ifPrincipale.getThenStatement();

            /*
             *   cas sans block
             */
            IfStatement sousIf = null;
            if (statement instanceof IfStatement) {
                sousIf = (IfStatement) statement;
            }

             /*
              *  cas avec block
              *  on verifie que notre  élément est de type block
              *  Apres avoir utiliser le job : "JobAccolade" toutes les instruction sont dans un Block
              *  Block doc : http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/dom/Block.html
              */
            if (statement instanceof Block) {
                Block block = (Block) statement;
                //on verifi que notre block possede un seul element et que cette élement est un if
                if (block.statements().size() == 1 && block.statements().get(0) instanceof IfStatement) {
                    sousIf = (IfStatement) block.statements().get(0);
                }
            }

            if (sousIf != null) {
                //on verifie que notre sous if ne possede pas de else
                if (sousIf.getElseStatement() == null) {
                    //on recupere la condition de notre ifPrincipale
                    Expression conditionDuIfPrincipale = ifPrincipale.getExpression();

                    //on recupere la condition depuis notre sous if
                    Expression conditionDuSousIf = sousIf.getExpression();

                    //on casse le lien qui relie notre sous if et sa condition
                    sousIf.setExpression(ifPrincipale.getAST().newExpressionMethodReference());

                    //on recupere les instructions à executer si la condition est remplie
                    Statement newStatement = sousIf.getThenStatement();

                    //on casse le lien qui relie nos instructions à executer et le sous if
                    sousIf.setThenStatement(ifPrincipale.getAST().newEmptyStatement());

                   /*
                    * on cree une nouvelle InfixExpression
                    * une InfixExpression est une condition qui utilise un operateur pour reunir deux conditions
                    * doc : http://help.eclipse.org/luna/topic/org.eclipse.jdt.doc.isv/reference/api/org/eclipse/jdt/core/dom/InfixExpression.html
                    */
                    InfixExpression nouvelleCondition = ifPrincipale.getAST().newInfixExpression();

                    //on set notre nouvelle condition dans notre if principal
                    //un nouveau element doit etre rattaché à un élement avant d'avoir des sous élements
                    ifPrincipale.setExpression(nouvelleCondition);

                    //on ajoute l'operateur AND à la nouvel condition
                    nouvelleCondition.setOperator(InfixExpression.Operator.CONDITIONAL_AND);

                    //on ajoute la condition du if pricipale
                    nouvelleCondition.setLeftOperand(conditionDuIfPrincipale);

                    //on ajoute la condition du sous if
                    nouvelleCondition.setRightOperand(conditionDuSousIf);

                    //on remplace les instructions à execute à notre ifprincipale
                    ifPrincipale.setThenStatement(newStatement);

                    Counter.increase();

                    // on recommence (pour les cas de sous sous if)
                    traitement(ifPrincipale);
                }
            }

        }
    }

}
