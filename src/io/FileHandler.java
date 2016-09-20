/***
 * File IO handle class, Write and read
 * 
 * @author Junwei Zhu
 * @version 19/09/2016
 * 
 */
package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FileHandler
{

    public static final String CUSTOMER_DATA = "Customer.txt";
    
    public static final String GAME_DATA = "Game.txt";
    public static final String MUSIC_DATA = "Music.txt";
    public static final String TV_DATA = "TV.txt";
    public static final String MOVIE_DATA = "Movie.txt";
    
    public static final String FILE_BORROWERLIST = "borrowers.txt";
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
     * write borrowers' information to the file
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
