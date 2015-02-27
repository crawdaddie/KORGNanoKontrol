KORGControl {
	var <cc, <name, <channel, <scInPort, <scOutPort, <ctrl;

	*new { arg cc, name = \ctrl, channel, scInPort, scOutPort;
		^super
		.newCopyArgs(cc, name, channel, scInPort, scOutPort)
		.init()
	}

	init {
		ctrl = MidiCcBus(cc, channel, scInPort, scOutPort, name);

	}
}