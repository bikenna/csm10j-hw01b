/**
 *
 * @author brianobioha
 */
import java.util.Vector;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Hw01b {
    public static void getStockStats(String stock, Vector<String> values){
        Vector<Double> nums = new Vector<Double>();
        DecimalFormat round = new DecimalFormat("0.00");
        double sum = 0;
        int counter = 0;
            
            for(int i = 0; i < values.size(); i++){
                if(values.get(i).toLowerCase().equals(stock.toLowerCase())){
                    Double num = Double.parseDouble(values.get(i + 1));
                    nums.add(num);
                    counter++;
                    sum += num;
                }
            }
        try{   
            double largest = nums.get(0);
            double smallest = nums.get(0);
        
            for(double i : nums){
                if(i > largest)
                    largest = i;
                else if(i < smallest)
                    smallest = i;
            }

            double mean = sum/(double)counter;

            System.out.println(stock + " Min: " + smallest + " Max: " + largest + " Avg:" + round.format(mean) + "\n");
        }catch(ArrayIndexOutOfBoundsException e){
                System.out.println(stock + " was not found" + "\n");
         }
    }
    public static void GetStockHigh(Vector<String> values){
        Vector<Double> numValues = new Vector<Double>();
        for(String i : values){
            if(isNumeric(i)){
                Double val = Double.parseDouble(i);
                numValues.add(val);
            }      
        }
        double largest = numValues.firstElement();
        String ticker = "";
        for(double i : numValues){
            if(i > largest)
                largest = i;
        }
        for(int i = 0; i < values.size(); i++){
            if(isNumeric(values.get(i))){
                Double val = Double.parseDouble(values.get(i));
                if(val == largest)
                   ticker = values.get(i - 1);
            }
        }
        System.out.println(ticker + " has highest price of " + largest + "\n");
    }
    public static void GetStockLow(Vector<String> values){
        Vector<Double> numValues = new Vector<Double>();
        for(String i : values){
            if(isNumeric(i)){
                Double val = Double.parseDouble(i);
                numValues.add(val);
            }      
        }
        double smallest = numValues.firstElement();
        String ticker = "";
        for(double i : numValues){
            if(i < smallest)
                smallest = i;
        }
        for(int i = 0; i < values.size(); i++){
            if(isNumeric(values.get(i))){
                Double val = Double.parseDouble(values.get(i));
                if(val == smallest)
                   ticker = values.get(i - 1);
            }
        }
        System.out.println(ticker + " has lowest price of " + smallest + "\n");
    }
    public static boolean isNumeric(String str)  
    {  
        try  
        {  
          double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException e)  
        {  
          return false;  
        }  
        return true;  
    }
    public static void main(String [] args){
        try{
                FileReader filename = new FileReader("stocks.txt");
                Scanner inFile = new Scanner(filename);
                Vector<String> arry = new Vector<String>();
                while(inFile.hasNext()){
                    arry.addElement(inFile.next());
                }
            while(true){ 
                System.out.println("Enter '1' to get max, min and avg of stock");
                System.out.println("Enter '2' to get stock ticker with highest price");
                System.out.println("Enter '3' to get stock ticker with lowest price");
                System.out.println("Enter 'c' to change stock filename");
                System.out.println("Enter 'q' to quit\n");
                System.out.print("Your choice: ");

                Scanner input = new Scanner(System.in);
                String choice = input.next().toLowerCase();

                    switch(choice){
                        case "1":
                            System.out.print("Enter stock ticker: ");
                            String stock = input.next();  
                            getStockStats(stock, arry);
                            break;

                        case "2":
                            GetStockHigh(arry);
                            break;

                        case "3":
                            GetStockLow(arry);
                            break;

                        case "c":
                            String newFile;
                            Scanner fileName = new Scanner(System.in);
                            System.out.print("Enter a stock filename: ");
                            newFile = fileName.next();
                            System.out.println(); 
                            Scanner inFile2 = new Scanner(new FileReader(newFile));
                            arry.removeAllElements();
                            while(inFile2.hasNext()){
                                arry.addElement(inFile2.next());
                            }
                            break;
                            
                        case "q":
                            System.out.println("Goodbye");
                            System.exit(1);  

                        default:
                            System.out.println("Unrecognized choice" + "\n");         
                    }
            }
        }catch(FileNotFoundException e){
            System.out.print("File not found"); 
        }
    }
}
