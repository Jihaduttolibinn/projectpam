<?php
// File: update_book.php
// Endpoint untuk mengupdate buku

require_once 'config.php';

// Hanya terima method PUT
if ($_SERVER['REQUEST_METHOD'] !== 'PUT') {
    http_response_code(405);
    sendResponse(false, "Method tidak diizinkan");
    exit();
}

// Ambil data JSON dari request body
$json = file_get_contents('php://input');
$data = json_decode($json, true);

// Validasi input
if (!isset($data['buku_id']) || !isset($data['judul']) || !isset($data['penulis']) || !isset($data['buku'])) {
    http_response_code(400);
    sendResponse(false, "ID buku, judul, penulis, dan isi buku harus diisi");
    exit();
}

$buku_id = intval($data['buku_id']);
$judul = $data['judul'];
$penulis = $data['penulis'];
$buku = $data['buku'];

// Koneksi database
$conn = getDBConnection();

// Update buku
$stmt = $conn->prepare("UPDATE buku SET judul = ?, penulis = ?, buku = ? WHERE buku_id = ?");
$stmt->bind_param("sssi", $judul, $penulis, $buku, $buku_id);

if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        // Ambil data buku yang sudah diupdate
        $stmt2 = $conn->prepare("SELECT * FROM buku WHERE buku_id = ?");
        $stmt2->bind_param("i", $buku_id);
        $stmt2->execute();
        $result = $stmt2->get_result();
        $updated_book = $result->fetch_assoc();
        
        sendResponse(true, "Buku berhasil diupdate", $updated_book);
        
        $stmt2->close();
    } else {
        http_response_code(404);
        sendResponse(false, "Buku tidak ditemukan atau tidak ada perubahan");
    }
} else {
    http_response_code(500);
    sendResponse(false, "Gagal mengupdate buku: " . $stmt->error);
}

$stmt->close();
$conn->close();
?>
