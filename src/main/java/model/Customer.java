package model;

public class Customer {
    int id;
    String name;
    String address;
    int age;
    boolean isMale;

    public Customer() {
    }
    public Customer( String name, String address, int age, boolean isMale) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.isMale = isMale;
    }

    public Customer(int id, String name, String address, int age, boolean isMale) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
