package com.example.springbatchtaskletexample.Config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.example.springbatchtaskletexample.Job.TransactionProcessor;
import com.example.springbatchtaskletexample.Job.TransactionReader;
import com.example.springbatchtaskletexample.Job.TransactionWriter;

@Configuration
public class TaskletConfig {

    @Bean
    protected Step readLines(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("readLines", jobRepository)
                .tasklet(new TransactionReader(), transactionManager)
                .build();
    }

    @Bean
    protected Step processLines(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("processLines", jobRepository)
                .tasklet(new TransactionProcessor(), transactionManager)
                .build();
    }

    @Bean
    protected Step writeLines(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("writeLines", jobRepository)
                .tasklet(new TransactionWriter(), transactionManager)
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("taskletsJob", jobRepository)
                .start(readLines(jobRepository, transactionManager))
                .next(processLines(jobRepository, transactionManager))
                .next(writeLines(jobRepository, transactionManager))
                .build();
    }
}
