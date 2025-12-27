# Panduan Instalasi Backend Buku Online

## 📁 Struktur Folder

```
bukuonline/
├── database.sql           # File SQL untuk membuat database dan tabel
└── api/
    ├── config.php         # Konfigurasi database
    ├── login.php          # Endpoint login
    ├── get_books.php      # Endpoint get buku
    ├── add_book.php       # Endpoint tambah buku
    ├── update_book.php    # Endpoint update buku
    ├── delete_book.php    # Endpoint hapus buku
    └── search_books.php   # Endpoint cari buku
```

## 🚀 Cara Instalasi

### 1. Copy Folder ke XAMPP

1. Copy seluruh folder `bukuonline` ke `C:\xampp\htdocs\`
2. Hasil akhir: `C:\xampp\htdocs\bukuonline\`

### 2. Buat Database

1. Buka XAMPP Control Panel
2. Start **Apache** dan **MySQL**
3. Buka browser, akses: `http://localhost/phpmyadmin`
4. Klik tab **SQL**
5. Copy isi file `database.sql` dan paste ke SQL editor
6. Klik **Go** untuk execute

### 3. Test API

Buka browser dan test endpoint berikut:

#### Test Get All Books
```
http://localhost/bukuonline/api/get_books.php
```

#### Test Login (gunakan Postman atau curl)
```
POST http://localhost/bukuonline/api/login.php
Body (JSON):
{
    "username": "admin",
    "password": "admin123"
}
```

## 📝 Endpoint API

| Method | Endpoint | Deskripsi | Parameter |
|--------|----------|-----------|-----------|
| POST | `/api/login.php` | Login admin/user | Body: username, password |
| GET | `/api/get_books.php` | Get semua buku | - |
| GET | `/api/get_books.php?book_id=1` | Get buku by ID | Query: book_id |
| POST | `/api/add_book.php` | Tambah buku baru | Body: judul, penulis, buku |
| PUT | `/api/update_book.php` | Update buku | Body: buku_id, judul, penulis, buku |
| DELETE | `/api/delete_book.php?book_id=1` | Hapus buku | Query: book_id |
| GET | `/api/search_books.php?search=laskar` | Cari buku | Query: search |

## 🔐 Default Credentials

### Admin
- Username: `admin`
- Password: `admin123`

### User
- Username: `user`
- Password: `user123`

## 📚 Sample Data Buku

Database sudah include 3 buku sample:
1. Laskar Pelangi - Andrea Hirata
2. Bumi Manusia - Pramoedya Ananta Toer
3. Negeri 5 Menara - Ahmad Fuadi

## ⚙️ Konfigurasi

Jika perlu mengubah konfigurasi database, edit file `api/config.php`:

```php
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'bukuonline');
```

## 🔧 Troubleshooting

### Error: Connection failed
- Pastikan MySQL di XAMPP sudah running
- Cek username/password database di config.php

### Error: Table doesn't exist
- Pastikan sudah menjalankan file database.sql
- Cek apakah database `bukuonline` sudah dibuat

### Error: 404 Not Found
- Pastikan folder ada di `C:\xampp\htdocs\bukuonline\`
- Cek Apache di XAMPP sudah running

## ✅ Selesai!

Backend API siap digunakan. Jalankan aplikasi Android dan pastikan BASE_URL sudah benar:
- Emulator: `http://10.0.2.2/bukuonline/api/`
- Device fisik: `http://[IP_KOMPUTER]/bukuonline/api/`
