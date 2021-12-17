package com.tycrek.timberlake;

/**
 * A <strong>Sink</strong> is a place where a stream of data can be sent.
 * <p>
 * Sink implementations will implement two methods: <code>out</code> & <code>error</code>.
 */
public interface Sink {
    /**
     * Normal output stream (file descriptor 1)
     */
    void out(Log log, String message);

    /**
     * Error output stream (file descriptor 2)
     */
    void error(Log log, String message);
}
