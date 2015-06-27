KORGControl {
	var <cc, <name, <channel, <scInPort, <ctrls;

	*new { arg cc, name, channel, scInPort;
		^super
		.newCopyArgs(cc, name, channel, scInPort)
		.init()
	}

	init {
		ctrls = List.newClear(1);
		ctrls.put(0, KORGCcBus(cc, channel, scInPort, name ++ '_0'));

		^this;
	}

	add { arg ctrlName;
		var i = this.ctrls.size;
		var newControl;
		ctrlName.notNil.if(
			{ newControl = KORGCcBus(cc, channel, scInPort, ctrlName.asSymbol)},
			{ newControl = KORGCcBus(cc, channel, scInPort, name ++ '_'++i.asSymbol)});
		ctrls.add(newControl);
		^newControl
	}

	ctrl {
		^this.ctrls.at(0)
	}


}