package usertext.basic.entity;

public class UserExtraInfo {
    private Integer extraInfoId;

    private Integer userId;

    private String phoneNumber;

    private String address;

    private Byte age;

    private String idCardNumber;

    public Integer getExtraInfoId() {
        return extraInfoId;
    }

    public void setExtraInfoId(Integer extraInfoId) {
        this.extraInfoId = extraInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber == null ? null : idCardNumber.trim();
    }
}