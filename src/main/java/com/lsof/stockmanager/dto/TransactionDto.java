package com.lsof.stockmanager.dto;

import com.lsof.stockmanager.model.Transaction;
import com.lsof.stockmanager.model.TransactionType;
import com.lsof.stockmanager.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionDto {

    private Integer id;

    @Positive
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;

    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .destinationIban(transactionDto.getDestinationIban())
                .user(
                        User.builder()
                                .id(transactionDto.getUserId())
                                .build()
                )
                .build();
    }



}
