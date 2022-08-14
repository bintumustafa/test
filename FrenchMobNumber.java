public class FrenchMobNumber{
	public static void main(String[] args) {
		while (args.length != 1){
			System.out.println ("Veuillez renseigner le numéro à vérifier");
			System.exit(0);
		}
		String number = args [0];
		FrenchMobNumber fbm = new FrenchMobNumber();
		boolean french = fbm.isFrenchMobNumber(number);
		System.out.println (String.valueOf(french));

	}


	boolean numericChecking (String n){
		boolean numeric = false;
		if (n.length() == 8){
			try {
				Float f = Float.parseFloat(n);
				numeric = true;
			} catch (NumberFormatException e) {
				numeric = false;
			}
		}
		return numeric;

	}

	boolean isFrenchMobNumber (String number) {
		boolean french = false;
		try {
		if (number.substring(0,2).equals("00")) {
			if (number.substring(2,4).equals("33")){
				if (number.substring(4,5).equals("6") || number.substring(4,5).equals("7")){
					String n = number.substring(5);
					french = numericChecking(n);									
				}
			}
		}
		else if (number.substring(0,1).equals("+")) {
			if (number.substring(1,3).equals("33")){
				if (number.substring(3,4).equals("6") || number.substring(3,4).equals("7")){
					String n = number.substring(4);
					french = numericChecking(n);
				} 
			}
		}
		} catch(java.lang.StringIndexOutOfBoundsException e){}
		return french;
	}
}