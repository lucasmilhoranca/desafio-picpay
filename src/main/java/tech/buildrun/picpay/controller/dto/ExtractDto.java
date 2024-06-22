package tech.buildrun.picpay.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record ExtractDto(String fullName,
                         String cpfCnpj,
                         BigDecimal balance,
                         List<TransferExtractDto> extract) {
}
