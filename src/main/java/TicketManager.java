import java.util.Arrays;

public class TicketManager {
    private TicketRepository ticketRepo;

    public TicketManager(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public TicketEntry[] findAllTicketsByPort(String whereFrom, String whereTo) {
        TicketEntry[] ticketDatabse = ticketRepo.getTicketDatabase();
        TicketEntry[] bufferDatabase = new TicketEntry[ticketDatabse.length];
        int pos = 0;
        int matchCount = 0;
        /** в задании, кстати. не уточняется, где именно ищутся совпадения: код 1 и код 2 должны находиться строго
         *  в полях одного билета или в разных - поэтому я сделал самый ленивый вариант, да */
        for (TicketEntry targetTicket : ticketDatabse) {
            if (targetTicket.getPortDeparture().contains(whereFrom) && targetTicket.getPortArrival().contains(whereTo)) {
                bufferDatabase[pos] = targetTicket;
                pos++;
                matchCount++;
            } else {
                pos++;
            }
        }

        TicketEntry[] resultDatabase = new TicketEntry[matchCount];
        int posResult = 0;
        pos = 0;

        for (pos = 0; pos < bufferDatabase.length; pos++) {
            if (bufferDatabase[pos] != null) {
                resultDatabase[posResult] = bufferDatabase[pos];
                posResult++;
            }
        }
        Arrays.sort(resultDatabase); /** добавляем сортировку от меньшего значения (цены) к большей */
        return resultDatabase;
    }
}
