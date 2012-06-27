
var init = function()
{
	var $feedbackCombo = $("select[name='feedback'");
	$feedbackCombo.change( comboBoxHandler );
};

var comboBoxHandler = function()
{
	var $this = $(this);
	var $docId = $this.attr( "docId" );
	var $feedbackValue = $this.val();
	
	FeedbackUpdater.updateFeedback( $docId, $feedbackValue, dwrCallback );
};

var dwrCallback = function( returnedValue )
{
	alert(returnedValue);
};

// wait for the DOM to be loaded 
$(document).ready(function() { 
	init();
}); 
