 // Dữ liệu thống kê doanh thu
 const revenueData = {
     labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
     datasets: [{
         label: "Doanh thu",
         data: [1000, 1200, 800, 1500, 1300, 1600, 2000, 9000, 1456, 2900, 1000, 23000],
         backgroundColor: "rgba(75, 192, 192, 0.2)",
         borderColor: "rgba(75, 192, 192, 1)",
         borderWidth: 1
     }]
 };

 // Cấu hình biểu đồ
 const chartConfig = {
     type: "bar",
     data: revenueData,
     options: {
         scales: {
             y: {
                 beginAtZero: true
             }
         }
     }
 };

 // Vẽ biểu đồ
 const ctx = document.getElementById("myChart").getContext("2d");
 new Chart(ctx, chartConfig);

 // Dữ liệu thống kê giá sản phẩm
 const priceData = {
     labels: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D"],
     datasets: [{
         label: "Giá sản phẩm",
         data: [1000, 1200, 800, 1500],
         backgroundColor: "rgba(75, 192, 192, 0.2)",
         borderColor: "rgba(75, 192, 192, 1)",
         borderWidth: 1
     }]
 };

 // Cấu hình biểu đồ giá sản phẩm
 const priceChartConfig = {
     type: "bar",
     data: priceData,
     options: {
         scales: {
             y: {
                 beginAtZero: true
             }
         }
     }
 };

 // Vẽ biểu đồ giá sản phẩm
 const priceCtx = document.getElementById("myChartPrice").getContext("2d");
 new Chart(priceCtx, priceChartConfig);

 // Dữ liệu thống kê tỷ lệ sản phẩm
 const productData = {
     labels: ["Sản phẩm A", "Sản phẩm B", "Sản phẩm C", "Sản phẩm D"],
     datasets: [{
         data: [25, 35, 20, 20],
         backgroundColor: ["#4e73df", "#1cc88a", "#f6c23e", "#36b9cc"]
     }]
 };

 // Cấu hình biểu đồ tỷ lệ sản phẩm
 const productChartConfig = {
     type: "pie",
     data: productData
 };

 // Vẽ biểu đồ tỷ lệ sản phẩm
 const productCtx = document.getElementById("myChartSanPham").getContext("2d");
 new Chart(productCtx, productChartConfig);