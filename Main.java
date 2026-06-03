//Naomi Rodriguez
//06/01/2026
//AI Quiz Game
//This program will allow for the user to play a quiz on AI

//Changed: Added a score counter after evrery question answer to keep the 
//player up to date. And also added a custom comment depending on the player's 
//score

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /*Set the variables for the quiz, easy to change when adding or removing
      questions or answers
    */
    public static final int NUMBER_OF_QUESTIONS = 10;
    public static final int NUMBER_OF_CHOICES = 4;

    //Data structures for data of the questions and answers
    //Each question is stored in a question array
    //Easy set of answers is storedd in the dual dimensionall array
    public static void main(String[] args) {
        String[] questions = new String[NUMBER_OF_QUESTIONS];
        String[][] answers = new String[NUMBER_OF_QUESTIONS][NUMBER_OF_CHOICES];
        int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

        //Retrieve the questions and answers
        readQuizFile(questions, answers, correctAnswers);

        Scanner input = new Scanner(System.in);
        int score = 0;
        
        //Mainly what the user sees and interacts with it.
        //Holds the greetings
        System.out.println("Welcome to the AI Quiz Game!");
        System.out.println("Choose the correct answer by entering 1, 2, 3, or 4.\n");
        
        //Outputs the quesiions
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            for (int j = 0; j < answers[i].length; j++) {
                System.out.println((j + 1) + ". " + answers[i][j]);
            }
            
            //allows for user input
            System.out.print("Your answer: ");
            int userAnswer = input.nextInt() - 1;
            
            //Processes the answer given by the user.
            //Right or wrong.
            if (userAnswer == correctAnswers[i]) {
                System.out.println("Correct!");
                score++;
                //CHANGED: Added a score counter cstom
                System.out.println("Your current score is: " + score + ", Nice! \n");
            } else {
                System.out.println("Incorrect.");
                System.out.println("The correct answer was: " + answers[i][correctAnswers[i]]);
                //CHANGED: Added a score counter custom
                System.out.println("Your current score is: " + score + ", Focus on this next one!\n");
            }
        }

        System.out.println("Quiz complete!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);
        
        //CHANGED: Added a personalized comment depending on the final score
        if (score == 10) {
            System.out.println("Wow a perfect score! Incredible!");
        } else if (score >= 5) {
            System.out.println("Hey not so bad! You're quite knowledgable.");
        } else {
            System.out.println("wow you should try retaking this...");
        }

        input.close();
    }

    //Grabs the necessary information from the csv file and keeps it 
    //organized for use.
    public static void readQuizFile(String[] questions, String[][] answers, int[] correctAnswers) {
        try {
            File file = new File("ai_quiz_questions.csv");
            Scanner fileReader = new Scanner(file);

            fileReader.nextLine();

            int index = 0;

            while (fileReader.hasNextLine() && index < questions.length) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                questions[index] = data[0];

                for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
                    answers[index][i] = data[i + 1];
                }

                correctAnswers[index] = 0;
                index++;
            }

            fileReader.close();

        //In case of error
        } catch (FileNotFoundException e) {
            System.out.println("The quiz file could not be found.");
        }
    }
}