import model.HocPhan;
import model.SinhVien;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<SinhVien> listSinhVien = new ArrayList<>();
    private static ArrayList<HocPhan> listHocPhan = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static SinhVienRepository sinhVienRepository = new SinhVienRepository();


    public static void headerMenu(){
        System.out.println("-----------------------------------------------------------------");
        System.out.println("1. Hien thi tat ca sinh vien");
        System.out.println("2. Them sinh vien");
        System.out.println("3. Them hoc phan theo ma sinh vien");
        System.out.println("4. Hien thi hoc phan theo ma sinh vien");
        System.out.println("5. Sap xep sinh vien giam dan theo diem trung binh");
        System.out.println("6. Sua hoc phan theo ma sinh vien");
        System.out.println("7. Xoa hoc phan theo ma sinh vien");
        System.out.println("8. Xoa sinh vien theo ma sinh vien");
        System.out.println("9. Sua sinh vien theo ma sinh vien");
        System.out.println("0. Thoat");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Your choice: ");
    }


    public static boolean binarySearch(ArrayList<SinhVien> listSinhVien, String msv){
        int left = 0;
        int right = listSinhVien.size() - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (listSinhVien.get(mid).getMsv().equals(msv)){
                System.out.println(listSinhVien.get(mid));
                return true;
            }
            if (listSinhVien.get(mid).getMsv().compareTo(msv) < 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // thuat toan quick sort

    public static void quickSort(ArrayList<SinhVien> listSinhVien, int left, int right){
        if (left < right){
            int pi = partition(listSinhVien, left, right);
            quickSort(listSinhVien, left, pi - 1);
            quickSort(listSinhVien, pi + 1, right);
        }
    }

    public static int partition(ArrayList<SinhVien> listSinhVien, int left, int right){
        double pivot = listSinhVien.get(right).getDiemtrungbinh();
        int i = left - 1;
        for (int j = left; j < right; j++){
            if (listSinhVien.get(j).getDiemtrungbinh() > pivot){
                i++;
                SinhVien temp = listSinhVien.get(i);
                listSinhVien.set(i, listSinhVien.get(j));
                listSinhVien.set(j, temp);
            }
        }
        SinhVien temp = listSinhVien.get(i + 1);
        listSinhVien.set(i + 1, listSinhVien.get(right));
        listSinhVien.set(right, temp);
        return i + 1;
    }


    public static void displayAllStudents(){
        listSinhVien = sinhVienRepository.layDanhSachSinhVien();
        int stt = 1;
        System.out.println("Danh sach sinh vien: ");
        for (SinhVien sv : listSinhVien) {
            System.out.println("-------------" + "Sinh Vien thu "+ stt + "-------------");
            System.out.println(sv);
            stt++;
            for (HocPhan hp : sinhVienRepository.layDanhSachHocPhan(sv.getMsv())) {
                System.out.println(hp);
            }
        }
    }

    public static void addSinhVien() {
        System.out.printf("Nhap ma sinh vien: ");
        String msv = sc.nextLine();
        System.out.printf("Nhap ten sinh vien: ");
        String fullname = sc.nextLine();
        int soluonghocphan = 0;
        double diemtrungbinh = 0;
        SinhVien sv = new SinhVien(msv, fullname, soluonghocphan, diemtrungbinh);
        sinhVienRepository.themSinhVien(sv);
        listSinhVien = sinhVienRepository.layDanhSachSinhVien();
    }


    public static void xoaSinhVienTheoMSV(){
        System.out.printf("Nhap ma sinh vien: ");
        String msv = sc.nextLine();
        if (!binarySearch(listSinhVien, msv)){
            System.out.println("Khong tim thay sinh vien co ma sinh vien la: " + msv);
        } else {
            sinhVienRepository.xoaSinhVien(msv);
        }
    }


    public static void themHocPhanTheoMSV(){
        System.out.printf("Nhap ma sinh vien: ");
        String msv = sc.nextLine();
        if (!binarySearch(listSinhVien, msv)){
            System.out.println("Khong tim thay sinh vien co ma sinh vien la: " + msv);
        } else {
            System.out.printf("Nhap ma mon hoc: ");
            String mamonhoc = sc.nextLine();
            System.out.printf("Nhap ten mon hoc: ");
            String tenmonhoc = sc.nextLine();
            System.out.printf("Nhap so tin chi: ");
            Integer sotinchi = sc.nextInt();
            sc.nextLine();
            System.out.printf("Nhap so ngay nghi: ");
            Integer songaynghi = sc.nextInt();
            sc.nextLine();
            System.out.printf("Nhap diem: ");
            Double diem = sc.nextDouble();
            sc.nextLine();
            HocPhan hp = new HocPhan(mamonhoc,tenmonhoc,sotinchi,msv,songaynghi,diem);
            sinhVienRepository.themHocPhan(hp);
            sinhVienRepository.capNhatSinhVien(msv);
        }
    }

    public static void hienThiHocPhanTheoMSV(){
        System.out.printf("Nhap ma sinh vien: ");
        String msv = sc.nextLine();
        if (!binarySearch(listSinhVien, msv)){
            System.out.println("Khong tim thay sinh vien co ma sinh vien la: " + msv);
        } else {
            for (HocPhan hp : sinhVienRepository.layDanhSachHocPhan(msv)){
                System.out.println(hp);
            }
        }
    }

    public static void xoaHocPhanTheoMSV(){
        System.out.printf("Nhap ma sinh vien: ");
        String msv = sc.nextLine();
        if (!binarySearch(listSinhVien, msv)){
            System.out.println("Khong tim thay sinh vien co ma sinh vien la: " + msv);
        } else {
            for (HocPhan hp : sinhVienRepository.layDanhSachHocPhan(msv)){
                System.out.println(hp);
            }
            System.out.printf("Nhap ma mon hoc: ");
            String mamonhoc = sc.nextLine();
            sinhVienRepository.xoaHocPhan(msv, mamonhoc);
            sinhVienRepository.capNhatSinhVien(msv);
        }
    }




    public static void main(String[] args) {
        int choice;
        do{
            headerMenu();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    addSinhVien();
                    break;
                case 3:
                    themHocPhanTheoMSV();
                    break;
                case 4:
                    hienThiHocPhanTheoMSV();
                    break;
                case 5:
                    quickSort(listSinhVien, 0, listSinhVien.size() - 1);
                    for (SinhVien sv : listSinhVien){
                        System.out.println("- MSV: " + sv.getMsv() +
                                "  Ho va ten: " + sv.getFullname() +
                                "  Diem trung binh: " + sv.getDiemtrungbinh());
                    }
                    break;
                case 6:
                    System.out.println("Sua hoc phan theo ma sinh vien");
                    break;
                case 7:
                    xoaHocPhanTheoMSV();
                    break;
                case 8:
                    xoaSinhVienTheoMSV();
                    break;
                case 0:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (choice != 0);
    }
}
