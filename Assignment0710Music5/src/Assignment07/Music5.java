package Assignment07;

enum Note {
	MIDDLE_C,C_SHARP,B_FLAT;
}

interface Playable {
	void play(Note n);
}

interface Instrument {
	// Compile-time constant:
	int VALUE = 5; // static & final
	void adjust();
}

class Wind implements Instrument, Playable {
	@Override
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	@Override
	public void adjust() {
		System.out.println("Adjusting " + this);
	}
	@Override
	public String toString() {
		return "Wind";
	}
}

class Percussion implements Instrument, Playable {
	@Override
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	@Override
	public void adjust() {
		System.out.println("Adjusting " + this);
	}
	@Override
	public String toString() {
		return "Percussion";
	}
}

class Stringed implements Instrument, Playable {
	@Override
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	@Override
	public void adjust() {
		System.out.println("Adjusting " + this);
	}
	@Override
	public String toString() {
		return "Stringed";
	}
}

class Brass extends Wind {
	@Override
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	@Override
	public void adjust() {
		System.out.println("Adjusting " + this);
	}
	@Override
	public String toString() {
		return "Brass";
	}
}

class Woodwind extends Wind {
	@Override
	public void play(Note n) {
		System.out.println(this + ".play() " + n);
	}
	@Override
	public void adjust() {
		System.out.println("Adjusting " + this);
	}
	@Override
	public String toString() {
		return "Woodwind";
	}
}

public class Music5 {
	
	static void tune(Playable i) {
		i.play(Note.MIDDLE_C);
	}
	
	static void tuneAll(Playable[] e) {
		for (Playable i: e) {
			tune(i);
		}
	}
	
	public static void main(String[] args) {
		Playable[] orchestra = {
			new Wind(),
			new Percussion(),
			new Stringed(),
			new Brass(),
			new Woodwind()
		};
		tuneAll(orchestra);
	}
}
