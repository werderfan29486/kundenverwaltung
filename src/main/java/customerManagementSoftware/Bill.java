package customerManagementSoftware;

public class Bill {

    private int hourlyPrice = 80;
    private int numberOfHours;
    private String billNumber;
    private static int count = 0;

    public Bill() {
        ++count;
        this.billNumber = String.format("%03d", count);
    }

    public int getHourlyPrice() {
        return hourlyPrice;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public static int getCount() {
        return count;
    }

    public void setHourlyPrice(int hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public static void setCount(int count) {
        Bill.count = count;
    }
}
