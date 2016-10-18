package org.java8recipes.chapter09.recipe09_07;

/**
 * User: Freddy
 * Modified by Juneau
 * Recipe 9-7
 */
public class Recipe9_7 {
    public static void main (String[] args) {
        Recipe9_7 recipe = new Recipe9_7();
        recipe.start();
    }

    Object chatServer = null;

    private void start() {
        try {
            sendChat("Hello, how are you?");
        } catch (ConnectionUnavailableException e) {
            System.out.println("Caught a connection unavailable Exception!");
        }

        disconnectChatServer(chatServer);
    }

    private void disconnectChatServer(Object chatServer) {
        if (chatServer == null) throw new IllegalChatServerException("Chat server is empty");
    }

    private void sendChat(String chatMessage) throws ConnectionUnavailableException {
        if (chatServer == null) throw new ConnectionUnavailableException("Can't find the chat server");
    }


    class ConnectionUnavailableException extends Exception {
        ConnectionUnavailableException(String message) {
            super(message);
        }
    }

    class IllegalChatServerException extends RuntimeException {
        IllegalChatServerException(String message) {
            super(message);
        }
    }
}
