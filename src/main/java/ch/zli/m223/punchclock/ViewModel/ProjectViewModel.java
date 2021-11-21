package ch.zli.m223.punchclock.ViewModel;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectViewModel {

    private Long id;

    private String name;

    private List<OptionViewModel> users;
    private List<OptionViewModel> groups;

    private List<Long> userids;
    private List<Long> groupids;

    public ProjectViewModel(Project project){
        id = project.getId();
        name = project.getName();
        users = project.getUsers().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
        groups = project.getGroups().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

    public ProjectViewModel(){

    }

    public void MapTo(Project project){
        project.setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<OptionViewModel> users) {
        this.users = users;
    }

    public List<OptionViewModel> getGroups() {
        return groups;
    }

    public void setGroups(List<OptionViewModel> groups) {
        this.groups = groups;
    }

    public List<Long> getUserids() {
        return userids;
    }

    public void setUserids(List<Long> userids) {
        this.userids = userids;
    }

    public List<Long> getGroupids() {
        return groupids;
    }

    public void setGroupids(List<Long> groupids) {
        this.groupids = groupids;
    }
}
