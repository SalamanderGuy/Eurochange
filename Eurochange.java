

public class Eurochange 
{
    private String country;
    private float av_price;
    private float buy_price;
    private float sell_price;
    private float buy_comm;
    private float sell_comm;
    private float balance;
    //Constructors
    public Eurochange(String country,float balance)
    {
        this.country = country;
        this.balance = balance;
    }
    public Eurochange(String country,float av_price,float buy_price,float sell_price,float buy_comm,float sell_comm,float balance)
    {
        this.country = country;
        this.av_price = av_price;
        this.buy_price = buy_price;
        this.sell_price = sell_price;
        this.buy_comm = buy_comm;
        this.sell_comm = sell_comm;
        this.balance = balance;
    }
    
    //Getters Methods
    public String getCountry()
    {
     return this.country;
    }
    
    public float getAv_price()
    {
     return this.av_price;
    }
    
    public float getBuy_price()
    {
     return this.buy_price;
    }
    
    public float getSell_price()
    {
     return this.sell_price;
    }
    
    public float getBuy_comm()
    {
     return this.buy_comm;
    }
    
    public float getSell_comm()
    {
     return this.sell_comm;
    }
    
    public float getBalance()
    {
     return this.balance;
    }
    //Set Methods
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public void setAv_price(float av_price)
    {
        this.av_price = av_price;
    }
    
    public void setBuy_price(float buy_price)
    {
        this.buy_price = buy_price;
    }
    
    public void setSell_price(float sell_price)
    {
        this.sell_price = sell_price;
    }
    
    public void setBuy_comm(float buy_comm)
    {
        this.buy_comm = buy_comm;
    }
    
    public void setSell_comm(float sell_comm)
    {
        this.sell_comm = sell_comm;
    }
    
    public void setBalance(float balance)
    {
        this.balance = balance;
    }
    
    //  --Functions--
    public void PrintBuySellWithComm() //function that prints buy/sell values (5)
    {
        float buy;
        float sell;
        buy= buy_price +(buy_price * buy_comm);
        sell= sell_price -(sell_price * sell_comm);
        System.out.println(country +"--->   ΑΓΟΡΑ = " + buy + " || ΠΩΛΗΣΗ = " + sell);
    }
    
    public float BuyAmountInEuros(float amount) //function that calculates a specific amount in euros(buy)
    {
        float buy;
        buy= buy_price +(buy_price * buy_comm);
        amount= amount*buy ;
        return amount;
    }
    
    public float SellAmountInEuros(float amount)//function that calculates a specific amount in euros(sell)
    {
        float sell;
        sell= sell_price -( sell_price * sell_comm );
        amount= amount * sell ;
        return amount;
    }
    
	public float BuyAmountBank(float amount) //function that calculates a specific amount in euros(buy) for banks
    {
        amount= amount * buy_price ;
        return amount;
    }
    
    public float SellAmountBank(float amount)//function that calculates a specific amount in euros(sell) for banks
    {
        amount= amount * sell_price ;
        return amount;
    }
	
    public String UpdateBalance() //function that prints buy/sell values (5)
    {
        String s=this.country+"; "+this.balance;
        
        return s;
    }
}