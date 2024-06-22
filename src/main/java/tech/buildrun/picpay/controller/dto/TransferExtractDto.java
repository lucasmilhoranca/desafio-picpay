package tech.buildrun.picpay.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferExtractDto(UUID transferId,
                                 WalletExtractDto sender,
                                 WalletExtractDto receiver,
                                 BigDecimal value,
                                 String operationType) {
}
