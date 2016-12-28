package parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by b010cli on 20/06/2016.
 */
public class Parameter {

    private String fileName;

    private boolean classEdited;

    private String nameLogger;

	private static Map<String,String> variablesMap = new HashMap<>();

    public Parameter() {
        this.classEdited = false;
    }

    public Parameter(String fileName) {
        this.fileName = fileName;
        this.classEdited = false;
    }

    public boolean isClassEdited() {
        return classEdited;
    }

    public void setClassEdited(boolean classEdited) {
        this.classEdited = classEdited;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClassName() {
        return this.fileName.substring(0, this.getFileName().indexOf("."));
    }

    public String getNameLogger() {
        return nameLogger;
    }

    public void setNameLogger(String nameLogger) {
        this.nameLogger = nameLogger;
    }

	public Map<String, String> getVariablesMap() {
		return variablesMap;
	}

	public void setVariablesMap(Map<String, String> variablesMap) {
		this.variablesMap = variablesMap;
	}
}
