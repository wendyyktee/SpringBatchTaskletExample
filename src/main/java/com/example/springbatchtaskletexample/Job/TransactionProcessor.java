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
import com.example.springbatchtaskletexample.Model.FutureTransaction;
import com.example.springbatchtaskletexample.Model.TransactionSummary;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionProcessor implements Tasklet,
                                             StepExecutionListener {
    private Logger logger = LoggerFactory.getLogger(
            TransactionProcessor.class);
    private List<FutureTransaction> transactionList;
    private List<TransactionSummary> transactionSummaries;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();
        this.transactionList = (List<FutureTransaction>) executionContext.get("transactionList");
        logger.debug("Transaction Processor initialized.");
        StepExecutionListener.super.beforeStep(stepExecution);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Map<List<String>, Long> map = transactionList.stream()
                                                     .collect(Collectors.groupingBy(
                                                             line -> Arrays.asList(line.transformToClientInformation(), line.transformToProductInformation()),
                                                             Collectors.summingLong(FutureTransaction::transformToTransactionAmount)));


        transactionSummaries =
                map.entrySet()
                   .stream()
                   .map(e -> new TransactionSummary(e.getKey().get(0), e.getKey().get(1), e.getValue()))
                   .collect(Collectors.toList());

        logger.info("Total transaction Summary records: " + transactionSummaries.size());

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("transactionSummaries", this.transactionSummaries);
        logger.debug("Transaction Processor ended.");
        return ExitStatus.COMPLETED;
    }
}
