package gui;

import jobs.*;
import jobs.fortify.JobA1XmlExternalEntityInjection;
import jobs.fortify.JobA4PathManipulation;
import jobs.sonar.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by b010cli on 27/09/2016.
 */
public enum ListJob {

    JOB_ACCOLADE("JOB_ACCOLADE","Ajout dans les {If, Else, For, While}",new JobAccolade(),TypeJob.SONAR,Criticite.AUCUNE),
    JOB_CATCH("JOB_CATCH","Ajout d'un log dans les catch vide",new JobCatch(), TypeJob.SONAR,Criticite.AUCUNE),
    JOB_EQUALS("JOB_EQUALS","Correction des Equals",new JobEquals(), TypeJob.SONAR,Criticite.POSSIBLE),
    JOB_LOGGER("JOB_LOGGER","Unification des Loggers",new JobLogger(), TypeJob.SONAR,Criticite.FORTPROBABLE),
    JOB_PRINTSTACKTRACE("JOB_PRINTSTACKTRACE","transformation des PrintStackTrace en log",new JobPrintStackTrace(), TypeJob.SONAR,Criticite.POSSIBLE),
    JOB_CORRECTION_SIZE("JOB_CORRECTION_SIZE","Correction des \"*.size() > 0\" en \"*.isEmpty()\"" ,new JobComparSize(), TypeJob.SONAR,Criticite.POSSIBLE),
    JOB_A4_PATH_MANIPULATION("JOB_A4_PATH_MANIPULATION","Correction des erreur fortify de type A4 path manipulation ",new JobA4PathManipulation(), TypeJob.FORTIFY,Criticite.AUCUNE),
	JOB_A1_XML_EXTERNAL_ENTITY_INJECTION("JOB_A1_XML_EXTERNAL_ENTITY_INJECTION","Correction des erreur fortify de type A1 XML External Entity Injection ",new JobA1XmlExternalEntityInjection(), TypeJob.FORTIFY,Criticite.AUCUNE),
    JOB_IF_SEQUENCE("JOB_IF_SEQUENCE","Correction des if qui se suivent",new JobIfSequence(), TypeJob.SONAR,Criticite.AUCUNE);

    enum TypeJob {
        FORTIFY("FORTIFY"),
        SONAR("SONAR");
        private String value;
        TypeJob(String value) {
            this.value=value;
        }
        public String getValue() {
            return value;
        }
    }

    enum Criticite{
        AUCUNE("TESTER C'EST DOUTER"),
        POSSIBLE("POSSIBLE"),
        FORTPROBABLE("FORTE");
        private String value;
        Criticite(String value) {
            this.value=value;
        }
        public String getValue() {
            return value;
        }
    }


    private final String name;

    private final String description;

    private final IJob job;

    private BooleanProperty selected;

    private TypeJob  typeJob;

    private Criticite criticicte;

    ListJob(String name, String textJob, IJob job, TypeJob  typeJob, Criticite criticicte) {
        this.name = name;
        this.description = textJob;
        this.job = job;
        this.typeJob= typeJob;
        this.criticicte = criticicte;
        this.selected = new SimpleBooleanProperty(false);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public IJob getJob() {
        return job;
    }

    public boolean isIsSelected() {
        return selected.get();
    }

    public BooleanProperty isSelectedProperty() {
        return selected;
    }

    public void setIsSelected(boolean isSelected) {
        this.selected.set(isSelected);
    }

    public TypeJob getTypeJob() {
        return typeJob;
    }

    public Criticite getCriticicte() {
        return criticicte;
    }


}
