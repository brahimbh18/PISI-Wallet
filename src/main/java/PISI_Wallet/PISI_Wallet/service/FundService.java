package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.BankCard;
import PISI_Wallet.PISI_Wallet.model.Fund;
import PISI_Wallet.PISI_Wallet.model.Wallet;
import PISI_Wallet.PISI_Wallet.repository.BankCardRepository;
import PISI_Wallet.PISI_Wallet.repository.FundRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundService {
    private final FundRepository fundRepository;
    private final BankCardRepository bankCardRepository;
    private final WalletService walletService;

    public FundService(FundRepository fundRepository, BankCardRepository bankCardRepository, WalletService walletService) {
        this.fundRepository = fundRepository;
        this.bankCardRepository = bankCardRepository;
        this.walletService = walletService;
    }

    public boolean createFund(Fund fund, BankCard bankCard) {
        BankCard card = bankCardRepository.getCardByNum(bankCard.getCardNumber());
        if (card == null) {
            bankCardRepository.createCard(bankCard);
        } else if (card.getCvv() != bankCard.getCvv()){
            return false;
        }
        fund.setCardId(bankCardRepository.getCardByNum(bankCard.getCardNumber()).getId());
        fundRepository.createFund(fund);

        walletService.updateBalanceById(fund.getWalletId(), fund.getAmount());
        return true;
    }

    public List<Fund> getFundsByWalletId(int id) {
        return fundRepository.getFundsByWalletId(id);
    }
} 