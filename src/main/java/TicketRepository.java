public class TicketRepository {

    private TicketEntry[] ticketDatabase = new TicketEntry[0];

    public TicketEntry[] getTicketDatabase() {
        return ticketDatabase;
    }

    public void setTicketDatabase(TicketEntry[] ticketDatabase) {
        this.ticketDatabase = ticketDatabase;
    }

    public void addTicketToRepo(TicketEntry newTicket) throws AlreadyExistsException { /** добавляет объект билета в репозиторий */

        for (TicketEntry targetTicket : ticketDatabase) {
            if (targetTicket.getTicketId() == newTicket.getTicketId()) {
                throw new AlreadyExistsException("Билет с таким ID (" + targetTicket.getTicketId() + ") уже добавлен в БД");
            }
        }

        TicketEntry[] bufferDatabase = new TicketEntry[ticketDatabase.length + 1];
        for (int pos = 0; pos < ticketDatabase.length; pos++) {
            bufferDatabase[pos] = ticketDatabase[pos];
        }
        bufferDatabase[bufferDatabase.length - 1] = newTicket;
        setTicketDatabase(bufferDatabase);;
    }

    public void removeTicketById(int targetId) throws NotFoundException { /** удаляет объект билета из репозитория по ID */
        TicketEntry[] ticketDatabase = getTicketDatabase();

        if (findTicketById(targetId) == null) {
            throw new NotFoundException("Билет с указанным ID (" + targetId + ") отсутствует в базе данных");
        }

        int matchCount = 0;
        for (TicketEntry newTicketEntry : ticketDatabase) {
            if (newTicketEntry.getTicketId() == targetId) {
                matchCount++;
            }
        }

        int pos = 0;
        int buffPos = 0;
        TicketEntry[] bufferDatabase = new TicketEntry[ticketDatabase.length - matchCount];
        for (TicketEntry newTicketEntry : ticketDatabase) {
            if (newTicketEntry.getTicketId() != targetId) {
                bufferDatabase[buffPos] = ticketDatabase[pos];
                pos++;
                buffPos++;
            } else {
                pos++;
            }
        }
        setTicketDatabase(bufferDatabase);
    }

    public TicketEntry[] returnAllTicketsAdded() { /** выводит все объекты билетов  , имеющиеся в репозитории */
        TicketEntry[] ticketDatabase = getTicketDatabase();
        return ticketDatabase;
    }

    public TicketEntry findTicketById(int targetId) { /** осуществляет поиск билета в БД по ID */
        TicketEntry[] ticketDatabase = getTicketDatabase();

        for (TicketEntry targetTicket : ticketDatabase) {
            if (targetTicket.getTicketId() == targetId) {
                return targetTicket;
            }
        }
        return null;
    }
}
