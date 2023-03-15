import java.util.Comparator;

public class TicketByDurationComparator implements Comparator<TicketEntry> {
    @Override
    public int compare(TicketEntry o1, TicketEntry o2) {
        if (o1.getFliteDuration() > o2.getFliteDuration()) {
            return 1;
        } else if (o1.getFliteDuration() < o2.getFliteDuration()) {
            return -1;
        } else {
            return 0;
        }
    }
}
