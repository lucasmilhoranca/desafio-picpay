package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.buildrun.picpay.controller.dto.TransferDto;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InsufficientBalanceException;
import tech.buildrun.picpay.exception.TransferNotAllowedForTypeException;
import tech.buildrun.picpay.exception.TransferNotAuthorizedException;
import tech.buildrun.picpay.exception.WalletNotFoundException;
import tech.buildrun.picpay.repository.TransferRepository;
import tech.buildrun.picpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final NotificationService notificationService;
    private final AuthorizationService authorizationService;

    public TransferService(NotificationService notificationService,
                           AuthorizationService authorizationService,
                           TransferRepository transferRepository,
                           WalletRepository walletRepository) {
        this.notificationService = notificationService;
        this.authorizationService = authorizationService;
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var reciver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        reciver.credit(transferDto.value());

        var transfer = new Transfer(sender, reciver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(reciver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if(!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForTypeException();
        }

        if(!sender.isBalancerEqualOrGreaterThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
