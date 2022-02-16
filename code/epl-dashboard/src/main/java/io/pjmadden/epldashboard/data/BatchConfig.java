package io.pjmadden.epldashboard.data;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import io.pjmadden.epldashboard.model.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[] {

            "id", "HomeTeam", "AwayTeam", "FullTimeHomeTeamGoals", "FullTimeAwayTeamGoals", "FullTimeResult",
            "HalfTimeHomeTeamGoals", "HalfTimeAwayTeamGoals", "HalfTimeResult", "Referee",
            "HomeTeamShots", "AwayTeamShots", "HomeTeamShotsOnTarget", "AwayTeamShotsOnTarget", "HomeTeamCorners",
            "AwayTeamCorners", "HomeTeamFoulsCommitted", "AwayTeamFoulsCommited", "HomeTeamYellowCards",
            "AwayTeamYellowCards", "HomeTeamRedCards", "AwayTeamRedCards", "Date", "Time"

    };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // dealing with the input part
    @Bean
    public FlatFileItemReader<Matchinput> reader() {
        return new FlatFileItemReaderBuilder<Matchinput>()
                .name("MatchItemReader")
                .resource(new ClassPathResource("match-data-update.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Matchinput>() {
                    {
                        setTargetType(Matchinput.class);
                    }
                })
                .build();
    }

    // for each line its going to call the processor

    @Bean
    public MatchDataProcessor processor() {
        return new MatchDataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (id, home_team, away_team, full_time_home_team_goals, full_time_away_team_goals, full_time_result, half_time_home_team_goals, half_time_away_team_goals, half_time_result, referee, home_team_shots, away_team_shots, home_team_shots_on_target, away_team_shots_on_target, home_team_corners, away_team_corners, home_team_fouls_committed, away_team_fouls_commited, home_team_yellow_cards, away_team_yellow_cards, home_team_red_cards, away_team_red_cards, date, time ) "
                        + " VALUES (:id, :homeTeam, :awayTeam, :fullTimeHomeTeamGoals, :fullTimeAwayTeamGoals, :fullTimeResult, :halfTimeHomeTeamGoals, :halfTimeAwayTeamGoals, :halfTimeResult, :referee, :homeTeamShots, :awayTeamShots, :homeTeamShotsOnTarget, :awayTeamShotsOnTarget, :homeTeamCorners, :awayTeamCorners, :homeTeamFoulsCommitted, :awayTeamFoulsCommited, :homeTeamYellowCards, :awayTeamYellowCards, :homeTeamRedCards, :awayTeamRedCards, :date, :time )")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<Matchinput, Match>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
