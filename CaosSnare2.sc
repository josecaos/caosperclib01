//written by @Ill_Slide
//sharper like snare
//Part of CaosPercLib 2.0

CaosSnare2 : CaosKick {

	*new {

		^super.new;

	}

	*ar {|att=0.01,rel=0.35,iphase=0.01,bw=0.5,highcutfreq=1920,rq=0.85,gate=1,amp1=0.75,amp2=0.5|
		var sna,env;

		sna = this.signal(iphase,bw,highcutfreq,rq,amp1,amp2);
		sna=this.comp(sna,0.4,0.39,0.7);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(sna*env,[1, -0.98]);

	}

	ar {|att=0.01,rel=0.35,iphase=0.01,bw=0.5,highcutfreq=220,rq=0.85,gate=1,amp1=0.75,amp2=0.5|
		var sna,env;

		sna = this.signal(iphase,bw,highcutfreq,rq,amp1,amp2);
		sna=this.comp(sna,0.4,0.39,0.7);
		env=EnvGen.ar(Env.perc(att,rel),gate,doneAction:2);
		^Pan2.ar(sna*env,[1, -0.98]);

	}

	*robot {|att= 0.01,rel= 0.35,iphase=0.01,bw=0.5,highcutfreq=220,rq=0.5,amp1=0.75,amp2=0.5,t=1,tp=0|
		var sna,env;

		sna=Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(Mix(220,480,1125,2220,4218),iphase,bw,amp2/4)+
			GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);
		sna=CompanderD.ar(sna,0.4,0.39,0.7);
		env=EnvGen.ar(Env.perc(att,rel),Impulse.kr(t,tp),doneAction:0);
		^Pan2.ar(sna*env,[1, -0.98]);

	}

	*signal {|iphase,bw,highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(220,iphase,bw,amp2/4)+
			GrayNoise.ar(amp1/1.5),highcutfreq,rq),0.7);

	}

	signal {|iphase,bw,highcutfreq,rq,amp1,amp2|

		^Limiter.ar(RHPF.ar(PinkNoise.ar(amp1)+
			LFPulse.ar(220,iphase,bw,amp2/4)+
			GrayNoise.ar(amp1/2),highcutfreq,rq),0.7);

	}

}