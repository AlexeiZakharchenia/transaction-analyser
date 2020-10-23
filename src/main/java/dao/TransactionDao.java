package dao;

import model.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionDao{
    List<Transaction> getAll() throws Exception;

}
