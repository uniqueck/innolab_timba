package com.innolab.timba.handler;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class CancelAndStopIntentHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
	}

	/**
	 * Antwort, wenn Skill ohne Intent aufgrufen wird
	 */
	public Optional<Response> handle(HandlerInput input) {
		String text = "ciao";
		return input.getResponseBuilder().withSpeech(text).withSimpleCard("HelloWorld", text).withReprompt(text)
				.build();
	}

}
