package ch.zli.m223.punchclock.ViewModel;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.UserGroup;

import javax.swing.*;

public class OptionViewModel {

    private String Label;

    private String Value;

    public OptionViewModel(){

    }

    public OptionViewModel(Project project){
        Label = project.getName();
        Value = project.getId().toString();
    }

    public OptionViewModel(UserGroup group){
        Label = group.getName();
        Value = group.getId().toString();
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
