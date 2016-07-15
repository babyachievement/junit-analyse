package junit.tests.alvin.rt.segments;

/**
 * Created by IntelliJ IDEA.
 * Author: HaoQiang
 * Date: 2016/7/15
 * Time: 15:29
 *
 * @Copyright (C) 2008-2016 oneapm.com. all rights reserved.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class SegmentedOutputStream extends OutputStream implements PacketProcessor {
    private final PrintStream myPrintStream;
    private boolean myStarted = false;

    public SegmentedOutputStream(PrintStream transportStream) {
        this(transportStream, false);
    }

    public SegmentedOutputStream(PrintStream transportStream, boolean started) {
        myPrintStream = transportStream;
        myStarted = started;
        try {
            flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public synchronized void write(int b) throws IOException {
        if (b == SegmentedStream.SPECIAL_SYMBOL && myStarted) writeNext(b);
        writeNext(b);
        flush();
    }

    public synchronized void flush() throws IOException {
        myPrintStream.flush();
    }

    public synchronized void close() throws IOException {
        myPrintStream.close();
    }

    private void writeNext(int b) {
        myPrintStream.write(b);
    }

    public synchronized void processPacket(String packet) {
        if (!myStarted)
            sendStart();
        writeNext(SegmentedStream.MARKER_PREFIX);
        String encodedPacket = Packet.encode(packet);
        writeNext(String.valueOf(encodedPacket.length())+SegmentedStream.LENGTH_DELIMITER+encodedPacket);
    }

    private void writeNext(String string) {
        try {
            myPrintStream.write(string.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendStart() {
        writeNext(SegmentedStream.STARTUP_MESSAGE);
        myStarted = true;
    }

    public PrintStream getPrintStream() {
        return myPrintStream;
    }
}
