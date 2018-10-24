package pojo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component("room")
public class Room {
    private int kdno;
    private int kcno;
    private int ccno;
    private String kdname;
    private String papername;
    private Timestamp exptime;

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

    public String getKdname() {
        return kdname;
    }

    public void setKdname(String kdname) {
        this.kdname = kdname;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    public Timestamp getExptime() {
        return exptime;
    }

    public void setExptime(Timestamp exptime) {
        this.exptime = exptime;
    }

    public String getPrimaryKey() {
        return kdno + "-" + kcno + "-" + ccno;
    }
}
