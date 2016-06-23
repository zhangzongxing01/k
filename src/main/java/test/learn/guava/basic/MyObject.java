package test.learn.guava.basic;

public class MyObject  {

    private String  name;
    private Integer number1;
    private Integer number2;

    public MyObject(String name, Integer number1, Integer number2) {
        super();
        this.name = name;
        this.number1 = number1;
        this.number2 = number2;
    }

    public MyObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }


}
