package footballapi.demo.model.team;

public class FakeTeamDto {

    private final Long idTeam;
    private final String strTeam;

    public FakeTeamDto(Long idTeam, String strTeam) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }
}
