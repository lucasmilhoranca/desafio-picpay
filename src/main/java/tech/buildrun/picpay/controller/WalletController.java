package tech.buildrun.picpay.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.picpay.controller.dto.CreateWalletDto;
import tech.buildrun.picpay.controller.dto.ExtractDto;
import tech.buildrun.picpay.controller.dto.TransferExtractDto;
import tech.buildrun.picpay.entity.Transfer;
import tech.buildrun.picpay.entity.Wallet;
import tech.buildrun.picpay.service.WalletService;

import java.util.List;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto) {
        var wallet = walletService.createWallet(dto);

        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/extract/{walletId}")
    public ResponseEntity<ExtractDto> walletExtract(@PathVariable("walletId") Long walletId ) {

        var extract = walletService.getExtract(walletId);

        return ResponseEntity.ok(extract);
    }
}
