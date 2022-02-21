
package io.pjmadden.epldashboard.data;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.pjmadden.epldashboard.model.Match;

// ItemProcessor takes 2 arguments input type and output type
public class MatchDataProcessor implements ItemProcessor<Matchinput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);
// process method to know how to process one entry from the input (Matchinput)to the final Match class
    @Override
    public Match process(final Matchinput matchinput) throws Exception {

        // populate this match with whatever is in the input (matchinput)
        Match match = new Match();
        // call the set methods from the Match Class,
        // get the data from the Matchinput class (CSV) pass as argument into the setter
        
        // conversion needed depending on data passed ex... String to Interger
        // Matchinput class the data is stored as String coming from CSV, hence some
        // data needs to be converted
        match.setId(Long.parseLong(matchinput.getId()));
        match.setHomeTeam(matchinput.getHome_team());
        match.setAwayTeam(matchinput.getAway_team());
        match.setFullTimeHomeTeamGoals(Integer.parseInt(matchinput.getFull_time_home_team_goals()));
        match.setFullTimeAwayTeamGoals(Integer.parseInt(matchinput.getFull_time_away_team_goals()));
        match.setFullTimeResult(matchinput.getFull_time_result());
        match.setHalfTimeHomeTeamGoals(Integer.parseInt(matchinput.getHalf_time_home_team_goals()));
        match.setHalfTimeAwayTeamGoals(Integer.parseInt(matchinput.getHalf_time_away_team_goals()));
        match.setHalfTimeResult(matchinput.getHalf_time_result());
        match.setReferee(matchinput.getReferee());
        match.setHomeTeamShots(Integer.parseInt(matchinput.getHome_team_shots()));
        match.setAwayTeamShots(Integer.parseInt(matchinput.getAway_team_shots()));
        match.setHomeTeamShotsOnTarget(Integer.parseInt(matchinput.getHome_team_shots_on_target()));
        match.setAwayTeamShotsOnTarget(Integer.parseInt(matchinput.getAway_team_shots_on_target()));
        match.setHomeTeamCorners(Integer.parseInt(matchinput.getHome_team_corners()));
        match.setAwayTeamCorners(Integer.parseInt(matchinput.getAway_team_corners()));
        match.setHomeTeamFoulsCommitted(Integer.parseInt(matchinput.getHome_team_fouls_committed()));
        match.setAwayTeamFoulsCommited(Integer.parseInt(matchinput.getAway_teams_fouls_commited()));
        match.setHomeTeamYellowCards(Integer.parseInt(matchinput.getHome_team_yellow_cards()));
        match.setAwayTeamYellowCards(Integer.parseInt(matchinput.getAway_team_yellow_cards()));
        match.setHomeTeamRedCards(Integer.parseInt(matchinput.getHome_team_yellow_cards()));
        match.setAwayTeamRedCards(Integer.parseInt(matchinput.getAway_team_yellow_cards()));
        match.setDate(LocalDate.parse(matchinput.getDate()));
        match.setTime(LocalTime.parse(matchinput.getTime()));
        return match;

    }

}
