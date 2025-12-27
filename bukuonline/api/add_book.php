<?php
// File: add_book.php
// Endpoint untuk menambah buku baru

require_once 'config.php';

// Hanya terima method POST
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    sendResponse(false, "Method tidak diizinkan");
    exit();
}

// Ambil data JSON dari request body
$json = file_get_contents('php://input');
$data = json_decode($json, true);

// Validasi input
if (!isset($data['judul']) || !isset($data['penulis']) || !isset($data['buku'])) {
    http_response_code(400);
    sendResponse(false, "Judul, penulis, dan isi buku harus diisi");
    exit();
}

$judul = $data['judul'];
$penulis = $data['penulis'];
$buku = $data['buku'];

// Koneksi database
$conn = getDBConnection();

// Insert buku baru
$stmt = $conn->prepare("INSERT INTO buku (judul, penulis, buku) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $judul, $penulis, $buku);

if ($stmt->execute()) {
    $new_id = $conn->insert_id;
    
    // Ambil data buku yang baru ditambahkan
    $stmt2 = $conn->prepare("SELECT * FROM buku WHERE buku_id = ?");
    $stmt2->bind_param("i", $new_id);
    $stmt2->execute();
    $result = $stmt2->get_result();
    $new_book = $result->fetch_assoc();
    
    http_response_code(201);
    sendResponse(true, "Buku berhasil ditambahkan", $new_book);
    
    $stmt2->close();
} else {
    http_response_code(500);
    sendResponse(false, "Gagal menambahkan buku: " . $stmt->error);
}

$stmt->close();
$conn->close();
?>
