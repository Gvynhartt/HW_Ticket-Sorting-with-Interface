import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {

    TicketRepository ticketRepo = new TicketRepository();
    TicketManager ticketMngr = new TicketManager(ticketRepo);

    TicketEntry ticket1 = new TicketEntry(1, 5928, "MSK", "PET", 300);
    TicketEntry ticket2 = new TicketEntry(2, 2001, "BST", "WTC", 911);
    TicketEntry ticket3 = new TicketEntry(3, 4096, "SAS", "SOS", 404);
    TicketEntry ticket4 = new TicketEntry(4, 1138, "LOL", "KEK", 512);
    TicketEntry ticket5 = new TicketEntry(5, 1940, "GER", "CVN", 1236);

    TicketEntry ticket6 = new TicketEntry(6, 9001, "MSK", "PET", 303);

    TicketEntry ticket7 = new TicketEntry(7, 2501, "MSK", "PET", 731);
    TicketEntry ticket8 = new TicketEntry(8, 2501, "MSK", "PET", 371);

    @Test
    public void shdFindTicketsIfSame() { /** проверяет поиск, если есть совпадения в полях одного билета */
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry[] expected = {ticket3};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("SAS", "SOS");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketsIfDifferent() { /** если совпадения в полях двух разных билетов */
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry[] expected = {};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("GER", "PET");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketsIfSameMultiple() { /** если запросу соответствует несколько билетов */
        ticketRepo.addTicketToRepo(ticket1); /** нужные города, цена 5928 */
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket6); /** нужные города, цена 9001 */
        ticketRepo.addTicketToRepo(ticket7); /** нужные города, цена 2501 */

        TicketEntry[] expected = {ticket7, ticket1, ticket6};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("MSK", "PET");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketsIfNonexistent() { /** если введён запрос с отсутствующим в базе кодом IATA */
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket6);
        ticketRepo.addTicketToRepo(ticket7);

        TicketEntry[] expected = {};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("AIU", "IOU");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketsIfSamePrice() { /** если находятся два билета с одинаковой ценой */
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);
        ticketRepo.addTicketToRepo(ticket8);
        ticketRepo.addTicketToRepo(ticket7);

        TicketEntry[] expected = {ticket8, ticket7};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("MSK", "PET");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketsIfFirstCheaperThanSecond() { /** если находятся два билета, и 1 дешевле 2*/
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);
        ticketRepo.addTicketToRepo(ticket6);

        TicketEntry[] expected = {ticket1, ticket6};
        TicketEntry[] actual = ticketMngr.findAllTicketsByPort("MSK", "PET");

        Assertions.assertArrayEquals(expected, actual);
    }
}
