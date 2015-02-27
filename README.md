# KORGNanoKontrol
supercollider classes for the KORG NanoKONTROL2, based on the QuNeo quark 


a few classes for conveniently dealing with the korg nanokontrol

in supercollider do eg
a = KORGMapper.new;
a.setup;

a.knobs; //returns array of 8 KORGControls
a.knobs[0].ctrl; 
//returns a slightly modified MidiCcBus, 
//ie makes a control bus on the server and has 
//the convenience methods .ar, .kr and .map
//a.knobs[0].ctrl.mappingFunc_({myFunc}) for arbitrary mappings 
//(default is [0,127]->[0,1])

a.sliders; //does the same

a.buttons; //array of buttons organised in groups of 3
//provide your own functions func0 and func127
