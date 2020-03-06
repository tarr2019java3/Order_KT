public class EmptyPositionExeception extends RuntimeException {
    public EmptyPositionExeception() {

        super("This position is empty! Choose existing position");
    }
}
