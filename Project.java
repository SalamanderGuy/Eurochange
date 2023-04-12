
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Eurochange[] obj = new Eurochange[10] ;  //array with objects
        //filenames..
        String csvFile1 = "BoG_rates_of_exchange.csv" ;  
        String csvFile2 = "commission_fees.csv" ;
        String csvFile3 = "opening_till_balance.csv" ;
        
        BufferedReader br1 = null ;
        BufferedReader br2 = null ;
        BufferedReader br3 = null ;
        String cvsSplitBy = ";" ; //seperator
        
        //variables useful for filereading
        String line1 = "" ;
        String line2 = "" ;
        String line3 = "" ;
        
        int i=0 ;
        try 
        {
            // opening files for reading 
            br1 = new BufferedReader(new FileReader(csvFile1));
            br2 = new BufferedReader(new FileReader(csvFile2));
            br3 = new BufferedReader(new FileReader(csvFile3)); //Parallel opening the files 
            //
                    line3=br3.readLine();
                    String[] array3 = line3.split(cvsSplitBy);
                    obj[i]=new Eurochange(array3[0],Float.parseFloat(array3[1]));
                    i++;
            //Reading Files  
            while ( ((line1 = br1.readLine()) != null) && ((line2 = br2.readLine()) != null) && ((line3 = br3.readLine()) != null) ) //file reading 
            {
                String[] array1 = line1.split(cvsSplitBy);
                String[] array2 = line2.split(cvsSplitBy);
                array3 = line3.split(cvsSplitBy);
                //store values in objects
                obj[i]=new Eurochange(array1[0],Float.parseFloat(array1[1]),Float.parseFloat(array1[2]),Float.parseFloat(array1[3]),Float.parseFloat(array2[1]),Float.parseFloat(array2[2]),Float.parseFloat(array3[1]));
                i++;
                if(i==11){line2 = br2.readLine();}
            }

        }
        catch (FileNotFoundException e) //Exception if file not found
        {
            e.printStackTrace();
        } 
        catch (IOException e) //Exception for file problem
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br1 != null && br2!=null && br3!=null )  // Close files
            {
                try 
                {
                    br1.close();
                    br2.close();
                    br3.close();
                    System.out.println("--Όλα τα αρχεία ενημερώθηκαν επιτυχώς--\n\n" );
                } 
                catch (IOException e) //Exception for problem at file closing
                {
                    e.printStackTrace();
                }
            }
        }
        int input = 666;
            ArrayList<Transaction> list = new ArrayList<>();
            
        System.out.println("-- ΚΑΛΩΣΗΡΘΑΤΕ! --\n\n" );
        double tmp = 0;
        int trans_id=0;
        while(input!=0) //MENU
        {   System.out.println("\n----------------------ΜΕΝΟΥ ΕΠΙΛΟΓΩΝ--------------------------------" );
            System.out.println("[1] -- Εκτύπωση τιμών αγοράς/πώλησης συναλλάγματος ανταλλακτηρίου --" );
            System.out.println("[2] -- Υπολογισμός αξίας συναλλάγματος --" );
            System.out.println("[3] -- Συναλλαγή για ιδιώτες --" );
            System.out.println("[4] -- Συναλλαγή για Τράπεζες  --" );
            System.out.println("[5] -- Εκτύπωση των συναλλαγών με την σειρά που πραγματοποιήθηκαν --" );
            System.out.println("[6] -- Εκτύπωση των συναλλαγών ταξινομημένων κατά αξία            --" );
            System.out.println("[7] -- Εκτύπωση του όγκου των συναλλαγών --" );
            System.out.println("[0] -- Έξοδος από το μενού επιλογών --" );
            System.out.println("\n--------------------------------------------------------------------" );
            System.out.print("Δώσε αριθμό επιλογής :");
            Scanner s = new Scanner(System.in); 
            input = s.nextInt(); 
            switch (input) 
            {
                case 0:
                    break;    
                case 1:
                    for(i=1;i<obj.length;i++)
                        obj[i].PrintBuySellWithComm();
                    break;
                case 2:
                    System.out.println("Πληκτρολόγησε το ποσό προς υπολογισμό :");
                    Scanner v = new Scanner(System.in); 
                    int value = v.nextInt(); 
                    System.out.println("Πληκτρολόγησε το ξένο νόμισμά (USD,GBP,DKK,SEK,JPY,CHF,NOK,CAD,AUD) : ");
                    Scanner scanner = new Scanner(System. in);
                    String inputString = scanner. nextLine();
                    //CHECK IF INPUT IS CORRECT
                    if(inputString.equals("USD") || inputString.equals("GBP") || inputString.equals("DKK") || inputString.equals("SEK") || inputString.equals("JPY") || inputString.equals("CHF") || inputString.equals("NOK") || inputString.equals("CAD") || inputString.equals("AUD") )
                    {
                        for(i=1;i<obj.length;i++)
                        {   
                            if(obj[i].getCountry().equals(inputString))
                            {
                                System.out.println("Αξία αγοράς  συναλλάγματος σε ευρώ :" + obj[i].BuyAmountInEuros(value));
                                System.out.println("Αξία πώλησης συναλλάγματος σε ευρώ :" + obj[i].SellAmountInEuros(value));
                            }
                        }
                    }
                    else
                       System.out.println(" Το νόμισμα που πληκτρολογήσατε δεν είναι έγκυρο! "); 
                    break;
                case 3:
                    System.out.println("--Αν θες να πραγματοποιήσεις αγορά πλητρολόγησε  '1'--");
                    System.out.println("--Αν θες να πραγματοποιήσεις πώληση πλητρολόγησε '2'--");
                    Scanner c = new Scanner(System.in); 
                    int in = c.nextInt();
                    String type;    // είδος συναλλαγής
                    float amount=0; // ποσό
                    String coin=""; //νόμισμα
                    String name=""; //όνομα(πελάτη ή τραπεζας)
                    String id="";  
                    if(in==1)
                    {   type = "buying" ;
                        System.out.println("--Πληκτρολόγησε το ποσό που θες να μετατρέψεις σε ξένο νόμισμα :");
                        int k = c.nextInt();
                        System.out.println("--Πληκτρολόγησε το ξένο νόμισμα που θες να παραλάβεις (USD,GBP,DKK,SEK,JPY,CHF,NOK,CAD,AUD) : ");
                        Scanner sc = new Scanner(System. in);
                        coin = sc. nextLine();
                        System.out.println("--Πληκτρολόγησε Όνομα ");
                        name = sc. nextLine();
                        System.out.println("--Πληκτρολόγησε αριθμό ταυτότητας ");
                        id = sc.nextLine();
                        for(i=1;i<obj.length;i++)
                        {   
                            if(obj[i].getCountry().equals(coin))
                            {    
                                 obj[0].setBalance(k + obj[0].getBalance()); //ενημερώνουμε το ποσο των ευρώ (υπαρχον ποσο + currentbalance)
                                 amount = obj[i].BuyAmountInEuros(k); // υπολογισμός ποσού αγορας
                                 obj[i].setBalance(obj[i].getBalance()- amount);
                                 tmp= obj[i].getBuy_price();
                                 
                            }
                        }
                        if(k>15000) // εάν το ποσό είναι μεγαλύτερο από 15000 κρατάμε επιπλέον πληροφορίες
                        {
                            System.out.println("--Πληκτρολόγησε αριθμό ΑΦΜ ");
                            String afm = sc.nextLine();
                            System.out.println("--Πληκτρολόγησε διεύθυνση κατοικίας ");
                            String address = sc.nextLine();
                        }
                        
                    }
                    else
                    {
                        type = "selling" ;
                        System.out.println("--Πληκτρολόγησε το ποσό σε ευρώ που θες να πουλήσεις :");
                        int k = c.nextInt();
                        System.out.println("--Πληκτρολόγησε το ξένο νόμισμα που θες να παραλάβεις (USD,GBP,DKK,SEK,JPY,CHF,NOK,CAD,AUD) : ");
                        Scanner sc = new Scanner(System. in);
                        coin = sc. nextLine();
                        System.out.println("--Πληκτρολόγησε Όνομα ");
                        name = sc. nextLine();
                        System.out.println("--Πληκτρολόγησε αριθμό ταυτότητας ");
                        id = sc.nextLine();
                        for(i=1;i<obj.length;i++)
                        {   
                            if(obj[i].getCountry().equals(coin))
                            {
                                 obj[i].setBalance(obj[i].getBalance() - k);
                                 amount = obj[i].SellAmountInEuros(k); // υπολογισμός ποσού 
                                 obj[0].setBalance(obj[0].getBalance() + amount);//ενημέρωση ποσου ευρώ
                                 tmp= obj[i].getBuy_price();//System.out.println("Αξία πώλησης συναλλάγματος σε ευρώ :" + obj[i].SellAmountInEuros(value));
                            }
                        }
                        if(k>15000) // εάν το ποσό είναι μεγαλύτερο από 15000 κρατάμε επιπλέον πληροφορίες
                        {
                            System.out.println("--Πληκτρολόγησε αριθμό ΑΦΜ ");
                            String afm = sc.nextLine();
                            System.out.println("--Πληκτρολόγησε διεύθυνση κατοικίας ");
                            String address = sc.nextLine();
                        }
                    }
                    
                    Transaction tran = new Transaction(trans_id++,coin,amount,id,name,type,tmp);
                    list.add(tran);
                    break;
                case 4:
                    System.out.println("--Αν θες να πραγματοποιήσεις αγορά πλητρολόγησε  '1'--");
                    System.out.println("--Αν θες να πραγματοποιήσεις πώληση πλητρολόγησε '2'--");
                    Scanner c1 = new Scanner(System.in); 
                    in = c1.nextInt();
                    String type1;    // είδος συναλλαγής
                    float amount1=0; // ποσό
                    String coin1; //νόμισμα
                    String name1; //όνομα(πελάτη ή τραπεζας)
                    String id1;  
                    
                    if(in==1)
                    {   type1 = "buying" ;
                        System.out.println("--Πληκτρολόγησε το ποσό που θες να μετατρέψεις σε ξένο νόμισμα :");
                        int k1 = c1.nextInt();
                        System.out.println("--Πληκτρολόγησε το ξένο νόμισμα που θες να παραλάβεις (USD,GBP,DKK,SEK,JPY,CHF,NOK,CAD,AUD) : ");
                        Scanner sc1 = new Scanner(System. in);
                        coin1 = sc1. nextLine();
                        System.out.println("--Πληκτρολόγησε Όνομα Tράπεζας ");
                        name1 = sc1. nextLine();
                        System.out.println("--Πληκτρολόγησε αριθμό ταυτότητας ");
                        id1 = sc1.nextLine();
                        for(i=1;i<obj.length;i++)
                        {   
                            if(obj[i].getCountry().equals(coin1))
                            {
                                 obj[0].setBalance(k1 + obj[0].getBalance()); //ενημερώνουμε το ποσο των ευρώ (υπαρχον ποσο + currentbalance)
                                 amount1 = obj[i].BuyAmountBank(k1); // υπολογισμός ποσού αγορας
                                 obj[i].setBalance(obj[i].getBalance()- amount1);
                                 tmp= obj[i].getBuy_price();
                            }
                        }
                        
                        
                    }
                    else
                    {
                        type1 = "selling" ;
                        System.out.println("--Πληκτρολόγησε το ποσό σε ευρώ που θες να πουλήσεις :");
                        int k1 = c1.nextInt();
                        System.out.println("--Πληκτρολόγησε το ξένο νόμισμα που θες να παραλάβεις (USD,GBP,DKK,SEK,JPY,CHF,NOK,CAD,AUD) : ");
                        Scanner sc1 = new Scanner(System. in);
                        coin1 = sc1. nextLine();
                        System.out.println("--Πληκτρολόγησε Όνομα ");
                        name1 = sc1. nextLine();
                        System.out.println("--Πληκτρολόγησε αριθμό ταυτότητας ");
                        id1 = sc1.nextLine();
                        for(i=1;i<obj.length;i++)
                        {   
                            if(obj[i].getCountry().equals(coin1))
                            {
                                 obj[i].setBalance(obj[i].getBalance() - k1);
                                 amount1 = obj[i].SellAmountBank(k1); // υπολογισμός ποσού 
                                 obj[0].setBalance(obj[0].getBalance() + amount1);//ενημέρωση ποσου ευρώ
                                 tmp= obj[i].getBuy_price();//System.out.println("Αξία πώλησης συναλλάγματος σε ευρώ :" + obj[i].SellAmountInEuros(value));
                            }
                        }
                        
                    }
                    
                    Transaction tran2 = new Transaction(trans_id++,coin1,amount1,id1,name1,type1,tmp);
                    list.add(tran2);
                    
                    break;
                case 5:
                    if(list.isEmpty()) //check if there arent any transactions ..
                    {
                        System.out.println("Δεν υπάρχουν συναλλαγές για προβολή !");
                    }
                    else
                    {
                        //Print with order
                        for(int j=0;j<list.size();j++)
                        {
                            for(int z=0;z<list.size();z++)
                        {
                            if(j == list.get(z).getID() )
                            {
                                System.out.println(list.get(z).print());
                                System.out.println("\n-------------------------------------------------------\n\n");
                            }
                            
                        }
                        }
                    }
                    break;
                case 6:
                    if(list.isEmpty()) //check if there arent any transactions ..
                    {
                        System.out.println("Δεν υπάρχουν συναλλαγές για προβολή !");
                    }
                    else
                    {
                        //we call comparator to sort the list of objects by amount
                        Comparator comparator = Collections.reverseOrder(); //used for sorting objects by value
                        Collections.sort(list,comparator);	//function for sorting
                        for(int j=0;j<list.size();j++) //print sorted list
                            {
                                System.out.println(list.get(j).print()); //function that prints values
                                System.out.println("\n-------------------------------------------------------\n\n"); 
                            }
                    }       
                    break;
                case 7://print all transaction without any order..
                    if(list.isEmpty()) //check if there arent any transactions ..
                    {
                        System.out.println("Δεν υπάρχουν συναλλαγές για προβολή !");
                    }
                    else
                    {
                        
                        for(int j=0;j<list.size();j++) //we print every transaction
                        {
                            System.out.println(list.get(j).print());
                            System.out.println("\n-------------------------------------------------------\n\n");
                        }
                    }
                    
            }
    ///-----------------------------------------------------------------------------------------------------            
            
            Scanner pauser = new Scanner (System.in); //Pause programm 
            System.out.println("-- Πάτα ENTER για να συνεχίσεις! --");         
            pauser.nextLine();            
        }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");//current date(used for filename)
            LocalDate localDate = LocalDate.now();
       
        String csvFilename = "transactions_"+dtf.format(localDate)+".csv";
        String bal = "closing_till_balance"+dtf.format(localDate)+".csv";
        int n=0;
        
        try //		create/open files
        {
            FileWriter w = new FileWriter(bal);
            FileWriter csvwriter = new FileWriter(csvFilename);
            //-------------------------------
            //print transactions with priority
            for(int j=0;j<list.size();j++)
            {
                for(int z=0;z<list.size();z++)
                        {
                            if(j == list.get(z).getID() )
                            {
                                csvwriter.append(list.get(z).print());
                                csvwriter.append("\n-------------------------------------------------------\n\n");
                            }
                        }
            }
            csvwriter.close();
            //-------------------------------------
            
            //print closing_till_balance.csv file
            for(int j=0;j<obj.length;j++)
            {
                w.append(obj[j].UpdateBalance());
                w.append("\n");
            }
            w.close();
            //-----------------------------------------------------------------------------------------
            for( n=0;n<list.size();n++)//PRINT RECEIVES IN FILES
            {   FileWriter writer = new FileWriter("receivenum_"+list.get(n).getID()+".csv");//each receive has unique file name
                writer.append("-------------This is a Receive-------------------------\n");
                writer.append(list.get(n).print());//CALL function at transaction class for print 
                writer.append("\n-------------THANKS FOR CHOOSING US--------------------");
                writer.close();
            }
            
        }
        catch (Exception e)  	//Handle file errors
        {
            System.out.println("exception :" + e.getMessage());
        }
        System.out.println("---  Ευχαριστούμε που μας επιλέξατε!  ---\n");
       
    }
    
}