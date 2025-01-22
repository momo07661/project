package com.fsse2401.project.service;

import com.fsse2401.project.data.transaction.domainObject.ResponseTransactionData;
import com.fsse2401.project.data.user.domainObject.FireBaseUserData;

public interface TransactionService {
    ResponseTransactionData postTransaction(FireBaseUserData data);

    ResponseTransactionData getTransactionById(FireBaseUserData data, Integer tid);

    ResponseTransactionData updateTransactionStatus(FireBaseUserData data, Integer tid);

    ResponseTransactionData finishTransactionStatus(FireBaseUserData data, Integer tid);

    void updateTransactionSessionId(FireBaseUserData data, Integer tid, String sessionId);
}
