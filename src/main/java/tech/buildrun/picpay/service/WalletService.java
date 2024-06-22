package tech.buildrun.picpay.service;

import org.springframework.stereotype.Service;
import tech.buildrun.picpay.controller.dto.CreateWalletDto;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.exception.InvalidCpfCnpjException;
import tech.buildrun.picpay.exception.WalletDataAlreadyExistsException;
import tech.buildrun.picpay.repository.WalletRepository;
import tech.buildrun.picpay.utils.ValidaCpfCnpj;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
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
}
