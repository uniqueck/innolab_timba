package com.innolab.timba.handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class HelpIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("AMAZON.HelpIntent"));
	}

	/**
	 * Antwort, wenn Skill ohne Intent aufgrufen wird
	 */
	public Optional<Response> handle(HandlerInput input) {
		String text = "help";
		return input.getResponseBuilder().withSpeech(text).withSimpleCard("HelloWorld", text).withReprompt(text)
				.build();
	}

}
