package ch.zli.m223.punchclock.ViewModel;

import ch.zli.m223.punchclock.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserViewModel {

    private Long id;

    private String email;

    private String vorname;

    private String nachname;

    private Boolean is_Admin;
    private Boolean hasPasswort;

    private List<OptionViewModel> projects;
    private List<OptionViewModel> groups;

    private List<Long> projectids;

    private List<Long> groupids;

    public UserViewModel(User u){
        id = u.getId();
        email = u.getEmail();
        vorname = u.getVorname();
        nachname = u.getNachname();
        is_Admin = u.getIs_Admin();
        projects = u.getProjects().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
        groups = u.getGroups().stream().map(x -> new OptionViewModel(x)).collect(Collectors.toList());
        if(u.getPasswort() == null){
            hasPasswort = false;
        }else{
            hasPasswort = true;
        }
    }

    public UserViewModel(){

    }

    public void MapTo(User user){
        user.setEmail(email);
        user.setVorname(vorname);
        user.setNachname(nachname);
        user.setIs_Admin(is_Admin);
    }

    public Boolean getHasPasswort() {
        return hasPasswort;
    }

    public void setHasPasswort(Boolean hasPasswort) {
        this.hasPasswort = hasPasswort;
    }

    public List<Long> getProjectids() {
        return projectids;
    }

    public void setProjectids(List<Long> projectids) {
        this.projectids = projectids;
    }

    public List<Long> getGroupids() {
        return groupids;
    }

    public void setGroupids(List<Long> groupids) {
        this.groupids = groupids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Boolean getIs_Admin() {
        return is_Admin;
    }

    public void setIs_Admin(Boolean is_Admin) {
        this.is_Admin = is_Admin;
    }

    public List<OptionViewModel> getProjects() {
        return projects;
    }

    public void setProjects(List<OptionViewModel> projects) {
        this.projects = projects;
    }

    public List<OptionViewModel> getGroups() {
        return groups;
    }

    public void setGroups(List<OptionViewModel> groups) {
        this.groups = groups;
    }
}
