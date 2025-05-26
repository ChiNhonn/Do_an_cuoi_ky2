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
├── src/
│ ├── model/
│ ├── view/
│ ├── controller/
│ ├── config/
│ ├── Charts/
│ ├── chartdao/
│ ├── dao/
│ └── icon/
├── images/
├── Database/
└── README.md

---

## ⚙️ Hướng Dẫn Cài Đặt & Chạy Chương Trình

I. **Cài đặt các phần mềm cần thiết:**
 - Java JDK 8 trở lên
 - MySQL Server
 - IDE Java (Eclipse, IntelliJ,...)
 - Thêm thư viện: `mysql-connector-java`, `jfreechart`, `jcommon`(có trong file code)
   
   
   
II. **Hướng dẫn tải về **  
	Tải source code về: https://github.com/ChiNhonn/Do_an_cuoi_ky2.git
	
	
	
III. **Khởi tạo CSDL:**
   - Mở MySQL Workbench hoặc phpMyAdmin
   - Chạy file: `Database/spy_agency.sql`
   - Mở xampp và vào trang http://localhost/phpmyadmin/ tạo 1 database mới có tên là spy_agency và import cơ sở dữ liệu trong folder Database -> file spy_agency.sql trong source code.




IV. **Sử dụng eclipse để chạy **
  	
   
 V. **Vào view chạy ở class loginInterface2.**
 
- Tài khoản 
- Username: admin123
- Password: admin123

### Giao diện đăng nhập: 


![Giao diện đăng nhập](images/dangnhap.png)

### Giao diện đăng ký:


![Giao diện đăng ký](images/dangky.png)

### Giao diện chính :


![Giao diện chính](images/giaodienchinh.png)
   
   
### Giao diện chức năng 1:


![Giao diện chức năng 1](images/affiliation.png)
   
### Giao diện chức năng 2:


![Giao diện chức năng 2](images/affiliationrel.png)

### Giao diện chức năng 3:


![Giao diện chức năng 3](images/agent.png)

### Giao diện chức năng 4:


![Giao diện chức năng 4](images/language.png)

### Giao diện chức năng 5:


![Giao diện chức năng 5](images/languagerel.png)

### Giao diện chức năng 6:


![Giao diện chức năng 6](images/mission.png)

### Giao diện chức năng 7:


![Giao diện chức năng 7](images/sc.png)

### Giao diện chức năng 8:


![Giao diện chức năng 8](images/skill.png)

### Giao diện chức năng 9:


![Giao diện chức năng 9](images/skillrel.png)

### Giao diện chức năng 10:


![Giao diện chức năng 10](images/team.png)

### Giao diện chức năng 11:


![Giao diện chức năng 11](images/teamrel.png)

### Giao diện quản lý người dùng :


![Giao diện quản lý người dùng](images/account.png)

### Giao diện chart chính :


![Giao diện chart chính](images/chart.png)

### Giao diện chart 2:


![Giao diện chart 2](images/chart2.png)

### Giao diện chart 3:


![Giao diện chart 3](images/chart3.png)

### Giao diện chart 4:


![Giao diện chart 4](images/chart4.png)


### Giao diện chart 5:

![Giao diện chart 5](images/chart5.png)


### Giao diện chart 6:

![Giao diện chart 6](images/chart6.png)
