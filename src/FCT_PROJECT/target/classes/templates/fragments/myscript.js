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
	var elems = document.querySelectorAll('select');
	var instances = M.FormSelect.init(elems, options);
});

// Or with jQuery

$(document).ready(function() {
	$('select').formSelect();
});

//Carrousel
 var instance = M.Carousel.init({
    fullWidth: true
  });

  // Or with jQuery

  $('.carousel.carousel-slider').carousel({
    fullWidth: true
  });
     