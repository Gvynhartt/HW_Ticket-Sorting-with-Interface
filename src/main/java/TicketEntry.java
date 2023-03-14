public class TicketEntry implements Comparable<TicketEntry> {

private int ticketId;
private int ticketPrice;
private String portDeparture;
private String portArrival;
private int fliteDuration;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getPortDeparture() {
        return portDeparture;
    }

    public void setPortDeparture(String portDeparture) {
        this.portDeparture = portDeparture;
    }

    public String getPortArrival() {
        return portArrival;
    }

    public void setPortArrival(String portArrival) {
        this.portArrival = portArrival;
    }

    public int getFliteDuration() {
        return fliteDuration;
    }

    public void setFliteDuration(int fliteDuration) {
        this.fliteDuration = fliteDuration;
    }

    public TicketEntry(int ticketId, int ticketPrice, String portDeparture, String portArrival, int fliteDuration) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.portDeparture = portDeparture;
        this.portArrival = portArrival;
        this.fliteDuration = fliteDuration;
    }

    @Override
    public int compareTo(TicketEntry o) { /** добавляем метод сравнения билетов по цене */
        if (this.ticketPrice < o.ticketPrice) {
            return -1;
        } else if (this.ticketPrice > o.ticketPrice) {
            return 1;
        } else {
            return 0;
        }
    }
}
