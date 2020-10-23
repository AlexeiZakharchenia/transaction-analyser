import service.TransactionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        TransactionService service = new TransactionService();
        String startStr = "20/07/2018 13:12:22";
        String endStr = "20/08/2028 13:12:22";
        String merchant = "Kwik-E-Mart";

        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(startStr);
        Date end =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(endStr);

        System.out.println("Total: " + service.getTotal(start, end, merchant));
        System.out.println("Average: " + service.getAverage(start, end, merchant));
    }
}
