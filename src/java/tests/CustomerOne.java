package tests;
import java.util.Objects;

public class CustomerOne {
    private final String name;
    private final String email;
    private final String phoneNumber;

    public CustomerOne(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', email='" + email + "', phoneNumber='" + phoneNumber + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerOne customer = (CustomerOne) o;
        return name.equals(customer.name) &&
                email.equals(customer.email) &&
                phoneNumber.equals(customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber);
    }
}

class Main1 {
    public static void main(String[] args) {
        CustomerOne customer1 = new CustomerOne("Alice", "alice@example.com", "123-456-7890");
        CustomerOne customer2 = new CustomerOne("Alice", "alice@example.com", "123-456-7890");

        System.out.println(customer1);  // Customer{name='Alice', email='alice@example.com', phoneNumber='123-456-7890'}
        System.out.println(customer1.equals(customer2));  // true
    }
}