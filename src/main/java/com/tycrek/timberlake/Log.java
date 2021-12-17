package com.tycrek.timberlake;

import com.tycrek.timberlake.sinks.SystemSink;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class Log {
    @Getter private final String channelName;
    private final List<Sink> sinks = new ArrayList<>();
    private final ConcurrentHashMap<String, Channel> channels = new ConcurrentHashMap<>();

    //#region Configurable options
    /**
     * Show the channel name in the log message?
     */
    @Getter @Setter private boolean includeName = true;
    /**
     * Should the channel name be uppercase?
     */
    @Getter @Setter private boolean uppercaseName = true;
    /**
     * Show timestamp in the log message?
     */
    @Getter @Setter private boolean showTimestamp = true;
    //#endregion

    /**
     * Sample usage of the {@link Log} class
     */
    public static void main(String[] args) {

        // Create a new Log instance. Channel name could be the title of your project, for example.
        var log = new Log("timberlake");

        // Add any Sinks you want to use. The default Sink is a SystemSink, which prints to the console.
        log.addSink(new SystemSink());

        // Create any new Channels you want to use. Make sure you add all Sinks before calling this method!
        var epicChannel = log.channel("epic");

        // Call the logger methods
        log.info("Hello?");
        epicChannel.info("Hello!");
    }

    /**
     * Adds a {@link Sink} to the list of sinks. <strong>This should be called <em>before</em> <code>.channel()</code>!</strong>
     */
    public void addSink(Sink sink) {
        sinks.add(sink);
    }

    /**
     * Spawns a new {@link Channel} with the given name. If the channel already exists, it will return the existing channel.
     * <strong>Remember to add all sinks before calling this method!</strong>
     *
     * @param channelName The name of the channel. Will be shown in the logs (if enabled)
     */
    public Channel channel(String channelName) {
        return channels.computeIfAbsent(channelName, unused -> {
            var newChannel = new Channel(channelName, this);
            sinks.forEach(newChannel::addSink);
            return newChannel;
        });
    }

    /**
     * Logs an info message
     */
    public void info(String message) {
        sinks.forEach(sink -> sink.out(this, String.format("[%s] %s", Level.INFO, message)));
    }
}
