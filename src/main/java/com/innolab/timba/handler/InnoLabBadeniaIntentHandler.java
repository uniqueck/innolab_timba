package com.innolab.timba.handler;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

public class InnoLabBadeniaIntentHandler implements RequestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(InnoLabBadeniaIntentHandler.class);
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("InnoLabBadeniaIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		System.out.println("start - handle");
		Intent intent = getIntent(input);
		Slot slot = intent.getSlots().get("zeitpunkt");
		String speechText = "Gewählter Slot: " + slot.getValue();
		LOGGER.info(speechText);
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("HelloWorld", speechText).withReprompt("Möchtest du noch mehr wissen?").build();
	}
	
	protected IntentRequest getIntentRequest(HandlerInput input) {
		return (IntentRequest) input.getRequestEnvelope().getRequest();
	}
	
	protected Intent getIntent(HandlerInput input) {
		return getIntentRequest(input).getIntent();
	}

}
