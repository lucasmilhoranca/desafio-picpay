package tech.buildrun.picpay.controller.dto;

import tech.buildrun.picpay.entity.Wallet;

public record WalletExtractDto(String fullName,
                               String Cnpj) {

    public static WalletExtractDto transformFromWallet(Wallet wallet) {
        return new WalletExtractDto(wallet.getFullName(), wallet.getCpfCnpj());
    }
}
