package kr.ac.konkuk.yulgongmate;

public class CalendarData {
    private String et_schedule_title;
    private String et_date;
    private String et_time;
    private int btn_mod;
    private int btn_del;

    public CalendarData(String et_schedule_title, String et_date, String et_time) {
        this.et_schedule_title = et_schedule_title;
        this.et_date = et_date;
        this.et_time = et_time;
    }

    public String getEt_schedule_title() {
        return et_schedule_title;
    }

    public void setEt_schedule_title(String et_schedule_title) {
        this.et_schedule_title = et_schedule_title;
    }

    public String getEt_date() {
        return et_date;
    }

    public void setEt_date(String et_date) {
        this.et_date = et_date;
    }

    public String getEt_time() {
        return et_time;
    }

    public void setEt_time(String et_time) {
        this.et_time = et_time;
    }

    public int getBtn_mod() {
        return btn_mod;
    }

    public void setBtn_mod(int btn_mod) {
        this.btn_mod = btn_mod;
    }

    public int getBtn_del() {
        return btn_del;
    }

    public void setBtn_del(int btn_del) {
        this.btn_del = btn_del;
    }
}
