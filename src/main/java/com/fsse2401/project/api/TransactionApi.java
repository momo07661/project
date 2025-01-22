package com.fsse2401.project.api;

import com.fsse2401.project.config.EnvConfig;
import com.fsse2401.project.data.transaction.dto.ResponseTransactionDto;
import com.fsse2401.project.data.transaction.dto.UpdateResponseTransactionDto;
import com.fsse2401.project.service.TransactionService;
import com.fsse2401.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.fsse2401.project.config.EnvConfig.PRO_S3_BASE_URL;

@RestController
@RequestMapping("/transaction")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PRO_BASE_URL, PRO_S3_BASE_URL})
public class TransactionApi {
    @Autowired
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public ResponseTransactionDto prepareTransaction(JwtAuthenticationToken jwtToken){
        return new ResponseTransactionDto(transactionService.postTransaction(JwtUtil.getFirebaseUserData(jwtToken)));
    }

    @GetMapping("/{tid}")
    public ResponseTransactionDto getTransactionById(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new ResponseTransactionDto(transactionService.getTransactionById(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }

    @PatchMapping("/{tid}/pay")
    public UpdateResponseTransactionDto updateTransactionStatus(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new UpdateResponseTransactionDto(transactionService.updateTransactionStatus(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }

    @PatchMapping("/{tid}/finish")
    public ResponseTransactionDto finishTransactionStatus(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new ResponseTransactionDto(transactionService.finishTransactionStatus(JwtUtil.getFirebaseUserData(jwtToken), tid));
    }
}
