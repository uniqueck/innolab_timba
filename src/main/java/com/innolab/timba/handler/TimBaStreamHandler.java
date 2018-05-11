package com.innolab.timba.handler;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class TimBaStreamHandler extends SkillStreamHandler {

	public TimBaStreamHandler() {
		super(Skills.standard().addRequestHandlers(new CancelAndStopIntentHandler(), new InnoLabBadeniaIntentHandler(),
				new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler()).build());
	}

}
