package ch.zli.m223.punchclock.ViewModel;

public class LoginViewModel {

    private Boolean hasPasswort;
    private String jwt;

    public LoginViewModel(){
        hasPasswort = true;
    }

    public Boolean getHasPasswort() {
        return hasPasswort;
    }

    public void setHasPasswort(Boolean hasPasswort) {
        this.hasPasswort = hasPasswort;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
