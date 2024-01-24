package com.example.springbatchtaskletexample.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.example.springbatchtaskletexample.Model.FutureTransaction;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionReader implements Tasklet,
                                          StepExecutionListener {
    private final Logger logger = LoggerFactory
            .getLogger(TransactionReader.class);
    private List<FutureTransaction> transactionList;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        transactionList = new ArrayList<>();
        logger.debug("Transaction Reader initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        try (Stream<String> transactionLine = Files.lines(Paths.get(ClassLoader.getSystemResource("sampleInput.txt")
                                                                               .toURI()))) {
            transactionList = transactionLine.map(record -> TransactionMapper.mapTransaction(record))
                                             .collect(Collectors.toList());
        }
        logger.debug("Total Transaction records read : " + transactionList.size());
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("transactionList", this.transactionList);
        logger.debug("Transaction Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
