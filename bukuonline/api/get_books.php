<?php
// File: get_books.php
// Endpoint untuk mengambil semua buku atau buku berdasarkan ID

require_once 'config.php';

// Hanya terima method GET
if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    sendResponse(false, "Method tidak diizinkan");
    exit();
}

// Koneksi database
$conn = getDBConnection();

// Cek apakah ada parameter book_id
if (isset($_GET['book_id'])) {
    // Ambil buku berdasarkan ID
    $book_id = intval($_GET['book_id']);
    
    $stmt = $conn->prepare("SELECT * FROM buku WHERE buku_id = ?");
    $stmt->bind_param("i", $book_id);
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows > 0) {
        $book = $result->fetch_assoc();
        sendResponse(true, "Buku ditemukan", $book);
    } else {
        http_response_code(404);
        sendResponse(false, "Buku tidak ditemukan");
    }
    
    $stmt->close();
} else {
    // Ambil semua buku
    $sql = "SELECT * FROM buku ORDER BY judul ASC";
    $result = $conn->query($sql);
    
    $books = [];
    while ($row = $result->fetch_assoc()) {
        $books[] = $row;
    }
    
    sendResponse(true, "Data buku berhasil diambil", $books);
}

$conn->close();
?>
