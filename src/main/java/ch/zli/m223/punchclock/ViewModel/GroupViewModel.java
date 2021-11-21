package ch.zli.m223.punchclock.ViewModel;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.UserGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.stream.Collectors;

public class GroupViewModel {

    private Long id;

    private String name;

    private List<OptionViewModel> users;
    private List<OptionViewModel> projects;

    private List<Long> userids;
    private List<Long> projectids;

    public GroupViewModel(UserGroup group){
        id = group.getId();
        name = group.getName();
        users = group.getUsers().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
        projects = group.getProjects().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
    }

    public GroupViewModel(){

    }

    public void MapTo(UserGroup group){
       group.setName(name);
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

    public List<OptionViewModel> getProjects() {
        return projects;
    }

    public void setProjects(List<OptionViewModel> projects) {
        this.projects = projects;
    }

    public List<Long> getUserids() {
        return userids;
    }

    public void setUserids(List<Long> userids) {
        this.userids = userids;
    }

    public List<Long> getProjectids() {
        return projectids;
    }

    public void setProjectids(List<Long> projectids) {
        this.projectids = projectids;
    }
}
