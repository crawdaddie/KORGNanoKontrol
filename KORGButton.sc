KORGButton {
	var <cc, <name, <channel, <scInPort, <scOutPort, <ctrl, <>func0, <>func127;

	*new { arg cc, name = \ctrl, channel, scInPort, scOutPort;
		^super
		.newCopyArgs(cc, name, channel, scInPort, scOutPort)
		.init()
	}

	init {

		func0 = {};
		func127 = {};
		ctrl = MIDIFunc.cc( { arg val;

			switch ( val,
				127, func127.value(),
				0, func0.value()
			);


		}, cc, scInPort);

	}

}
