package com.tycrek.timberlake.sinks;

import com.tycrek.timberlake.Log;
import com.tycrek.timberlake.Sink;

// todo: BUG: Figure out why System.err doesn't immediately print to console
/**
 * A basic {@link Sink} that prints to the system console.
 * <p>
 * This sink simply wraps the {@link System#out} and {@link System#err} <code>.printf()</code> methods.
 */
public class SystemSink implements Sink {

    @Override
    public void out(Log log, String message) {
        System.out.printf("%s%n", message);
    }

    @Override
    public void error(Log log, String message) {
        System.out.printf("%s%n", message);
    }

    @Override
    public void error(Log log, Throwable t) {
        t.printStackTrace(System.out);
    }

    @Override
    public void error(Log log, String message, Throwable t) {
        error(log, message);
        error(log, t);
    }
}
