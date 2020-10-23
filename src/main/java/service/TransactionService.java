package service;

import dao.TransactionDao;
import dao.TransactionDaoImpl;
import model.Transaction;
import util.TransactionType;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionService {
    private TransactionDao transactionDao;

    public TransactionService() {
        transactionDao = new TransactionDaoImpl();
    }

    public Long getTotal(Date start, Date end, String merchant) {
        Long total = 0L;
        try {
            total= getActualTransactions(start, end, merchant).count();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return total;
    }

    public Float getAverage(Date start, Date end, String merchant) {
        Float average = 0f;
        try {
            average = (float)getActualTransactions(start, end, merchant)
                    .mapToDouble(Transaction::getCost).average().getAsDouble();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return average;
    }

    private Stream<Transaction> getActualTransactions(Date start, Date end, String merchant) throws Exception {
        if (start.after(end)) {
            throw new IllegalArgumentException("Start date later than end date");
        }
        Float average = null;

        List<Transaction> all = transactionDao.getAll();

        transactionDao.getAll().stream()
                .filter(transaction -> transaction.getRelatedTransaction() != null
                        && transaction.getType() == TransactionType.REVERSAL)
                .forEach(all::remove);

        return all.stream()
                .filter(transaction -> transaction.getMerchant().equals(merchant))
                .filter(transaction -> !transaction.getDate().before(start)
                        && !transaction.getDate().after(end));


    }
}
