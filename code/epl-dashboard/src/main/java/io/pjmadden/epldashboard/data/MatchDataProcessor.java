
package io.pjmadden.epldashboard.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import io.pjmadden.epldashboard.model.Match;

// ItemProcessor takes 2 arguments input type and output type
public class MatchDataProcessor implements ItemProcessor<Matchinput, Match> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Match process(final Matchinput matchinput) throws Exception {

        // populate this match with whatever is in the input (matchinput)
        Match match = new Match();

        match.setId(Long.parseLong(matchinput.getId()));
        match.setSeason(LocalDate.parse(matchinput.getSeason()));

        return match;
    
    }

}
