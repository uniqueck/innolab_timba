package com.innolab.timba.handler;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class LaunchRequestHandler implements RequestHandler {

	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.requestType(LaunchRequest.class));
	}

	/**
	 * Antwort, wenn Skill ohne Intent aufgrufen wird
	 */
	public Optional<Response> handle(HandlerInput input) {
		String text = "Du kannst mich fragen, wie lange hast du (heute|gestern|diese Woche) gearbeitet";
		return input.getResponseBuilder().withSpeech(text).withSimpleCard("TimBa", text).withReprompt(text)
				.build();
	}

}
