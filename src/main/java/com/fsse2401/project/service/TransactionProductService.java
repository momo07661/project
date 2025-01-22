package com.fsse2401.project.service;


import com.fsse2401.project.data.transaction.entity.TransactionEntity;
import com.fsse2401.project.data.transaction.entity.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {



    List<TransactionProductEntity> postAllTransactionProduct(TransactionEntity transactionEntity);
}
