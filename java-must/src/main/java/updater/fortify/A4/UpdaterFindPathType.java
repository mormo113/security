package updater.fortify.A4;

import org.eclipse.jdt.core.dom.*;
import parameters.Parameter;
import tools.LoggerScren;
import updater.AbstractUpdater;
import updater.execption.UpdaterException;

import java.util.List;

/**
 * Created by b010qds on 13/12/2016.
 */
public class UpdaterFindPathType extends AbstractUpdater {

	public static final LoggerScren LOGGER = new LoggerScren(UpdaterFindPathType.class.getName());

	@Override
	public boolean visit(MethodDeclaration declarator){
		try {
			Parameter parameter = getParameter();
			if(declarator.parameters().size() > 0){
				for(SingleVariableDeclaration variable : (List<SingleVariableDeclaration>)declarator.parameters()){
					parameter.getVariablesMap().put(variable.getName().toString(), variable.getType().toString());
				}
			}
			if(declarator.getBody() != null && declarator.getBody().statements().size() > 0){
				declarator.getBody().statements().stream().forEach(item -> getVariables(parameter, item));
			}
			return true;
		} catch (UpdaterException exception) {
			throw new UpdaterException(declarator.getStartPosition(),exception);
		}
	}

	/**
	 * Remplir une Map<variableName,variableType>
	 * de l'ensemble des variables déclarées dans la classe
	 * @param parameter
	 * @param item
	 */
	private void getVariables(Parameter parameter, Object item) {
		if(item instanceof VariableDeclarationStatement) {
			final String type = ((VariableDeclarationStatement) item).getType().toString();
			VariableDeclarationFragment vFragment = (VariableDeclarationFragment) ((VariableDeclarationStatement) item).fragments().get(0);
			parameter.getVariablesMap().put(vFragment.getName().toString(), type);
		} else if(item instanceof TryStatement){
			((TryStatement) item).getBody().statements().stream().forEach(obj -> getVariables(parameter, obj));
		} else if(item instanceof EnhancedForStatement){
			if(((EnhancedForStatement) item).getParameter() != null){
				parameter.getVariablesMap().put(((EnhancedForStatement) item).getParameter().getName().toString(),
						((EnhancedForStatement) item).getParameter().getType().toString());
			}
			if(((EnhancedForStatement) item).getBody() instanceof Block) {
				((Block) ((EnhancedForStatement) item).getBody()).statements().stream().forEach(statement -> getVariables(parameter, statement));
			}
		} else if(item instanceof ForStatement){
			if(((ForStatement) item).getBody() instanceof Block) {
				((Block) ((ForStatement) item).getBody()).statements().stream().forEach(statement -> getVariables(parameter, statement));
			}
		} else if(item instanceof IfStatement){
			ifImbricated(parameter, (IfStatement) item);
			elseImbricated(parameter, (IfStatement) item);
		} else if(item instanceof WhileStatement){
			if(((WhileStatement) item).getBody() instanceof Block) {
				((Block) ((WhileStatement) item).getBody()).statements().stream().forEach(statement -> getVariables(parameter, statement));
			}
		}
	}

	private void ifImbricated(Parameter parameter, IfStatement item) {
		if(item.getThenStatement() instanceof Block) {
			final Block thenStatement = (Block) item.getThenStatement();
			thenStatement.statements().stream().forEach(statement -> getVariables(parameter, statement));
		}
	}

	private void elseImbricated(Parameter parameter, IfStatement item) {
		if(item.getElseStatement() instanceof IfStatement){
			ifImbricated(parameter, ((IfStatement)item.getElseStatement()));
			elseImbricated(parameter, ((IfStatement)item.getElseStatement()));
		} else if(item.getElseStatement() instanceof Block){
			final Block elseStatement = (Block) item.getElseStatement();
			elseStatement.statements().stream().forEach(statement -> getVariables(parameter, statement));
		}
	}
}
