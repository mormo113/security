package updater.fortify.A4;

import updater.AbstractUpdater;
import updater.execption.UpdaterException;
import org.eclipse.jdt.core.dom.*;
import tools.Counter;
import tools.LoggerScren;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by b010cli on 04/10/2016.
 */
public class UpdaterPathManipulation extends AbstractUpdater {

    public static final LoggerScren LOGGER = new LoggerScren(UpdaterPathManipulation.class.getName());

    public boolean visit(ClassInstanceCreation declarator) {
        try {
            if (isValide(declarator)) {
                MethodInvocation methodInvocation = declarator.getAST().newMethodInvocation();
                methodInvocation.setName(declarator.getAST().newSimpleName("assertValidFilename"));
                methodInvocation.setExpression(declarator.getAST().newSimpleName("SecuredFile"));
				methodInvocation.arguments().clear();
				Expression arg1 = (Expression) declarator.arguments().get(0);
				arg1.delete();
				methodInvocation.arguments().add(arg1);
				Expression arg2 = null;
				if(declarator.arguments().size() > 0){
					arg2 = (Expression) declarator.arguments().get(0);
					arg2.delete();
					declarator.arguments().add(methodInvocation);
					declarator.arguments().add(arg2);
				}else{
					declarator.arguments().add(methodInvocation);
				}

                getParameter().setClassEdited(true);
                Counter.increase();
                LOGGER.info("Correction fortify : A4 PathManipulation");
            }
            return true;
        } catch (UpdaterException exception) {
            throw new UpdaterException(declarator.getStartPosition(),exception);
        }
    }

    private boolean isValide(ClassInstanceCreation declarator) {
		if((declarator.getType().toString().equals("File") || ((declarator.getType().toString().equals("FileReader")
				|| declarator.getType().toString().equals("FileInputStream")) && !isArgumentTypeIsFile(declarator)))
				&& !declarator.toString().contains("SecuredFile.assertValidFilename")
				&& declarator.arguments().size() == 1){
			return true;
		}else if((declarator.getType().toString().equals("FileOutputStream") ||
				declarator.getType().toString().equals("FileWriter")) && !isArgumentTypeIsFile(declarator)
				&& !declarator.toString().contains("SecuredFile.assertValidFilename")
				&& declarator.arguments().size() <= 2){
			return true;
		}
        return false;
    }

	private boolean isArgumentTypeIsFile(ClassInstanceCreation declarator){
		final List arguments = declarator.arguments();
		if (!arguments.isEmpty()){
			if(arguments.get(0) instanceof SimpleName){
				return isArgumentTypeIsFile((SimpleName) arguments.get(0));
			}else if(arguments.get(0) instanceof ArrayAccess){
				if(((ArrayAccess) arguments.get(0)).getArray() instanceof SimpleName) {
					return isArgumentTypeIsFile((SimpleName) ((ArrayAccess) arguments.get(0)).getArray());
				}
			}else if(arguments.get(0) instanceof MethodInvocation){
				return true;
			}
		}
		return false;
	}

	private boolean isArgumentTypeIsFile(SimpleName simpleName){
		String currentArgument = simpleName.getIdentifier().toString();
		final Map<String, String> variablesMap = getParameter().getVariablesMap();
		if(!variablesMap.isEmpty()){
			final String type = variablesMap.get(currentArgument);
			return ("File".equalsIgnoreCase(type) || "File[]".equalsIgnoreCase(type));
		}
		return false;
	}

}
