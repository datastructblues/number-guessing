import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class GuessNumber {
    static Integer point=0;
    static Integer count = 0;
    public static void main(String[] args) {
        String name =getPlayerName();
        startGame();
        writeFile(name,point);


    }
    public static String compareGuess(int userAnswer, int computerAnswer, int ct) {
        if (userAnswer == computerAnswer) {
            return "Correct!\n Attempt number: " + ct + "\n Your point: " + calculatePoint(count);
        } else if (userAnswer <= 0 || userAnswer > 100) {
            return "Invalid range";
        } else if (userAnswer > computerAnswer) {
            return "Too high, try again \n Attempt number: " + ct;
        } else if (userAnswer < computerAnswer) {
            return "Too low, try again:\n Attempt number: " + ct;
        } else {
            return "Incorrect entry.Attempt number: " + ct;
        }
    }

    public static void startGame() {
      int computerNumber = (int) (Math.random() * 100 + 1);
        int userAnswer = 0;
        while (userAnswer!=computerNumber) {
            if(++count>10) {
                JOptionPane.showMessageDialog(null, "Game over... \n Correct number is: "+computerNumber +" \n Your point:"+ 0);
                break;
            }
            String response = JOptionPane.showInputDialog(null,
                    "Enter a number between 1 and 100");

            try {
                userAnswer = Integer.parseInt(response);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Number expected.");
            }
            JOptionPane.showMessageDialog(null, "" + compareGuess(userAnswer, computerNumber, count));
        }
        point = calculatePoint(count);
    }
    public static Integer calculatePoint(int count){
        return ((10-count)*100)+100;
    }

    public static String getPlayerName(){
        String player_name=JOptionPane.showInputDialog(null,"Enter player name: ");
        return player_name;
    }

    public static void writeFile(String name, Integer point){
        FileOutputStream file = null;
            try {
                file=new FileOutputStream("highscores.dat",true);
                String score=(name+":"+ point + " point ");
                byte[] array=score.getBytes(StandardCharsets.UTF_8);
                try{
                    String lineSeparator = System.getProperty("line.separator");
                    file.write(array);
                    file.write(lineSeparator.getBytes());
                    file.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            } catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }finally {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
