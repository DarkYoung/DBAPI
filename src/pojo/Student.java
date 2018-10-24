package pojo;


import org.springframework.stereotype.Component;

@Component("student")
public class Student {
    private int registno;
    private int kdno;
    private int kcno;
    private int ccno;
    private int seat;
    private String name;

    public int getRegistno() {
        return registno;
    }

    public void setRegistno(int registno) {
        this.registno = registno;
    }

    public int getKdno() {
        return kdno;
    }

    public void setKdno(int kdno) {
        this.kdno = kdno;
    }

    public int getKcno() {
        return kcno;
    }

    public void setKcno(int kcno) {
        this.kcno = kcno;
    }

    public int getCcno() {
        return ccno;
    }

    public void setCcno(int ccno) {
        this.ccno = ccno;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryKey() {
        return registno + "";
    }
}
