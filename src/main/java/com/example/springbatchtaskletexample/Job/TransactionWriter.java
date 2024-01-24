package com.example.springbatchtaskletexample.Job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import com.example.springbatchtaskletexample.Model.TransactionSummary;
import com.example.springbatchtaskletexample.Utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class TransactionWriter implements Tasklet,
                                          StepExecutionListener {

    private final Logger logger = LoggerFactory
            .getLogger(TransactionWriter.class);

    private List<TransactionSummary> transactionSummaries;
    private FileUtils fileUtils;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.transactionSummaries = (List<TransactionSummary>) executionContext.get("transactionSummaries");
        try {
            fileUtils = new FileUtils("output", ".csv");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Lines Writer initialized.");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution,
                                ChunkContext chunkContext) throws Exception {
        for (TransactionSummary summary : transactionSummaries) {
            fileUtils.writeLine(summary);
            logger.debug("Wrote line " + summary.toString());
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Lines Writer ended.");
        return ExitStatus.COMPLETED;
    }
}
