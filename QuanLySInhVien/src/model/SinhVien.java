package model;

public class SinhVien {
    private String msv;
    private String fullname;
    private Integer soluonghocphan;
    private Double diemtrungbinh;

    public SinhVien(String msv, String fullname, Integer soluonghocphan, Double diemtrungbinh) {
        this.msv = msv;
        this.fullname = fullname;
        this.soluonghocphan = soluonghocphan;
        this.diemtrungbinh = diemtrungbinh;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getSoluonghocphan() {
        return soluonghocphan;
    }

    public void setSoluonghocphan(Integer soluonghocphan) {
        this.soluonghocphan = soluonghocphan;
    }

    public Double getDiemtrungbinh() {
        return diemtrungbinh;
    }

    public void setDiemtrungbinh(Double diemtrungbinh) {
        this.diemtrungbinh = diemtrungbinh;
    }

    @Override
    public String toString() {
        return "- MSV: " + msv + "\n"
                + "- Họ và tên: " + fullname + "\n"
                + "- Số học phần: " + soluonghocphan + "\n"
                + "- Điểm trung bình: " + diemtrungbinh;
    }
}
