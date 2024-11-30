package tests;
/* By using record class we can eliminate boilerplate used in CustomerOne class */
record Customer(String name, String email, String phoneNumber) {}

class Main {
    public static void main(String[] args) {
        Customer customer1 = new Customer("Alice", "alice@example.com", "123-456-7890");
        Customer customer2 = new Customer("Alice", "alice@example.com", "123-456-7890");

        System.out.println(customer1);  // Customer[name=Alice, email=alice@example.com, phoneNumber=123-456-7890]
        System.out.println(customer1.equals(customer2));  // true
    }
}