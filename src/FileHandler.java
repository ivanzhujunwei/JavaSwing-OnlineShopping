/***
 * File IO handle class, Write and read
 * 
 * @author Junwei Zhu
 * @version 19/09/2016
 * 
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *This is the class responsible for IO manipulation.
 */
public class FileHandler
{

	public static final String CUSTOMER_DATA = "Customer.txt";
	public static final String ADMIN_DATA = "AdminDetails.txt";
	
    
    public static final String GAME_FILE = "Game.txt";
    public static final String MUSIC_FILE = "Music.txt";
    public static final String TV_FILE = "TV Series.txt";
    public static final String MOVIE_FILE = "Movie.txt";
    public static final String ORDER_FILE = "Order.txt";
    public static final String CART_FILE = "cart.txt";
    public static final String SPLIT_CEMI = ";";
    public static final String SPLIT_COMMA = ",";

    /**
     * *
     * read information from files
     *
     * @param filename
     * @return data stored in the files
     */
    public static String readFromFile(String filename)
    {
        String data = "";
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                data = data + parser.nextLine() + SPLIT_CEMI;
            }
            inputFile.close();
            return data;
        } catch (FileNotFoundException exception)
        {
            System.out.println("File does not exist.");
        } catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return data;
    }

    /**
     * *
     * write data to overwrite file
     *
     * @param saveData the data which needs to be saved
     * @param filePath the file which will write
     */
    public static void writeToFile(String saveData, String filePath)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filePath);
            outputFile.println(saveData);
            outputFile.close();
        } catch (IOException e)
        {
            System.out.println("Sorry, fail to save borrowers' data!");
        }
    }
    
    /**
     * *
     * append data to file
     *
     * @param saveData the data which needs to be saved
     * @param filePath the file which will write
     */
    public static void appendToFile(String saveData, String filePath){
    	try
    	{
    	    FileWriter fw = new FileWriter(filePath,true); //the true will append the new data
    	    fw.write(saveData);//appends the string to the file
    	    fw.close();
    	}
    	catch(IOException ioe)
    	{
    	    System.err.println("IOException: " + ioe.getMessage());
    	}
    }
    
    private void exitLibrary()
	{
//		String saveData = "";
//		for (Borrower borrower : borrowerList.getBorrowerList())
//		{
//			ArrayList<Book> bookList = borrower.getBookList();
//			saveData += borrower.getName() + FileHandle.SPLIT_COMMA + borrower.getId() + FileHandle.SPLIT_COMMA
//					+ borrower.getAge();
//			for (Book book : bookList)
//				saveData += FileHandle.SPLIT_COMMA + book.toString();
//			saveData += "\n";
//		}
//		FileHandle.writeToFile(saveData);
//		System.out.println("You have exited, thank you!");
//		System.exit(0);
	}
}
