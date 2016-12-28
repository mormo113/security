package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jobs.AbstractJob;
import jobs.IJob;
import tools.BrowseDirectory;
import tools.Counter;
import tools.LoggerScren;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private static LoggerScren LOGGER = new LoggerScren(GUIController.class.getName());

    private Stage stage;

    @FXML
    private TextField lien;

    @FXML
    private TextArea textAreaLog;

    @FXML
    private CheckBox WriteInFile;

    @FXML
    private TableView tabJob;

    @FXML
    private ImageView imageViewMessage;

    @FXML
    private TextField listRepoNotTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabJob.getItems().setAll(ListJob.class.getEnumConstants());
        tabJob.setEditable(true);

        final TableColumn<ListJob, String> nameJobColumn = new TableColumn<>("Name Job");
        nameJobColumn.setCellValueFactory(param -> {
            final ListJob job = param.getValue();
            return new SimpleStringProperty(job.getName());
        });
        nameJobColumn.setEditable(false);

        final TableColumn<ListJob, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(param -> {
            final ListJob job = param.getValue();
            return new SimpleStringProperty(job.getDescription());
        });
        descriptionColumn.setEditable(false);

        final TableColumn<ListJob, String> descriptionType = new TableColumn<>("Type Job");
        descriptionType.setCellValueFactory(param -> {
            final ListJob job = param.getValue();
            return new SimpleStringProperty(job.getTypeJob().getValue());
        });
        descriptionType.setEditable(false);

        final TableColumn<ListJob, String> descriptionCriticite = new TableColumn<>("Criticite");
        descriptionCriticite.setCellValueFactory(param -> {
            final ListJob job = param.getValue();
            return new SimpleStringProperty(job.getCriticicte().getValue());
        });
        descriptionCriticite.setEditable(false);

        TableColumn selectedColumn = new TableColumn("Selectioner");
        selectedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ListJob, Boolean>, ObservableValue<Boolean>>() {
            //This callback tell the cell how to bind the data model 'Registered' property to
            //the cell, itself.
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ListJob, Boolean> param) {
                return param.getValue().isSelectedProperty();
            }
        });
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));
        selectedColumn.setEditable(true);

        tabJob.getColumns().setAll(nameJobColumn, descriptionColumn, descriptionType, descriptionCriticite, selectedColumn);

        listRepoNotTo.appendText(BrowseDirectory.getListRepoNotToString());

    }

    public void init(Stage stage) {
        this.stage = stage;
    }

    public void SelectFile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Resource File");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = directoryChooser.showDialog(stage);
        LoggerScren.setPrintLog(this.textAreaLog);
        if (file != null) {
            lien.setText(file.getPath());
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (lien.getText().isEmpty()) {
            printError("Aucun repertoire n'a été séléctioné");
            return;
        }
        if (!isJobSelected()) {
            printError("Aucun job est selectioné");
            return;
        }
        BrowseDirectory.setListRepoNotTo(Arrays.asList(listRepoNotTo.getText().replace(" ","").split(BrowseDirectory.SEPARATOR)));
        imageViewMessage.setImage(new Image("\\Img\\wait.gif"));

        LOGGER.info("DEBUT DU triatement sur : " + this.lien.getText());
        if (this.WriteInFile.isSelected()) {
            AbstractJob.setWriteInFile(true);
        }
        List<ListJob> list = tabJob.getItems();
        Runnable task = () -> {
            for (ListJob lj : list) {
                if (lj.isIsSelected()) {
                    IJob job = lj.getJob();
                    printMessage("Debut du job : " + lj.getName());
                    BrowseDirectory.doJob(this.lien.getText(), job);
                    printMessage("ligne traité : " + Counter.getCounter());
                    Counter.reset();
                    printMessage("Fin du job : " + lj.getName());
                }
            }
            LOGGER.info("Fin DU triatement sur : " + this.lien.getText());
            imageViewMessage.setImage(new Image("\\Img\\ok.png"));
        };
        new Thread(task).start();

    }

    private boolean isJobSelected() {
        List<ListJob> list = tabJob.getItems();
        for (ListJob lj : list) {
            if (lj.isIsSelected()) {
                return true;
            }
        }
        return false;
    }

    private void printMessage(String message) {
        textAreaLog.setStyle(null);
        this.textAreaLog.appendText(message + "\n");
    }

    private void printError(String message) {
        Image image = new Image("\\Img\\error.png");
        imageViewMessage.setImage(image);
        textAreaLog.setStyle("-fx-text-fill: red;");
        this.textAreaLog.appendText(message + "\n");
    }

}
