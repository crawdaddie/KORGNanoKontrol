KORGMapper {
	var <defaultChannel, <scInPort, <knobs, <sliders, <buttons;

	*new { arg defaultChannel = 0;
		^super
		.newCopyArgs(defaultChannel)
		.init()
	}

	init {
		//Connect to MIDI sources if noone has bothered to do it yet.
		if(MIDIClient.sources.isNil, {MIDIIn.connect});
		//Find nanoKONTROL in and out ports
		if(scInPort.isNil, {scInPort = this.detectInPort});

		knobs = Array.newClear(8);
		sliders = Array.newClear(8);
		buttons = Array.newClear(24);

		this.postInfo;

	}

	postInfo {
		''.postln;
		('nanoKONTROL2: In - ' ++ scInPort).postln;
		(Char.tab ++ 'knobs - ' ++ knobs.size).postln;
		(Char.tab ++ 'sliders - ' ++ sliders.size).postln;
		(Char.tab ++ 'buttons - ' ++ buttons.size).postln;
		''.postln;
	}








	detectInPort{^(MIDIClient.sources.detect({arg item;
		item.device.find("nanoKONTROL2").notNil}) !? _.uid ? 0);}


	createKnob { arg index, cc, channel;
		var name = \knob ++ '_' ++ index;
		if(channel.isNil, {channel = defaultChannel});
		knobs = knobs.put(index, KORGControl(cc, name, channel, scInPort));

	}

	createSlider { arg index, cc, channel;
		var name = \slider ++ '_' ++ index;
		if(channel.isNil, {channel = defaultChannel});
		sliders = sliders.put(index, KORGControl(cc, name, channel, scInPort));
	}

	createButton { arg index, cc, channel;
		var name = \button ++ '_' ++ index;
		if(channel.isNil, {channel = defaultChannel});
		buttons = buttons.put(index, KORGButton(cc, name, channel, scInPort));


	}

	setup {

		this.createKnob(0, 16);
		this.createKnob(1, 17);
		this.createKnob(2, 18);
		this.createKnob(3, 19);
		this.createKnob(4, 20);
		this.createKnob(5, 21);
		this.createKnob(6, 22);
		this.createKnob(7, 23);

		this.createSlider(0, 0);
		this.createSlider(1, 1);
		this.createSlider(2, 2);
		this.createSlider(3, 3);
		this.createSlider(4, 4);
		this.createSlider(5, 5);
		this.createSlider(6, 6);
		this.createSlider(7, 7);

		//buttons are arranged in groups of 3 next to each slider
		//therefore this.buttons[0] is the first s button
		//this.buttons[1] is the first m button
		//this.buttons[2] is the first r button
		//and so on
		this.createButton(0, 32);
		this.createButton(1, 48);
		this.createButton(2, 64);
		this.createButton(3, 33);
		this.createButton(4, 49);
		this.createButton(5, 65);
		this.createButton(6, 34);
		this.createButton(7, 50);
		this.createButton(8, 66);
		this.createButton(9, 35);
		this.createButton(10, 51);
		this.createButton(11, 67);
		this.createButton(12, 36);
		this.createButton(13, 52);
		this.createButton(14, 68);
		this.createButton(15, 37);
		this.createButton(16, 53);
		this.createButton(17, 69);
		this.createButton(18, 38);
		this.createButton(19, 54);
		this.createButton(20, 70);
		this.createButton(21, 39);
		this.createButton(22, 55);
		this.createButton(23, 71);

	}



}