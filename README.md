Tác giả: Đường Tri Nhân
Phần mềm quản lý Spy Agency2024

File word và slide PowerPoint nằm trong thư mục BaoCao

SpyAgency2024 là một phần mềm quản lý dữ liệu tình báo được phát triển bằng Java theo mô hình MVC, sử dụng MySQL để lưu trữ dữ liệu và JFreeChart để trực quan hóa. Ứng dụng cho phép quản lý thông tin agent, tổ chức, quan hệ liên kết, và tài khoản người dùng,...

---

## 🎯 Tính Năng Chính

- ✅ Quản lý Affiliation: thêm, sửa, xóa, tìm kiếm, làm mới 
- ✅ Quản lý Affiliationrel: thêm, sửa, xóa, tìm kiếm, làm mới 
- ✅ Quản lý Agent: thêm, sửa, xóa, tìm kiếm, làm mới, nhóm lại
- ✅ Quản lý language: thêm, sửa, xóa, tìm kiếm, làm mới 
- ✅ Quản lý languagerel: thêm, xóa, tìm kiếm
- ✅ Quản lý Mission: thêm, sửa, xóa, tìm kiếm, làm mới, nhóm lại
- ✅ Quản lý securityclearance: thêm, sửa, xóa, tìm kiếm, làm mới 
- ✅ Quản lý skill: thêm, xóa, tìm kiếm
- ✅ Quản lý skillrel: thêm, xóa, tìm kiếm
- ✅ Quản lý team: thêm, sửa, xóa, tìm kiếm, làm mới, nhóm lại
- ✅ Quản lý teamrel: thêm, xóa, tìm kiếm
- ✅ Quản lý tài khoản người dùng (Account): thêm, sửa, xóa, tìm kiếm, làm mới
- ✅ Biểu đồ thống kê
- ✅ Giao diện hiện đại với Java Swing

---

## 🧱 Công Nghệ Sử Dụng

| Thành phần       | Công nghệ     |
|------------------|---------------|
| Ngôn ngữ         | Java          |
| Giao diện        | Java Swing    |
| Kiến trúc        | MVC           |
| Cơ sở dữ liệu    | MySQL         |
| Thư viện biểu đồ | JFreeChart    |

---

## 📂 Cấu Trúc Thư Mục
Do_an_cuoi_ky_2/
│
├── src/			
│ ├── model/ 
│ ├── view/ 
│ ├── controller/ 
│ ├── config/ 
│ ├── Charts/
│ ├── chartdao/
│ ├── dao /
│ └── icon/
├── images/ 
├── Database
└── README.md 

---

## ⚙️ Hướng Dẫn Cài Đặt & Chạy Chương Trình

1. **Cài đặt các phần mềm cần thiết:**
   - Java JDK 8 trở lên
   - MySQL Server
   - IDE Java (Eclipse, IntelliJ,...)
   - Thêm thư viện: `mysql-connector-java`, `jfreechart`, `jcommon`(có trong file code)
   
2.  **Hướng dẫn tải về =**
	Tải source code về:
	
	

3. **Khởi tạo CSDL:**
   - Mở MySQL Workbench hoặc phpMyAdmin
   - Chạy file: `Database/spy_agency.sql`
   - Mở xampp và vào trang http://localhost/phpmyadmin/ tạo 1 database mới có tên là spy_agency và import cơ sở dữ liệu trong folder Database -> file spy_agency.sql trong source code.


4. **Sử dụng eclipse để chạy **
  	
   
5. **Vào view chạy ở class loginInterface2.**

-Tài khoản 
	Username: admin123
	Password: admin123

### Giao diện đăng nhập: 


![Giao diện đăng nhập](images/dangnhap.png)
   
   
   
   
