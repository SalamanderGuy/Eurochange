
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author EFTHIMEROS
 */
public class Transaction implements Comparable   // this class implements Comparable because we use Comparator for sorting objects
{
   private int id;
   private String date_time;
   private String currency;
   private float amount ;
   private double rate;
   private String type;
   private String Bank_id;
   private String Bank_name;
    
   //Constructors
   public Transaction(int id ,String currency,float amount,String Bank_id,String Bank_name,String type,double rate)
   {
        // GET CURRENT DATE_TIME AND STORE IT IN this.date_time
       SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
       Date date = new Date(System.currentTimeMillis());  
       this.date_time =  formatter.format(date);  
       this.id=id;
       this.currency = currency;
       this.amount = amount;
       this.rate = rate;
       this.Bank_id = Bank_id;
       this.Bank_name = Bank_name;
       this.type = type;
       
   }
   //-----GET METHODS------
   public int  getID()
   {
       return this.id;
   }
   
   public float getAmount()
   {
       return this.amount;
   }    
   
   public String getDateTime()
   {
       return this.date_time;
   }
   
   public String getType()
   {
       return this.type;
   }
   
   public String getCurrency()
   {
       return this.currency; 
   }
   
   public String getBankID()
   {
       return this.Bank_id; 
   }
   
   public String getBankName()
   {
       return this.Bank_name; 
   }
   
   public double getRate()
   {
       return this.rate;
   }        
   
   //-----SET METHODS------
   
   public void setID(int id)
   {
       this.id = id;
   }
   
   public void setDateTime(String date_time)
   {
       this.date_time = date_time;
   }
   
   public void setAmount(float amount)
   {
       this.amount = amount;
   }
   public void setType(String type)
   {
       this.type = type;    
   }
   public void setBankID(String Bank_id)
   {
       this.Bank_id = Bank_id;    
   }
   
   public void setBankName(String Bank_name)
   {
       this.Bank_name = Bank_name;      
   }
   
   public void setCurrency(String currency)
   {
       this.currency = currency;
   }
   
   public void setRate(double rate)
   {
       this.rate = rate;
   }
   
  
   public String print() //function for printing variables (used for receive)
   {
       String s;
       s=  "Transaction Id    : "+this.id;
       s=s+"\nDate/Time         : "+this.date_time;
       s=s+"\nTransaction type  : "+this.type;
       s=s+"\nCurrency          : "+this.currency;
       s=s+"\nAmount            : "+this.amount;
       s=s+"\nRate              : "+this.rate;
       s=s+"\nClient/Bank ID    : "+this.Bank_id;
       s=s+"\nClient/Bank Name  : "+this.Bank_name;
       return s;
   }

    @Override  //This is a comperator . Used for sorting objects by a specific variable
    public int compareTo(Object t) {
       // return this.getBankID().compareTo(((Transaction) t).getBankID());
            return  (this.getAmount() < ((Transaction) t).getAmount() ? -1 : (this.getAmount() == ((Transaction) t).getAmount() ? 0 : 1));  
    }
    
}
