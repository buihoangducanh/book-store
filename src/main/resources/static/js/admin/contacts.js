let contact = {};
// Hiển thị biểu mẫu thêm liên hệ
function showAddForm() {
    document.getElementById('formTitle').innerText = 'Thêm liên hệ';
    document.getElementById('contactForm').style.display = 'block';
    clearForm();
}
// Ẩn biểu mẫu
function hideForm() {
    document.getElementById('contactForm').style.display = 'none';
}

function showEditForm(button) {
    const row = button.parentNode.parentNode; // Lấy hàng chứa nút "Sửa"
    if (row) {
        // Lấy thông tin từ các ô trong hàng
        contact.id = row.cells[0].innerText;
        contact.name = row.cells[1].innerText;
        contact.email = row.cells[2].innerText;
        contact.subject = row.cells[3].innerText;
        contact.message = row.cells[4].innerText;
        contact.created_at = row.cells[5].innerText;

        // Đẩy thông tin vào form sửa liên hệ
        document.getElementById('formTitle').innerText = 'Sửa liên hệ';
        document.getElementById('contactId').value = contact.id;
        document.getElementById('name').value = contact.name;
        document.getElementById('email').value = contact.email;
        document.getElementById('subject').value = contact.subject;
        document.getElementById('message').value = contact.message;
        document.getElementById('contactForm').style.display = 'block';
    }
}

// Xóa dữ liệu trong biểu mẫu
function clearForm() {
    document.getElementById('contactId').value = '';
    document.getElementById('name').value = '';
    document.getElementById('email').value = '';
    document.getElementById('subject').value = '';
    document.getElementById('message').value = '';
}

//Phân trang
// Số sản phẩm trên mỗi trang
var itemsPerPage = 5;

// Lấy tất cả các hàng của bảng sản phẩm
var rows = document.querySelectorAll(".table tbody tr");

// Tổng số trang
var totalPages = Math.ceil(rows.length / itemsPerPage);

// Hiển thị trang đầu tiên khi tải trang
showPage(1);

function showPage(page) {
    var start = (page - 1) * itemsPerPage;
    var end = start + itemsPerPage;

    // Ẩn tất cả các hàng của bảng sản phẩm
    for (var i = 0; i < rows.length; i++) {
        rows[i].style.display = "none";
    }

    // Hiển thị các hàng của trang hiện tại
    for (var j = start; j < end && j < rows.length; j++) {
        rows[j].style.display = "table-row";
    }
}

function createPagination() {
    // Tạo các nút phân trang
    var pagination = document.getElementById("pagination");
    var html = "";

    for (var page = 1; page <= totalPages; page++) {
        html += '<button type="button" class="btn btn-light mx-1" onclick="showPage(' + page + ')">' + page + '</button>';
    }

    pagination.innerHTML = html;
}

createPagination();