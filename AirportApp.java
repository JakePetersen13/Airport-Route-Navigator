//---------------------------------------------------------------------------------------------------------------------------------
// Name: Petersen, Jake
// Project: 5
// Due: 12/8/2023
// Course: cs-2400-02-f23
//
// Description:
//      Implementaion of an Airport App that holds descriptiosn of airports and can determine cheapest distance between them in a graph.
//--------------------------------------------------------------------------------------------------------------------------------- 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AirportApp
{
    static Scanner scnr = new Scanner(System.in);
    static DictionaryInterface<String,String> airportsDictionary = new HashedDictionary<>();
    static GraphInterface<String> airportsGraph = new DirectedGraph<String>();
    
    public static void main(String args[])
    {
        try
        {
            Scanner file = new Scanner(new File("airports.csv"));
            boolean exit = false;
            String input = null;

            // adding airports to dictionary & vertexes
            file.useDelimiter(",");
            while (file.hasNextLine())
            {
                String[] inputs = file.nextLine().split(",", 2);
                airportsDictionary.add(inputs[0], inputs[1]);
                airportsGraph.addVertex(inputs[0]);
            }

            //adding edges to graph

            file = new Scanner(new File("distances.csv"));
            while (file.hasNextLine())
            {
                String[] inputs = file.nextLine().split(",", 10);

                airportsGraph.addEdge(inputs[0], inputs[1], Double.parseDouble(inputs[2]));
            }

            

            while (!exit) //while loop for menu
            {
                System.out.print("Command? ");
                input = scnr.next();

                switch (input.toLowerCase()) //processes commands from input
                { 
                    case "q": //information command
                        System.out.print("Airport Code? ");
                        boolean done = false;
                        String info = airportsDictionary.getValue(scnr.next().toUpperCase());
                        while (!done)
                        {
                            if (info == null)
                            {
                                System.out.println("Airport code unknown");
                                info = airportsDictionary.getValue(scnr.next().toUpperCase());
                            }
                            else
                                done = true;
                        }
                        System.out.println(info);
                        break;
                    case "d": //distance command
                        StackInterface<String> stack = new LinkedStack<>();
                        System.out.print("Airport codes from to? ");
                        String airport1 = scnr.next().toUpperCase();
                        String airport2 = scnr.next().toUpperCase();
                        String info1 = airportsDictionary.getValue(airport1);
                        String info2 = airportsDictionary.getValue(airport2 );

                        if (info1 == null || info2 == null)
                        {
                            System.out.println("Airport code unknown");
                        }
                        else
                        {
                            double distance = airportsGraph.getCheapestPath(airport1, airport2, stack );

                            if (distance != -1.0 && distance != 0.0)
                            {
                                System.out.println("The minimum distance between " + info1 + " and " + info2 + " is " + distance +  " through the route:"); 
                                while (!stack.isEmpty())
                                {
                                    System.out.println(airportsDictionary.getValue(stack.pop()));
                                }
                            }
                            else
                            {
                                System.out.println("Airports not connected");
                            }
                        }
                    
                        break;
                    case "h": //print screen
                        printMenu();
                        break;
                    case "e": //exit command
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                } //end switch
            } //end while
        }
        catch(FileNotFoundException e)
        {
            throw new RuntimeException("file not found");
        }

        
    }

    private static void printMenu()
    {
        System.out.println("Q Query the airport information by entering the airport code.");
        System.out.println("D Find the minimum distance between two airports.");
        System.out.println("H Display this message.");
        System.out.println("E Exit.");

    }
}