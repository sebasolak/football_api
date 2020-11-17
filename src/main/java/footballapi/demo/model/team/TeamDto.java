package footballapi.demo.model.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "teams")
public class TeamDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long automaticId;
    private Long idTeam;
    private String strTeam;
    private String username;

    public TeamDto(Long idTeam, String strTeam, String username) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.username = username;
    }

    public TeamDto() {
    }

    public Long getAutomaticId() {
        return automaticId;
    }

    public void setAutomaticId(Long automaticId) {
        this.automaticId = automaticId;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
