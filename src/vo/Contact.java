package vo;

public class Contact {
    private Long id;
    private String name;
    private int age;
    private String phone;

    public Contact(Long id, String name, int age, String phone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    // Getter
    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public int age(){
        return this.age;
    }
    public String getPhone(){
        return this.phone;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "[" + id + "] " + name + " / " + age + " / " + phone;
    }
}