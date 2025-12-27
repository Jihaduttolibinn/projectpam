<?php
// File: login.php
// Endpoint untuk login admin dan user

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
if (!isset($data['username']) || !isset($data['password'])) {
    http_response_code(400);
    sendResponse(false, "Username dan password harus diisi");
    exit();
}

$username = $data['username'];
$password = $data['password'];

// Koneksi database
$conn = getDBConnection();

// Cek di tabel admin terlebih dahulu
$stmt = $conn->prepare("SELECT admin_id, username, email FROM admin WHERE username = ? AND password = ?");

if (!$stmt) {
    http_response_code(500);
    sendResponse(false, "Database Error (Admin): " . $conn->error);
    exit();
}

$stmt->bind_param("ss", $username, $password);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    // Login sebagai admin berhasil
    $user = $result->fetch_assoc();
    sendResponse(true, "Login berhasil", [
        "user" => [
            "user_id" => $user['admin_id'],
            "username" => $user['username'],
            "email" => $user['email'],
            "role" => "admin"
        ]
    ]);
} else {
    // Cek di tabel pengguna
    $stmt = $conn->prepare("SELECT user_id, username, email FROM pengguna WHERE username = ? AND password = ?");
    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows > 0) {
        // Login sebagai user berhasil
        $user = $result->fetch_assoc();
        sendResponse(true, "Login berhasil", [
            "user" => [
                "user_id" => $user['user_id'],
                "username" => $user['username'],
                "email" => $user['email'],
                "role" => "user"
            ]
        ]);
    } else {
        // Login gagal
        http_response_code(401);
        sendResponse(false, "Username atau password salah");
    }
}

$stmt->close();
$conn->close();
?>
