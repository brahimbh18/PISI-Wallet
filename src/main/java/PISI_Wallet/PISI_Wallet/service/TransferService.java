package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.Transfer;
import PISI_Wallet.PISI_Wallet.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final WalletService walletService;
    public TransferService(TransferRepository transferRepository, WalletService walletService) {
        this.transferRepository = transferRepository;
        this.walletService = walletService;
    }


    public boolean createTransfer(Transfer transfer) {
        double balance = walletService.getBalanceByid(transfer.getWalletId());
        if (balance < transfer.getAmount()) {
            return false;
        }
        transferRepository.createTransfer(transfer);
        walletService.updateBalanceById(transfer.getWalletId(), transfer.getAmount() * -1);
        walletService.updateBalanceById(transfer.getReceivingWalletId(), transfer.getAmount() * 1);
        return true;
    }


    public List<Transfer> getTransfersByWalletId(int id) {
        return transferRepository.getTransfersByWalletId(id);
    }}
