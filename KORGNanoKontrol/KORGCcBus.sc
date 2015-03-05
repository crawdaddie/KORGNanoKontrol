/*
MidiCcBus by Jonathan Siemasko - Email: schemawound@yahoo.com - Web: http://schemawound.com/

This control registers a function to update a bus from a specific CC message/Channel combo.  The bus will output the values 0-1.
Includes shotcuts for map, value and creating a Ugen from the bus
*/

KORGCcBus {
	var <cc, <channel, <scInPort, <name;
	var <bus, <def, displayDebugInfo, <>func, <>mappingFunc;

	*new{ arg cc, channel = 0, scInPort, name;
		^super
		.newCopyArgs(cc, channel, scInPort, name)
		.init()
	}

	init{
		func = {};
		mappingFunc = {arg x; x/127};
		if(cc.notNil && channel.notNil && scInPort.notNil, {
			bus = Bus.control(Server.default, 1);



			//Set bus value.  Divide by 127 to normalize to 0..1
			def = MIDIdef.cc(name, { arg val;
				this.bus.set(this.mappingFunc.value(val));
				func.value(val);
				},
				ccNum: cc,
				chan: channel,
				srcID: scInPort
			);
			def.permanent = true;
		});
	}

	//Set to display debug values to the console
	debug{|setDebug = true| displayDebugInfo = setDebug;}

	//Returns a map for controlling a synth's node inputs
	map{^bus.asMap;}

	//sets the mappingFunc for the control


	//Returns the current bus value
	value{^bus.getSynchronous;}

	//Returns an OutputProxy mapped to the bus to use it inside of SynthDefs
	//Range is normalized from 0 to 1
	ar{|mul=1, add=0, lagTime = 0.1|^MulAdd(Lag.ar(bus.ar(1), lagTime), mul, add);}
	kr{|mul=1, add=0, lagTime = 0.1|^MulAdd(Lag.kr(bus.kr(1), lagTime), mul, add);}
}