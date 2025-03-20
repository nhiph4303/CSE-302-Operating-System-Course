package Assignment3;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(3, 1000);

        // Thực hiện giao dịch chuyển tiền từ tài khoản 0 sang tài khoản 1, số tiền 200
        boolean success1 = bank.transaction(0, 1, 200);
        System.out.println("Transaction 1 successful: " + success1);

        // Thực hiện giao dịch chuyển tiền từ tài khoản 1 sang tài khoản 2, số tiền 500
        boolean success2 = bank.transaction(1, 2, 500);
        System.out.println("Transaction 2 successful: " + success2);

        // Kiểm tra số dư các tài khoản sau các giao dịch
        System.out.println("Account 0 balance: " + bank.find(0).getBalance());
        System.out.println("Account 1 balance: " + bank.find(1).getBalance());
        System.out.println("Account 2 balance: " + bank.find(2).getBalance());
    }
}

