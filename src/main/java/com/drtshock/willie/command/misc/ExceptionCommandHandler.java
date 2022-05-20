package com.drtshock.willie.command.misc;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExceptionCommandHandler implements CommandHandler{

    private final List<Exception> exceptions;

    public ExceptionCommandHandler() {
        this.exceptions = new ArrayList<>();
        exceptions.add(new DrtshockNotFoundException());
        exceptions.add(new RibesgTypoedAgainExetpion());
        exceptions.add(new WillieIsNotSkynetYetException());
        exceptions.add(new AnswerDifferentThan42Exception());
        exceptions.add(new BryanIsNotInTheKitchenException());
        exceptions.add(new ThisMayBeTheMostUselessCommandEverException());
        exceptions.add(new UsingExceptionsToSpeakCouldBeABadThingException());
        exceptions.add(new ThisIsNotAnException());
    }


    @Override
	public void handle(Willie bot, Channel channel, User sender, String[] args) throws Exception {
        throw getRandomException();
	}

    private Exception getRandomException() {
        return exceptions.get(new Random().nextInt(exceptions.size()));
    }

    private class DrtshockNotFoundException extends Exception {}
    private class RibesgTypoedAgainExetpion extends Exception {}
    private class WillieIsNotSkynetYetException extends Exception {}
    private class AnswerDifferentThan42Exception extends Exception {}
    private class BryanIsNotInTheKitchenException extends Exception {}
    private class ThisMayBeTheMostUselessCommandEverException extends Exception {}
    private class UsingExceptionsToSpeakCouldBeABadThingException extends Exception {}
    private class ThisIsNotAnException extends Exception {}
}
