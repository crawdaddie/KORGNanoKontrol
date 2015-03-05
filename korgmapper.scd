y = Buffer.readChannel(s, "your sound file", channels: 0);



MIDIIn.connectAll;
a = KORGMapper.new;
a.setup;



SynthDef(\grains, { arg trigFreq = 1, rate = 1, pos = 0, grainDur = 0.1, buf;
	var trig = Impulse.ar(trigFreq);
	var sound = GrainBuf.ar(2, trig, grainDur, buf, rate, pos);
	Out.ar(0, sound);

}).add;



//.map maps the control bus created by each KORGControl to the synth arguments
g = Synth(\grains, [
	\trigFreq, a.knobs[0].ctrl.mappingFunc_({ arg x; x.linlin(0,127,0.1,100)}).map, //using your own mapping function
	\rate, a.sliders[0].ctrl.mappingFunc_({ arg x; x.linlin(0,127,0.1,2)}).map,
	\pos, a.knobs[1].ctrl.map, //maps from 0-1 by default
	\grainDur, a.knobs[2].ctrl.mappingFunc_({ arg x; x.linlin(0,127,0.01,1)}).map,
	\buf, y
]);

//the button controls still need a little work but basically should work like this:


a.buttons[0].func({"pressing button 0".postln},{"stopped pressing button 0".postln});



//all the stuff is contained in MIDIdefs so you can replace each one by just doing the .mappingFunc or the button[0].func({},{}) methods again
//or get rid of them all by doing MIDIdef.freeAll;