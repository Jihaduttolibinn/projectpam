-- Database: bukuonline
-- Buat database dan tabel untuk aplikasi Buku Online

CREATE DATABASE IF NOT EXISTS bukuonline;
USE bukuonline;

-- Tabel Admin
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100)
);

-- Tabel Buku
CREATE TABLE IF NOT EXISTS buku (
    buku_id INT PRIMARY KEY AUTO_INCREMENT,
    judul VARCHAR(100) NOT NULL,
    penulis VARCHAR(255) NOT NULL,
    buku LONGTEXT NOT NULL
);

-- Tabel Pengguna
CREATE TABLE IF NOT EXISTS pengguna (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Insert data admin
INSERT INTO admin (username, password, email) VALUES 
('admin', 'admin123', 'admin@bukuonline.com');

-- Insert data pengguna
INSERT INTO pengguna (username, password) VALUES 
('user', 'user123');

-- Insert data buku sample
INSERT INTO buku (judul, penulis, buku) VALUES 
('Laskar Pelangi', 'Andrea Hirata', 'Laskar Pelangi adalah novel pertama karya Andrea Hirata yang diterbitkan oleh Bentang Pustaka pada tahun 2005. Novel ini bercerita tentang kehidupan 10 anak dari keluarga miskin yang bersekolah di SD Muhammadiyah di Belitung. Mereka adalah Ikal, Arai, Sahara, Lintang, Mahar, A Kiong, Syahdan, Kucai, Borek, dan Trapani. Kesepuluh anak ini tergabung dalam kelompok yang mereka sebut sebagai Laskar Pelangi.'),

('Bumi Manusia', 'Pramoedya Ananta Toer', 'Bumi Manusia adalah novel pertama dari Tetralogi Buru karya Pramoedya Ananta Toer. Novel ini mengisahkan perjalanan seorang tokoh bernama Minke, seorang pribumi yang mendapat pendidikan Eropa dan menjadi salah satu pelopor kebangkasan nasional. Cerita berlatar pada masa kolonial Belanda di Indonesia.'),

('Negeri 5 Menara', 'Ahmad Fuadi', 'Negeri 5 Menara adalah novel karya Ahmad Fuadi yang diterbitkan tahun 2009. Novel ini bercerita tentang enam santri dari berbagai daerah di Indonesia yang belajar di Pondok Madani (PM) Ponorogo. Mereka adalah Alif dari Maninjau, Said dari Surabaya, Dulmajid dari Sumenep, Raja dari Medan, Atang dari Bandung, dan Baso dari Gowa.');
