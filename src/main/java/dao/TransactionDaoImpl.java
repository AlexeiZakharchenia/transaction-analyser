package dao;

import exceptions.CsvParseException;
import model.Transaction;
import util.TransactionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    public static final String CSV_FILE_FILE = "input.csv";

    @Override
    public List<Transaction> getAll() throws Exception{
        String line = "";


        ArrayList<Transaction> transactions = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/" + CSV_FILE_FILE)));) {
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {

                transactions.add(parseLine(line));

            }
        } catch (IOException e) {
            throw e;
        } catch (CsvParseException e){
            throw e;
        }catch (Exception e){
            throw new IllegalStateException();
        }


        return transactions;
    }

    private Transaction parseLine(String line) throws CsvParseException{
        String csvSplitter = ",";


        String parameters[] = line.split(csvSplitter);

        String id = parameters[0];
        if(id == null){
            throw new CsvParseException("Reading empty ID");
        }


        Date date = null;

        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(parameters[1]);
        }catch (ParseException ex){
            throw new CsvParseException("Incorrect date format");
        }

        Float cost = null;

        try {
            cost = Float.valueOf(parameters[2]);
        }catch (NumberFormatException ex){
            throw new CsvParseException("Incorrect amount format");
        }

        String merchant = parameters[3].substring(1);

        if(merchant == null){
            throw new CsvParseException("Reading empty merchant field");
        }

        TransactionType type = null;

        try {
            type = TransactionType.valueOf(parameters[4].replaceAll(" ", ""));
        }catch (IllegalArgumentException ex){
            throw new CsvParseException("Incorrect type format");
        }

        String relatedTransaction = (parameters.length==6) ? parameters[5] : null;

        return new Transaction(id, date, cost, merchant, type, relatedTransaction);

    }
}
