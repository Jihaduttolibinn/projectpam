<?php
// File: search_books.php
// Endpoint untuk mencari buku berdasarkan judul atau penulis

require_once 'config.php';

// Hanya terima method GET
if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    http_response_code(405);
    sendResponse(false, "Method tidak diizinkan");
    exit();
}

// Ambil parameter search dari query string
if (!isset($_GET['search'])) {
    http_response_code(400);
    sendResponse(false, "Parameter search harus diisi");
    exit();
}

$search = $_GET['search'];

// Koneksi database
$conn = getDBConnection();

// Cari buku berdasarkan judul atau penulis
$search_param = "%" . $search . "%";
$stmt = $conn->prepare("SELECT * FROM buku WHERE judul LIKE ? OR penulis LIKE ? ORDER BY judul ASC");
$stmt->bind_param("ss", $search_param, $search_param);
$stmt->execute();
$result = $stmt->get_result();

$books = [];
while ($row = $result->fetch_assoc()) {
    $books[] = $row;
}

if (count($books) > 0) {
    sendResponse(true, "Ditemukan " . count($books) . " buku", $books);
} else {
    sendResponse(true, "Tidak ada buku yang ditemukan", []);
}

$stmt->close();
$conn->close();
?>
