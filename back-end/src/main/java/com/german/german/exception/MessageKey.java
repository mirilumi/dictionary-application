package com.german.german.exception;

import lombok.Getter;

@Getter
public class MessageKey {
    private final String name;
    private final int numTokens;

    private MessageKey(String key, int numTokens){
        this.name = key;
        this.numTokens = numTokens;
    }
    public static final MessageKey entity_not_found = new MessageKey("entity_not_found",1);


}
