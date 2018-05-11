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

public class StundenGebuchtIntentHandler implements RequestHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(StundenGebuchtIntentHandler.class);
	
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("StundenGebuchtIntent"));
	}

	public Optional<Response> handle(HandlerInput input) {
		LOGGER.info("start - handle");
		
		int anzahlStunden = getAnzahlStunden(getZeitpunkt(input));
		
		String speechText;
		if (anzahlStunden==-1) {
			speechText = "Ich habe dich nicht verstanden";
		}
		else {
			speechText = "Du hast " + getZeitpunkt(input) + " " + anzahlStunden + " Stunden gearbeitet.";
		}
		
		
		return getAntwort(input, speechText);
	}
	
	private Optional<Response> getAntwort(HandlerInput input, String speechText) {
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Timba", speechText).withReprompt("Möchtest du noch mehr wissen?").build();
	}
	
	protected String getSlotValue(HandlerInput input, String slotName) {
		Slot slot = getIntent(input).getSlots().get(slotName);
		return slot==null ? null : slot.getValue();
	}
	
	protected String getZeitpunkt(HandlerInput input) {
		return getSlotValue(input, "zeitpunkt");
	}
	
	protected int getAnzahlStunden(String zeitpunkt) {
		switch (zeitpunkt) {
		case "heute":
		case "aktuell":
			return 7;

		case "gestern":
			return 9;
			
		case "diese woche":
			return 21;

		case "letzte woche":
			return 42;

		default:
			return -1;
		}
	}
	
	protected IntentRequest getIntentRequest(HandlerInput input) {
		return (IntentRequest) input.getRequestEnvelope().getRequest();
	}
	
	protected Intent getIntent(HandlerInput input) {
		return getIntentRequest(input).getIntent();
	}

}
