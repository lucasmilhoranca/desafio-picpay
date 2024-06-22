package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;
import tech.buildrun.picpay.controller.dto.*;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InvalidCpfCnpjException;
import tech.buildrun.picpay.exception.WalletDataAlreadyExistsException;
import tech.buildrun.picpay.exception.WalletNotFoundException;
import tech.buildrun.picpay.repository.TransferRepository;
import tech.buildrun.picpay.repository.WalletRepository;
import tech.buildrun.picpay.utils.ValidaCpfCnpj;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    private final TransferRepository transferRepository;

    public WalletService(WalletRepository walletRepository, TransferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        if (!ValidaCpfCnpj.isValidCpfCnpj(dto.cpfCnpj())) {
            throw new InvalidCpfCnpjException("Invalid CpfCnpj");
        }

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }

    public ExtractDto getExtract(Long walletId) {
        var wallet = walletRepository.findById(walletId);

        if(wallet.isEmpty()) {
            throw new WalletNotFoundException(walletId);
        }

        var transactions = transferRepository.findBySenderOrReceiver(wallet.get(), wallet.get());

        var transactionsDto = transactions
                .stream()
                .map(transfer -> new TransferExtractDto(
                        transfer.getId(),
                        new WalletExtractDto(transfer.getSender().getFullName(), transfer.getSender().getCpfCnpj()),
                        new WalletExtractDto(transfer.getReceiver().getFullName(), transfer.getReceiver().getCpfCnpj()),
                        transfer.getValue(),
                        walletId.equals(transfer.getSender().getId()) ? "debit" : "credit"
                ))
                .toList();

        return new ExtractDto(
                wallet.get().getFullName(),
                wallet.get().getCpfCnpj(),
                wallet.get().getBalance(),
                transactionsDto
                );
    }
}
