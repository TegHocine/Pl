package com.pl.player.batch;

import com.pl.player.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.Arrays;
import java.lang.reflect.Field;

import static com.pl.player.utils.Constants.PLAYER_FILE_PATH;

@Slf4j
@Configuration
public class PlayerReader {

    @Bean
    public FlatFileItemReader<Player> reader() {
        log.info("Job Reader of player stats CSV file started");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resources = resolver.getResource(PLAYER_FILE_PATH);
        
        LineMapper<Player> lineMapper = createLineMapper();

        return new FlatFileItemReaderBuilder<Player>()
                .name("playerItemReader")
                .resource(resources)
                .linesToSkip(1)
                .lineMapper(lineMapper)
                .build();
    }



    private LineMapper<Player> createLineMapper() {
        DefaultLineMapper<Player> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(getPlayerFieldNames());

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper((FieldSet fieldSet) -> Player.builder()
                .playerName(fieldSet.readString("playerName"))
                .nation(fieldSet.readString("nation"))
                .position(fieldSet.readString("position"))
                .age(getIntValue(fieldSet, "age"))
                .matchPlayed(getIntValue(fieldSet, "matchPlayed"))
                .starts(getIntValue(fieldSet, "starts"))
                .minutesPlayed(getDoubleValue(fieldSet, "minutesPlayed"))
                .goals(getDoubleValue(fieldSet, "goals"))
                .assists(getDoubleValue(fieldSet, "assists"))
                .penaltiesScored(getDoubleValue(fieldSet, "penaltiesScored"))
                .yellowCards(getDoubleValue(fieldSet, "yellowCards"))
                .redCards(getDoubleValue(fieldSet, "redCards"))
                .expectedGoals(getDoubleValue(fieldSet, "expectedGoals"))
                .expectedAssists(getDoubleValue(fieldSet, "expectedAssists"))
                .team(fieldSet.readString("team"))
                .build()
        );

        return lineMapper;
    }

    private String[] getPlayerFieldNames() {
        return Arrays.stream(Player.class.getDeclaredFields())
                .map(Field::getName)
                .filter(name -> !name.equals("id"))
                .toArray(String[]::new);
    }

    private int getIntValue(FieldSet fieldSet, String fieldName) {
        return fieldSet.readRawString(fieldName).isEmpty() ? 0 : fieldSet.readInt(fieldName);
    }

    private double getDoubleValue(FieldSet fieldSet, String fieldName) {
        return fieldSet.readRawString(fieldName).isEmpty() ? 0.0 : fieldSet.readDouble(fieldName);
    }
}
