package com.tycrek.timberlake.sinks;

import com.tycrek.timberlake.Log;
import com.tycrek.timberlake.Sink;

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
        System.err.printf("%s%n", message);
    }
}
