package model;

public class HocPhan {
    private String mamonhoc;
    private String tenmonhoc;
    private Integer sotinchi;
    private String msv;
    private Integer songaynghi;
    private Double diem;

    public HocPhan(String mamonhoc, String tenmonhoc, Integer sotinchi, String msv, Integer songaynghi, Double diem) {
        this.mamonhoc = mamonhoc;
        this.tenmonhoc = tenmonhoc;
        this.sotinchi = sotinchi;
        this.msv = msv;
        this.songaynghi = songaynghi;
        this.diem = diem;
    }

    public String getMamonhoc() {
        return mamonhoc;
    }

    public void setMamonhoc(String mamonhoc) {
        this.mamonhoc = mamonhoc;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public Integer getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(Integer sotinchi) {
        this.sotinchi = sotinchi;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public Integer getSongaynghi() {
        return songaynghi;
    }

    public void setSongaynghi(Integer songaynghi) {
        this.songaynghi = songaynghi;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return "\t" + "Mã môn học: " + mamonhoc + "\t" + "\t"
                + "Tên môn học: " + tenmonhoc + "\t"
                + "Số tín chỉ: " + sotinchi + "\t"
                + "Số ngày nghỉ: " + songaynghi + "\t" + "\t"
                + "Điểm: "   + diem + "\t";
    }
}
