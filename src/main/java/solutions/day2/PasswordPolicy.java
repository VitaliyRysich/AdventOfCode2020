package solutions.day2;

public class PasswordPolicy {

    private int lowestNumber;
    private int higestNumber;
    private char letter;
    private String password;

    public PasswordPolicy() {
    }

    public PasswordPolicy(int lowestNumberOfTimes, int higestNumberOfTimes, char letter, String password) {
        this.lowestNumber = lowestNumberOfTimes;
        this.higestNumber = higestNumberOfTimes;
        this.letter = letter;
        this.password = password;
    }

    public boolean isValidCountOfLetter(){
        int counter = 0;

        for(char ch: password.toCharArray()){
            if(ch == letter) {
                counter++;
            }
        }

        if(counter >= lowestNumber && counter <= higestNumber)
            return true;

        return false;
    }


    public boolean isValidLetterPosition(){
        boolean a = password.charAt(lowestNumber - 1) == letter;
        boolean b = password.charAt(higestNumber - 1) == letter;

        return (a && !b) || (b && !a);
    }
}
