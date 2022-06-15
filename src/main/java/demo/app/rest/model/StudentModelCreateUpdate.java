package demo.app.rest.model;

public class StudentModelCreateUpdate {

    private String name;
    private String oib;
    private String mobilePhone;
    private String email;
    private Integer mentorId;

    public StudentModelCreateUpdate(String name, String oib, String mobilePhone, String email, Integer mentorId) {
        this.name = name;
        this.oib = oib;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.mentorId = mentorId;
    }

    public StudentModelCreateUpdate() {
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

    public Integer getMentorId() {
        return mentorId;
    }

    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                ", name='" + name + '\'' +
                ", oib='" + oib + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", mentorId=" + mentorId +
                '}';
    }
}
