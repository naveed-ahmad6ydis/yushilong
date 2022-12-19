package com.drtshock.willie.command.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.pircbotx.Channel;
import org.pircbotx.User;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import com.drtshock.willie.command.misc.stupidexception.AnswerDifferentThan42Exception;
import com.drtshock.willie.command.misc.stupidexception.BryanIsNotInTheKitchenException;
import com.drtshock.willie.command.misc.stupidexception.DrtshockNotFoundException;
import com.drtshock.willie.command.misc.stupidexception.PopcornOutOfBoundsException;
import com.drtshock.willie.command.misc.stupidexception.RibesgTypoedAgainExetpion;
import com.drtshock.willie.command.misc.stupidexception.ThisIsNotAnException;
import com.drtshock.willie.command.misc.stupidexception.ThisMayBeTheMostUselessCommandEverException;
import com.drtshock.willie.command.misc.stupidexception.UsingExceptionsToSpeakCouldBeABadThingException;
import com.drtshock.willie.command.misc.stupidexception.WillieIsNotSkynetYetException;

public class ExceptionCommandHandler implements CommandHandler {

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
		exceptions.add(new PopcornOutOfBoundsException());
	}

	@Override
	public void handle(Willie bot, Channel channel, User sender, String[] args) throws Exception {
		throw getRandomException();
	}

	private Exception getRandomException() {
		return exceptions.get(new Random().nextInt(exceptions.size()));
	}
}
