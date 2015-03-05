KORGControl {
	var <cc, <name, <channel, <scInPort, <ctrl;

	*new { arg cc, name, channel, scInPort;
		^super
		.newCopyArgs(cc, name, channel, scInPort)
		.init()
	}

	init {
		ctrl = KORGCcBus(cc, channel, scInPort, name);
	}
}