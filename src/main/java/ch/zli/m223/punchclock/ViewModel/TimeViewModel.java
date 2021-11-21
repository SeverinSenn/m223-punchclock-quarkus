package ch.zli.m223.punchclock.ViewModel;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.domain.Time;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class TimeViewModel {

    private Long id;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private ProjectViewModel project;

    private Long projectId;

    public TimeViewModel(Time time){
        id = time.getId();
        checkIn = time.getCheckIn();
        checkOut = time.getCheckOut();
        project = new ProjectViewModel(time.getProject());
    }

    public TimeViewModel(){

    }

    public void MapTo(Time time){
        time.setCheckIn(checkIn);
        time.setCheckOut(checkOut);
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public ProjectViewModel getProject() {
        return project;
    }

    public void setProject(ProjectViewModel project) {
        this.project = project;
    }
}
