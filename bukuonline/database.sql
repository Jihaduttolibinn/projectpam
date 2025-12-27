-- Database: bukuonline
-- Buat database dan tabel untuk aplikasi Buku Online

CREATE DATABASE IF NOT EXISTS bukuonline;
USE bukuonline;

-- Hapus tabel lama jika ada (untuk reset struktur)
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS buku;
DROP TABLE IF EXISTS pengguna;

-- Tabel Admin
CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100)
);

-- Tabel Buku
CREATE TABLE buku (
    buku_id INT PRIMARY KEY AUTO_INCREMENT,
    judul VARCHAR(100) NOT NULL,
    penulis VARCHAR(255) NOT NULL,
    buku LONGTEXT NOT NULL
);

-- Tabel Pengguna
CREATE TABLE pengguna (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100)
);

-- Insert data admin
INSERT INTO admin (username, password, email) VALUES 
('admin', 'admin123', 'admin@bukuonline.com');

-- Insert data pengguna
INSERT INTO pengguna (username, password, email) VALUES 
('user', 'user123', 'user@bukuonline.com');

-- Insert data buku sample
INSERT INTO buku (judul, penulis, buku) VALUES 
('Laskar Pelangi', 'Andrea Hirata', 'Laskar Pelangi adalah novel pertama karya Andrea Hirata yang diterbitkan oleh Bentang Pustaka pada tahun 2005. Novel ini bercerita tentang kehidupan 10 anak dari keluarga miskin yang bersekolah di SD Muhammadiyah di Belitung.'),
('Bumi Manusia', 'Pramoedya Ananta Toer', 'Bumi Manusia adalah novel pertama dari Tetralogi Buru karya Pramoedya Ananta Toer. Novel ini mengisahkan perjalanan seorang tokoh bernama Minke.'),
('Negeri 5 Menara', 'Ahmad Fuadi', 'Negeri 5 Menara adalah novel karya Ahmad Fuadi yang diterbitkan tahun 2009. Novel ini bercerita tentang enam santri dari berbagai daerah di Indonesia.');
