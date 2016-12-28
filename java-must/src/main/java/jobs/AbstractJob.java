package jobs;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;
import tools.LoggerScren;

import java.io.*;

/**
 * Created by b010qds on 30/11/2016.
 */
public abstract class AbstractJob implements IJob{

    private static LoggerScren LOGGER = new LoggerScren(AbstractJob.class.getName());

    private Document doc;

    public static boolean WRITE_IN_FILE = false;

    public final CompilationUnit doJob(File file) throws IOException, BadLocationException {
        CompilationUnit cu = readParser(file, null);
        executeUpdater(cu);
        writeParser(file, cu);
        return cu;
    }

    public abstract void executeUpdater(CompilationUnit cu);

    private CompilationUnit readParser(File file, CompilationUnit cu) throws IOException {
        messageStart(file);

        String StringToParse = readFileToString(file);
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setSource(StringToParse.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        doc = new Document(StringToParse);
        cu = (CompilationUnit) parser.createAST(null);
        cu.recordModifications();
        ASTRewrite.create(cu.getAST());
        return cu;
    }

    private static String readFileToString(File filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    private void writeParser(File file, CompilationUnit cu) throws BadLocationException {
        if (WRITE_IN_FILE) {
            LOGGER.info("Write in : " + file.getName());
            TextEdit edits = cu.rewrite(doc, null);
            edits.apply(doc);
            writeStringInFile(file, doc.get());
        }
        messageEnd(file);
    }

    private void writeStringInFile(File file, String fileContent) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(fileContent);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioe) {
            LOGGER.error("Erreur levée de type IOException au niveau de la méthode " + "writeFile(...) : ", ioe);
            ioe.printStackTrace();
        }
    }

    private void messageStart(File file) {
        LOGGER.info("START Job : [" + this.getClass().getSimpleName() + "] File Name: " + file.getName());
    }

    private void messageEnd(File file) {
        LOGGER.info("END Job : [" + this.getClass().getSimpleName() + "] File Name :" + file.getName());
    }

    public static void setWriteInFile(boolean writeInFile) {
        WRITE_IN_FILE = writeInFile;
    }
}
