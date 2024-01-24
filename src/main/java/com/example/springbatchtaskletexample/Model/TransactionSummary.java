package com.example.springbatchtaskletexample.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionSummary implements Serializable {

    private String clientInformation;
    private String productInformation;
    private Long totalTransactionAmount;
}
