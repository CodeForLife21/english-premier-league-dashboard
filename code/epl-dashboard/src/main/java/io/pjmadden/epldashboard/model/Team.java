package io.pjmadden.epldashboard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalMatchesPlayed;
    private long totalMatchesWon;

    @Transient
    private List<Match>matches;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }
    public void setTotalMatchesPlayed(long totalMatchesPlayed) {
        this.totalMatchesPlayed = totalMatchesPlayed;
    }
    public long getTotalMatchesWon() {
        return totalMatchesWon;
    }
    public void setTotalMatchesWon(long totalMatchesWon) {
        this.totalMatchesWon = totalMatchesWon;
    }
    public Team(String teamName, long totalMatchesPlayed) {
        this.teamName = teamName;
        this.totalMatchesPlayed = totalMatchesPlayed;
    }
    @Override
    public String toString() {
        return "Team [teamName=" + teamName + ", totalMatches=" + totalMatchesPlayed + ", totalWins=" + totalMatchesWon + "]";
    }
    public Team() {
        
    }
    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    

    
}
