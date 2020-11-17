package footballapi.demo.dao;

import footballapi.demo.model.team.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("teamRepository")
public interface TeamRepository extends JpaRepository<TeamDto, Long> {

    @Modifying
    @Transactional
    @Query(value = "Delete from teams WHERE id_team like ?1 and username like ?2", nativeQuery = true)
    void deleteByTeamId(Long teamId, String username);

    @Query(value = "Select id_team from teams WHERE username like ?1", nativeQuery = true)
    List<Long> selectAllTeamsIds(String username);

    @Query(value = "Select count(*) from teams WHERE id_team like ?1 and username like ?2", nativeQuery = true)
    int isAlreadyTeamSaved(Long id_team, String username);

//    @Query(value = "Select id_team, str_team from teams WHERE username like ?1", nativeQuery = true)
//    List<String> selectSavedTeams(String username);


}
