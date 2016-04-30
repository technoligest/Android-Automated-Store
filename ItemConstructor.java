public class ItemConstructor {

    private String itemName;
    private int itemId;
    private String location;
    private int onShelf;
    private int inStock;
    private boolean inQueue;


    //Constructor
    public ItemConstructor(String itemName, int itemId, String location, int onShelf, int inStock, boolean inQueue){

        this.itemName = itemName;
        this.itemId = itemId;
        this.location = location;
        this.onShelf = onShelf;
        this.inStock = inStock;
        this.inQueue = inQueue;

    }

    //Set Objects
    public void setItemName(String name){itemName = name;}
    public void setItemId(int id){itemId = id;}
    public void setLocation(String loc){location = loc;}
    public void setOnShelf(int shelf){onShelf = shelf;}
    public void setInStock(int stock){inStock = stock;}
    public void setInQueue(boolean queue){inQueue = queue;}

    //Get Objects
    public String getItemName(){return itemName;}
    public int getItemId(){return itemId;}
    public String getLocation(){return location;}
    public int getOnShelf(){return onShelf;}
    public int getInStock(){return inStock;}
    public boolean getInQueue(){return inQueue;}

    //Increment onShelf and inStock
    public void shelfIncrement(){setOnShelf(getOnShelf() + 1);}
    public void stockIncrement(){setInStock(getInStock() - 1);}

}
