package com.example.technoligest.lastdraft;

public class Product {
    private String id;
    private String name;
    private String back;
    private String front;
    private String exp;
    private String location;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The back
     */
    public String getBack() {
        return back;
    }

    /**
     *
     * @param back
     * The back
     */
    public void setBack(String back) {
        this.back = back;
    }

    /**
     *
     * @return
     * The front
     */
    public String getFront() {
        return front;
    }

    /**
     *
     * @param front
     * The front
     */
    public void setFront(String front) {
        this.front = front;
    }

    /**
     *
     * @return
     * The exp
     */
    public String getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(String exp) {
        this.exp = exp;
    }

    /**
     *
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
        if(Integer.parseInt(front)<4)
            return "Name: "+name+
                    "\nStock: "+back+
                    "\nFront: "+front+
                    "\nStatus: LOW";
        if(Integer.parseInt(front)<8)
            return "Name: "+name+
                    "\nStock: "+back+
                    "\nFront: "+front+
                    "\nStatus: GOOD";
        return "Name: "+name+
                "\nStock: "+back+
                "\nFront: "+front+
                "\nStatus: PERFECT";

    }

}