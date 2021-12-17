package com.tycrek.timberlake;

import lombok.Getter;

/**
 * A <strong>Channel</strong> is like a mini-{@link Log}. It shares some properties with the parent Log but has its own name.
 */
public class Channel extends Log {
    @Getter private final Log parent;

    public Channel(String channelName, Log parent) {
        super(channelName);
        this.parent = parent;
    }
}
