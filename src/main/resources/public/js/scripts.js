$(document).ready(function() {
	$(".btn-403 a[class='btn btn-secondary']").click(function() {
		parent.history.back();
		return false;
	})
});

$(function() {
	$('[data-toggle="tooltip"]').tooltip()
});

$('#close-button').click(function() {
	var $target = $(this).parents('div');
	$target.fadeOut("slow");
});
