package updater.fortify.A1;

import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import tools.LoggerScren;
import updater.AbstractUpdater;
import updater.execption.UpdaterException;


/**
 * Created by b010qds on 21/12/2016.
 */
public class UpdaterXmlInjection extends AbstractUpdater {

	public static final LoggerScren LOGGER = new LoggerScren(UpdaterXmlInjection.class.getName());

	@Override
	public boolean visit(MethodDeclaration declarator) {
		try {
			if(declarator.getBody() != null && declarator.getBody().statements().size() > 0){
				for(Object item : declarator.getBody().statements()){
					if(item instanceof VariableDeclarationStatement && "DocumentBuilderFactory"
							.equals(((VariableDeclarationStatement) item).getType().toString()) &&
							((VariableDeclarationStatement) item).fragments().size() > 0){
						if(((VariableDeclarationStatement) item).fragments().get(0) instanceof VariableDeclarationFragment
								&& !isExpandEntityRefSetted(declarator)){
							String name = ((VariableDeclarationFragment) ((VariableDeclarationStatement) item).fragments().get(0)).getName().toString();
							MethodInvocation methodInvocation = creatNewMethodeWithoutArgument(declarator.getAST(), name, "setExpandEntityReferences");
							BooleanLiteral booleanLiteral = declarator.getAST().newBooleanLiteral(false);
							methodInvocation.arguments().add(booleanLiteral);
							ExpressionStatement expressionStatement = declarator.getAST().newExpressionStatement(methodInvocation);
							declarator.getBody().statements().add(declarator.getBody().statements().indexOf(item)+1, expressionStatement);
							getParameter().setClassEdited(true);
							LOGGER.info("Correction fortify : A1 Xml External Entity Injection [DocumentBuilderFactory]");
							Counter.increase();
							break;
						}
					}
				}
			}
			return true;
		} catch (UpdaterException exception) {
			throw new UpdaterException(declarator.getStartPosition(), exception);
		}
	}

	private boolean isExpandEntityRefSetted(MethodDeclaration declarator) {
		for(Object item : declarator.getBody().statements()) {
			if (item instanceof ExpressionStatement &&
					(((ExpressionStatement) item).getExpression().toString()).contains("setExpandEntityReferences(false)")) {
				return true;
			}
		}
		return false;
	}
}
