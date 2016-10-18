package com.apress.springrecipes.springbatch.config;

import com.apress.springrecipes.springbatch.UserRegistration;
import com.apress.springrecipes.springbatch.UserRegistrationValidationItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Created by marten on 17-03-14.
 */
@Configuration
public class UserJob {

    private static final String INSERT_REGISTRATION_QUERY =
            "insert into USER_REGISTRATION(FIRST_NAME, LAST_NAME, COMPANY, ADDRESS,CITY,STATE,ZIP,COUNTY,URL,PHONE_NUMBER,FAX)" +
            " values " +
            "(:firstName,:lastName,:company,:address,:city,:state,:zip,:county,:url,:phoneNumber,:fax)";

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private DataSource dataSource;

    @Value("file:${user.home}/batches/registrations.csv")
    private Resource input;

    @Bean
    public Job insertIntoDbFromCsvJob() {
        return jobs.get("insertIntoDbFromCsvJob")
                .start(step1())
                .build();
    }

    @Bean
    protected Step step1() {
        return steps.get("step1")
                .<UserRegistration,UserRegistration>chunk(5)
                .reader(csvFileReader())
                .processor(userRegistrationValidationItemProcessor())
                .writer(jdbcItemWriter())
                .build();
    }

    @Bean
    ItemReader<UserRegistration> csvFileReader() {
        FlatFileItemReader<UserRegistration> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setResource(input);
        return itemReader;
    }

    @Bean
    ItemWriter<UserRegistration> jdbcItemWriter() {
        JdbcBatchItemWriter itemWriter = new JdbcBatchItemWriter();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql(INSERT_REGISTRATION_QUERY);
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        return itemWriter;
    }

    @Bean
    LineMapper<UserRegistration> lineMapper() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames(new String[]{"firstName","lastName","company","address","city","state","zip","county","url","phoneNumber","fax"});

        BeanWrapperFieldSetMapper<UserRegistration> fieldSetMapper = new BeanWrapperFieldSetMapper();
        fieldSetMapper.setTargetType(UserRegistration.class);

        DefaultLineMapper<UserRegistration> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    ItemProcessor<UserRegistration, UserRegistration> userRegistrationValidationItemProcessor() {
        return new UserRegistrationValidationItemProcessor();
    }
}
