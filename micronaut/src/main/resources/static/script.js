$(function () {
	$.ajax({
		url: '/fruits',
		method: 'GET',
	}).done(function (data, txtStatus, r) {
		$("code").html(data);
	})
});
