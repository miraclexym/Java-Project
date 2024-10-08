package interfaces.interfaceprocessor;

class InterExchange implements Processor{
	@Override
	public String name() {
		return getClass().getSimpleName();
	}

	String exchangeChar(String string){
		char[] temp=string.toCharArray();
		for(int i = 0; i < string.length() && i + 1 < string.length(); i += 2){
			char temporary=temp[i];
			temp[i]=temp[i+1];
			temp[i+1]=temporary;
		}
		return new String(temp);
	}
	
	@Override
	public Object process(Object input) {
		return (exchangeChar((String)input));
	}
}
public class Apply {
	public static void process(Processor p, Object s){
		System.out.println("Using machine " + p.name() + " to process S yields result " + p.process(s));
	}
	public static String school="Nankai University";	
	public static String college="school of computing";
	public static String StudentID="2212032";
	
	public static void main(String[] args){
		process(new InterExchange(), school);
		process(new InterExchange(), college);
		process(new InterExchange(), StudentID);
	}
}
