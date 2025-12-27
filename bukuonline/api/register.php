<?php
// File: register.php
// Endpoint untuk registrasi pengguna baru

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
if (!isset($data['username']) || !isset($data['password']) || !isset($data['email'])) {
    http_response_code(400);
    sendResponse(false, "Username, password, dan email harus diisi");
    exit();
}

$username = $data['username'];
$password = $data['password'];
$email = $data['email'];

// Koneksi database
$conn = getDBConnection();

// Cek apakah username sudah ada
$stmt = $conn->prepare("SELECT user_id FROM pengguna WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    http_response_code(400);
    sendResponse(false, "Username sudah digunakan");
    $stmt->close();
    $conn->close();
    exit();
}

$stmt->close();

// Insert pengguna baru
$stmt = $conn->prepare("INSERT INTO pengguna (username, password, email) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $username, $password, $email);

if ($stmt->execute()) {
    sendResponse(true, "Registrasi berhasil");
} else {
    http_response_code(500);
    sendResponse(false, "Gagal mendaftarkan pengguna: " . $stmt->error);
}

$stmt->close();
$conn->close();
?>
