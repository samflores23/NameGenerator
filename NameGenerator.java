import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
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
    
    // Strings
    static String menu = "\nChoose from the following:\n1. Generate random names\n2. Exit\n\n";
    static String wordOptions = "Enter the number of words per name (enter\'m\' " +
        "to go back to main menu):\n\n";
    static String query = "\nGenerate more? [y]es, [n]o, [m]enu:\n\n";
    
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
        System.out.println("Welome to the random name generator!");
        
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
                    System.out.println("An error occured, invalid input!");
                    keys.nextLine();
                }
                
            } while( menuOpt != 1 && menuOpt != 2 );
            
            // Determine user selection
            switch ( menuOpt ) {
                case 1:
                    System.out.print( wordOptions + "> " );
                    if( keys.hasNext( "m" ) )
                    {
                        keys.next();
                        break;
                    }
                    
                    try {
                        numOfWords = keys.nextInt();
                    }
                    catch( Exception e ) {
                        System.out.println("An error occured, invalid input!");
                        keys.nextLine();
                        break;
                    }
                    
                    done = arrange( numOfWords );
                    break;
                case 2:
                    done = true;
                    break;
            }
            
        } while( !done );
        
        System.out.println( "Goodbye! :)" );
    }
    
    private static boolean arrange( int numWordsOut )
    {
        //Initialize random number generator
        randomNums = new Random();
        // Populate the string array list
        theWords = new ArrayList<String> ();
        File file = new File( "words.txt" );
        // For user input
        String menuOption2 = null;
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
        
        // Main arrange loop        
        while( true )
        {
            int r;
            int sz = theWords.size();
            // Print spacing
            System.out.print( "     " );
            // Do the magic
            for( int i = 0; i < numWordsOut; i++ )
            {
                r = randomNums.nextInt( sz*2 );
                // Print words
                System.out.print( theWords.get( r % sz ) + " " );
            }
            // New line for output
            System.out.println();
            
            // Query user
            do {
                System.out.print( query );
                System.out.print( "> " );
                
                menuOption2 = keys.next();
                
                if( menuOption2.equalsIgnoreCase( "no" ) || menuOption2.equalsIgnoreCase( "n" ) )
                {
                    // done, exit
                    return true;
                }
                
                if( menuOption2.equalsIgnoreCase( "yes" ) || menuOption2.equalsIgnoreCase( "y" ) )
                {
                    break;
                }
                
                if( menuOption2.equalsIgnoreCase( "menu" ) || menuOption2.equalsIgnoreCase( "m" ) )
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
