package example.day02_2.model.dto;

public class WaitingDto {
    int no;
    String phoneNumber;
    int count;
    public WaitingDto(){};

    public WaitingDto(int no, String phoneNumber, int count) {
        this.no = no;
        this.phoneNumber = phoneNumber;
        this.count = count;
    };
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WaitingDto [no=" + no + ", phoneNumber=" + phoneNumber + ", count=" + count + "]";
    }

    
}
