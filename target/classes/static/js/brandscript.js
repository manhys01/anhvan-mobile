$(document).ready(function() {
	$(".remove-btn").click(function() {
		$("#removeBrandModal").modal("show");
		var uri = "/admin/brands/remove/";
		var message = "Bạn có muốn xóa sản phẩm"
		
		var id = $(this).data("id");
		var name = $(this).data("name");
		
		var req = uri.concat(id);
		$("#message").attr("data-request", req);
		$("#message").text("Bạn có muốn xóa thương hiệu [".concat(name, "] không?"));
	});
	$("#yesBtn").click(function(e) {
		$("#removeBrandModal").modal("hide");
		var req = $("#message").attr("data-request");
		$.post(req, function(data) {
			if (!data) {
				alert("Xóa thất bại!");
			} else {
				location.reload();
			}
		});
	});
});