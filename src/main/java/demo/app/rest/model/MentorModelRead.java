package demo.app.rest.model;

public class MentorModelRead {

    private Integer id;
    private String name;
    private String oib;
    private String mobilePhone;
    private String email;

    public MentorModelRead(Integer id, String name, String oib, String mobilePhone, String email) {
        this.id = id;
        this.name = name;
        this.oib = oib;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public MentorModelRead() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "StudentModelRead{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oib='" + oib + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
