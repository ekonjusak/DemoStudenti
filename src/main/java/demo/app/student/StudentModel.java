package demo.app.student;

import javax.xml.bind.annotation.XmlRootElement;

import static java.lang.String.valueOf;

public class StudentModel {

    private Integer id;
    private String email;
    private String ime;
    private Number mentorid;
    private Number oib;
    private String phonenumber;

    public StudentModel(Integer id, String email, String ime, Number mentorid, Number oib, String phonenumber) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.mentorid = mentorid;
        this.oib = oib;
        this.phonenumber = phonenumber;
    }

    public StudentModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Number getMentorid() {
        return mentorid;
    }

    public void setMentorid(Number mentorid) {
        this.mentorid = mentorid;
    }

    public Number getOib() {
        return oib;
    }

    public void setOib(Number oib) {
        this.oib = oib;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", ime='" + ime + '\'' +
                ", mentorid=" + mentorid +
                ", oib=" + oib +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
