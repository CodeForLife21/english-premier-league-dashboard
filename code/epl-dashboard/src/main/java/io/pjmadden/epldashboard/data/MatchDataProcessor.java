
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
        match.setHomeTeam(matchinput.getHomeTeam());
        match.setAwayTeam(matchinput.getAwayTeam());
        match.setFullTimeHomeTeamGoals(Integer.parseInt(matchinput.getFullTimeHomeTeamGoals()));
        match.setFullTimeAwayTeamGoals(Integer.parseInt(matchinput.getFullTimeAwayTeamGoals()));
        match.setFullTimeResult(matchinput.getFullTimeResult());
        match.setHalfTimeHomeTeamGoals(Integer.parseInt(matchinput.getHalfTimeHomeTeamGoals()));
        match.setHalfTimeAwayTeamGoals(Integer.parseInt(matchinput.getHalfTimeAwayTeamGoals()));
        match.setHalfTimeResult(matchinput.getHalfTimeResult());
        match.setReferee(matchinput.getReferee());
        match.setHomeTeamShots(Integer.parseInt(matchinput.getHomeTeamShots()));
        match.setAwayTeamShots(Integer.parseInt(matchinput.getAwayTeamShots()));
        match.setHomeTeamShotsOnTarget(Integer.parseInt(matchinput.getHomeTeamShotsOnTarget()));
        match.setAwayTeamShotsOnTarget(Integer.parseInt(matchinput.getAwayTeamShotsOnTarget()));
        match.setHomeTeamCorners(Integer.parseInt(matchinput.getHomeTeamCorners()));
        match.setAwayTeamCorners(Integer.parseInt(matchinput.getAwayTeamCorners()));
        match.setHomeTeamFoulsCommitted(Integer.parseInt(matchinput.getHomeTeamFoulsCommitted()));
        match.setAwayTeamFoulsCommited(Integer.parseInt(matchinput.getAwayTeamFoulsCommited()));
        match.setHomeTeamYellowCards(Integer.parseInt(matchinput.getHomeTeamYellowCards()));
        match.setAwayTeamYellowCards(Integer.parseInt(matchinput.getAwayTeamYellowCards()));
        match.setHomeTeamRedCards(Integer.parseInt(matchinput.getHomeTeamRedCards()));
        match.setAwayTeamRedCards(Integer.parseInt(matchinput.getAwayTeamRedCards()));
        match.setDate(LocalDate.parse(matchinput.getDate()));
        match.setTime(LocalTime.parse(matchinput.getTime()));
        return match;

    }

}
