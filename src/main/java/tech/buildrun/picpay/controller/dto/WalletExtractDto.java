package tech.buildrun.picpay.controller.dto;


public record WalletExtractDto(Long walletId,
                               String fullName,
                               String cpfCnpj) {
}
