<?php
// File: delete_book.php
// Endpoint untuk menghapus buku

require_once 'config.php';

// Hanya terima method DELETE
if ($_SERVER['REQUEST_METHOD'] !== 'DELETE') {
    http_response_code(405);
    sendResponse(false, "Method tidak diizinkan");
    exit();
}

// Ambil parameter book_id dari query string
if (!isset($_GET['book_id'])) {
    http_response_code(400);
    sendResponse(false, "ID buku harus diisi");
    exit();
}

$book_id = intval($_GET['book_id']);

// Koneksi database
$conn = getDBConnection();

// Hapus buku
$stmt = $conn->prepare("DELETE FROM buku WHERE buku_id = ?");
$stmt->bind_param("i", $book_id);

if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        sendResponse(true, "Buku berhasil dihapus");
    } else {
        http_response_code(404);
        sendResponse(false, "Buku tidak ditemukan");
    }
} else {
    http_response_code(500);
    sendResponse(false, "Gagal menghapus buku: " . $stmt->error);
}

$stmt->close();
$conn->close();
?>
