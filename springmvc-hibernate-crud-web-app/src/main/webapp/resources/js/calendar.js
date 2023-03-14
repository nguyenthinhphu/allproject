$(function() {

	$('#birthdate').datepicker({
		dateFormat : 'dd/mm/yy',
		changeYear : true,
		changeMonth : true,
		beforeShow : function(input, inst) {
			var rect = input.getBoundingClientRect();
			setTimeout(function() {
				inst.dpDiv.css({
					top : rect.top + 15,
					left : rect.left + 190
				});
			}, 0);
		}
	});
	
	$('#insuranceStartDate').datepicker({
		dateFormat : 'dd/mm/yy',
		changeYear : true,
		changeMonth : true,
		beforeShow : function(input, inst) {
			var rect = input.getBoundingClientRect();
			setTimeout(function() {
				inst.dpDiv.css({
					top : rect.top + 15,
					left : rect.left + 190
				});
			}, 0);
		}
	});
	
	$('#insuranceEndDate').datepicker({
		dateFormat : 'dd/mm/yy',
		changeYear : true,
		changeMonth : true,
		beforeShow : function(input, inst) {
			var rect = input.getBoundingClientRect();
			setTimeout(function() {
				inst.dpDiv.css({
					top : rect.top + 15,
					left : rect.left + 190
				});
			}, 0);
		}
	});
});