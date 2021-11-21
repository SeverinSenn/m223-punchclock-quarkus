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

    private List<String> projects;

    private List<String> groups;

    public UserViewModel(User u){
        id = u.getId();
        email = u.getEmail();
        vorname = u.getVorname();
        nachname = u.getNachname();
        is_Admin = u.getIs_Admin();
        projects = u.getProjects().stream().map(x -> x.getName()).collect(Collectors.toList());
        groups = u.getGroups().stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    public UserViewModel(){

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

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
