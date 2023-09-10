$(document).ready(function() {
    const addedProducts = [];

    // Xử lý khi ấn nút "Thêm" để đưa sản phẩm vào bảng "Danh sách sản phẩm trong đơn hàng"
    $(".add-product").click(function() {
        const productId = $(this).data("product");
        // if (addedProducts.includes(productId)) {
        //     alert("Sản phẩm đã có trong đơn hàng!");
        //     return;
        // }

        const row = $(this).closest("tr");
        const productName = row.find("td:eq(0)").text();
        const author = row.find("td:eq(1)").text();
        const price = parseFloat(row.find("td:eq(2)").text());
        const quantity = 1;
        const totalPrice = (price * quantity).toFixed(2);

        const newRow = `
<tr>
<td>${productName}</td>
<td>${author}</td>
<td>
<input type="number" class="form-control quantity-input" value="${quantity}">
</td>
<td>${price}</td>
<td class="total-price">${totalPrice}</td>
<td>
<button type="button" class="btn btn-danger btn-sm delete-product" data-product="${productId}">Xóa</button>
</td>
</tr>
`;

        $("#selectedProducts").append(newRow);
        addedProducts.push(productId);
        updateTotalAmount();
    });

    // Xử lý sự kiện khi số lượng sản phẩm thay đổi
    $(document).on("input", ".quantity-input", function() {
        const quantity = $(this).val();
        const price = parseFloat($(this).closest("tr").find("td:eq(3)").text());
        const totalPrice = (price * quantity).toFixed(2);
        $(this).closest("tr").find(".total-price").text(totalPrice);
        updateTotalAmount();
    });

    // Xử lý sự kiện khi ấn nút xóa
    $(document).on("click", ".delete-product", function() {
        const productId = $(this).data("product");
        const index = addedProducts.indexOf(productId);
        if (index !== -1) {
            addedProducts.splice(index, 1);
        }
        $(this).closest("tr").remove();
        updateTotalAmount();
    });

    // Xử lý tìm kiếm sản phẩm
    $("#searchProduct").on("keyup", function() {
        const searchText = $(this).val().toLowerCase();
        $("tbody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(searchText) > -1);
        });
    });

    // Cập nhật tổng tiền phải trả
    function updateTotalAmount() {
        let totalAmount = 0;
        $(".total-price").each(function() {
            totalAmount += parseFloat($(this).text());
        });
        $("#totalAmount").val(totalAmount.toFixed(2));
    }
});


//phân trang
// Số sản phẩm trên mỗi trang
var itemsPerPage = 3;

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