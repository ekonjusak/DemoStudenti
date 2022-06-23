package demo.app.rest.model;

public class MentorModelCreateUpdate {

    private String name;
    private String oib;
    private String mobilePhone;
    private String email;

    public MentorModelCreateUpdate(String name, String oib, String mobilePhone, String email) {
        this.name = name;
        this.oib = oib;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public MentorModelCreateUpdate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "StudentModel{" +
                ", name='" + name + '\'' +
                ", oib='" + oib + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
