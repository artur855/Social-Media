$(document).ready(function() {
	$(".btn-403 a[class='btn btn-secondary']").click(function() {
		parent.history.back();
		return false;
	})
});

$(document).one('focus.autoExpand', 'textarea.autoExpand', function() {
	var savedValue = this.value;
	this.value = '';
	this.baseScrollHeight = this.scrollHeight;
	this.value = savedValue;
}).on('input.autoExpand', 'textarea.autoExpand', function() {
	var minRows = this.getAttribute('data-min-rows') | 0, rows;
	this.rows = minRows;
	rows = Math.ceil((this.scrollHeight - this.baseScrollHeight) / 20);
	this.rows = minRows + rows;
});