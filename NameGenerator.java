import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
/**
 * Simple terminal program to generate random names from words read
 * from text file "words.txt". Text file should reside in same directory
 * and there is to be one word per line in the text file.
 * 
 * @author Samuel Flores 
 * @version 06/21/2013
 */
public class NameGenerator
{
    // Some variables
    static Scanner keys = new Scanner(System.in);
    static boolean done = false;
    static int menuOpt = 0;
    static int numOfWords = 0;
    static ArrayList<String> theWords = null;
    static Random randomNums = null;
    static HashMap<String, String> wordsUsed = null;
    
    // Strings
    static String menu = "\nChoose from the following:\n1. Generate random names\n2. Exit\n\n";
    static String wordOptions = "Enter the number of words per name (enter 'm' " +
        "to go back to main menu):\n\n";
    static String query = "\nHit Enter to generate more, 'q' to quit, 'm' for menu:\n\n";
    
    /**
     * Constructor for objects
     */
    private NameGenerator()
    {
       
    }

    /**
     * 
     * @param  None  Does not take command-line arguments at this time
     * @return None  
     */
    public static void main(String[] args)
    {
        // Print welcome
        System.out.println("Welcome to the random name generator!");
        // Main program loop
        do {
            
            // Get user menu option
            do {
                // Print Menu
                System.out.print( menu );
                System.out.print( "> " );
                
                menuOpt = 0;
                
                try {
                    menuOpt = keys.nextInt();
                }
                catch( Exception e ) {
                    System.out.println("An error occurred, invalid input!");
                    keys.nextLine();
                }
                
            } while( menuOpt != 1 && menuOpt != 2 );
            
            // Determine user selection
            switch ( menuOpt ) {
                /*-------------------- User wants to use the generator --------------------*/
                case 1:
                    System.out.print( wordOptions + "> " );
                    if( keys.hasNext( "m" ) )
                    {
                        keys.nextLine();
                        break;
                    }
                    
                    try {
                        numOfWords = keys.nextInt(); // Only gets the next token in line
                    }
                    catch( Exception e ) {
                        System.out.println("An error occurred, invalid input!");
                        keys.nextLine();
                        break;
                    }
                    // Advance to next line for input
                    keys.nextLine();
                    
                    // Set up the words array list
                    theWords = new ArrayList<String> ();
                    // Open the words file
                    File file = new File( "words.txt" );
                    try {
                        Scanner fileScanner = new Scanner( file );
                        while( fileScanner.hasNextLine() )
                        {
                            theWords.add( fileScanner.nextLine() );
                        }
                        fileScanner.close();
                    } 
                    catch( Exception e )
                    {
                        e.printStackTrace();
                    }
					// Call function to begin generating names
                    done = arrange( numOfWords );
                    break;
                /*---------------------- User wants to exit program -----------------------*/
                case 2:
                    done = true;
                    break;
            }
            
        } while( !done );
        
        System.out.println( "\nGoodbye! :)" );
    }
	
    /**
	 * This method runs the name generation part of the program
	 * @param  numWordsOut  An integer representing the number of words to be output per name
	 * @return A boolean indicating true if the user wants to quit, false if user wants to continue running
	 */
    private static boolean arrange( int numWordsOut )
    {
        //Initialize random number generator
        randomNums = new Random();
        // Create a hashmap for words used to avoid same word selection
        wordsUsed = new HashMap( numWordsOut );
        // For user input
        String userResponse = null;
        // Get size of array list
        int sz = theWords.size();
        // Main arrange loop        
        while( true )
        {
            String word = null;
            int rand;
            // Clear all mappings (reset words used)
            wordsUsed.clear();
            // Print spacing
            System.out.print( "     " );
            // Do the magic
            for( int i = 0; i < numWordsOut; i++ )
            {
                // Make sure haven't used this word. If so, get another
                do {
                    rand = randomNums.nextInt( sz - 1 );
                    word = theWords.get( rand );
                } while( wordsUsed.containsKey( word ) );
                // Add the word to the words used hashmap
                wordsUsed.put( word, "" );
                // Print words
                System.out.print( theWords.get( rand ) + " " );
            }
            // New line for output
            System.out.println();
            
            // Query user
            do {
                System.out.print( query );
                System.out.print( "> " );
                
                userResponse = keys.nextLine();
                
                if( userResponse.equalsIgnoreCase( "q" ) || userResponse.equalsIgnoreCase( "quit" ) )
                {
                    // done, exit
                    return true;
                }
                
                if( userResponse.equalsIgnoreCase( "" ) || userResponse.equalsIgnoreCase( "" ) )
                {
                    // User hit Enter key, continue execution
                    break;
                }
                
                if( userResponse.equalsIgnoreCase( "menu" ) || userResponse.equalsIgnoreCase( "m" ) )
                {
                    // done but don't exit, go to menu
                    return false;
                }
                else
                {
                    System.out.println("Invalid input!");
                }
            } while( true );
        }
    }
}
