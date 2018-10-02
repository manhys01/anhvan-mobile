$(document).ready(function() {
	$(".remove-btn").click(function(e) {
		var id = $(this).data("id");
		var req = "/admin/brand/".concat(id);
		$.ajax({
			url : req,
			type : 'DELETE',
			success : function(result) {
				if (result) {
					alert("Xóa thành công!");
					location.reload();
				} else
					alert("Xóa thất bại!");
			}
		});
	});
});