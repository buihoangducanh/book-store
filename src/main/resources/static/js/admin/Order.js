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