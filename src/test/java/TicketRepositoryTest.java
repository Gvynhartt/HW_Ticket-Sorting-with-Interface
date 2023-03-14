import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketRepositoryTest {

    TicketRepository ticketRepo = new TicketRepository();

    TicketEntry ticket1 = new TicketEntry(1, 5928, "MSK", "PET", 300);
    TicketEntry ticket2 = new TicketEntry(2, 2001, "BST", "WTC", 911);
    TicketEntry ticket3 = new TicketEntry(3, 4096, "SAS", "SOS", 404);
    TicketEntry ticket4 = new TicketEntry(4, 1138, "LOL", "KEK", 512);
    TicketEntry ticket5 = new TicketEntry(5, 1940, "GER", "CVN", 1236);

    @Test
    public void shdAddToRepo() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        TicketEntry[] actual = ticketRepo.getTicketDatabase();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdAddToRepoIfAlreadyExists() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {ticketRepo.addTicketToRepo(ticket5);});
    }

    @Test
    public void shdReturnAllTicketsAdded() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        TicketEntry[] actual = ticketRepo.returnAllTicketsAdded();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdRemoveFromRepoByEgo() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);
        ticketRepo.removeTicketById(3);

        TicketEntry[] expected = {ticket1, ticket2, ticket4, ticket5};
        TicketEntry[] actual = ticketRepo.getTicketDatabase();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shdFindTicketByEgo() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry expected = ticket2;
        TicketEntry actual = ticketRepo.findTicketById(2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shdFindTicketByEgoIfNonexistent() {
        ticketRepo.addTicketToRepo(ticket1);
        ticketRepo.addTicketToRepo(ticket2);
        ticketRepo.addTicketToRepo(ticket3);
        ticketRepo.addTicketToRepo(ticket4);
        ticketRepo.addTicketToRepo(ticket5);

        TicketEntry expected = null;
        TicketEntry actual = ticketRepo.findTicketById(14);

        Assertions.assertEquals(expected, actual);
    }
}
