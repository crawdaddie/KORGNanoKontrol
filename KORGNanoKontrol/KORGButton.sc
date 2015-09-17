KORGButton {
	var <cc, <name, <channel, <scInPort, <ctrl;
	var <def, <>func127, <>func0;
	var <value;

	*new { arg cc, name = \ctrl, channel, scInPort;
		^super
		.newCopyArgs(cc, name, channel, scInPort)
	.init()
	}


	init {
		value = 0;
		func127 = {};
		func0 = {};
		def = MIDIdef.cc(name, { arg val;
			switch( val,
				127, {value = 1; func127.value()},
				0, {value = 0; func0.value()});

		}, ccNum: cc, chan: channel, srcID: scInPort);
		def.permanent = true;
	}

	func { arg press, unpress;
		func127 = press;
		func0 = unpress;
		^this
	}
}