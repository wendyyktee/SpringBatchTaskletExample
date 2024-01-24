package com.example.springbatchtaskletexample.Utils;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.springbatchtaskletexample.Model.TransactionSummary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private final String outputFilePrefix;
    private final String outputFileSuffix;
    private final Logger logger = LoggerFactory
            .getLogger(FileUtils.class);
    Path tempFile;

    public FileUtils(String outputFilePrefix, String outputFileSuffix) throws IOException {
        this.outputFilePrefix = outputFilePrefix;
        this.outputFileSuffix = outputFileSuffix;
        tempFile = Files.createTempFile(outputFilePrefix, outputFileSuffix);
        logger.info(tempFile.toAbsolutePath().normalize().toString());

    }

    public void writeLine(TransactionSummary transaction) throws Exception {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(new FileWriter(tempFile.toString()));

        String[] singleData = new String[3];
        singleData[0] = transaction.getClientInformation();
        singleData[1] = transaction.getProductInformation();
        singleData[2] = String.valueOf(transaction.getTotalTransactionAmount());

        csvWriter.writeNext(singleData);

        csvWriter.close();
    }
}
