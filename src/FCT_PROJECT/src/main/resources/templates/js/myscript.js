//Calendar

document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.datepicker');
	var instances = M.Datepicker.init(elems, options);
});

// Or with jQuery

$(document).ready(function() {
	$('.datepicker').datepicker();
});


//Select
document.addEventListener('DOMContentLoaded', function() {
	var elems1 = document.querySelectorAll('select');
	var instances1 = M.FormSelect.init(elems1, options);
});

// Or with jQuery

$(document).ready(function() {
	$('select').formSelect();
});

//Carrousel

document.addEventListener('DOMContentLoaded', function() {
    var elems2 = document.querySelectorAll('.carousel');
    var instances2 = M.Carousel.init(elems2);
  });

