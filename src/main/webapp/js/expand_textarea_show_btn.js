const textareas = document.querySelectorAll('textarea');
const saveButton = document.getElementById('save');


function updateTextAreas(textareas) {
	textareas.forEach(textarea => {
		adjustTextArea(textarea);
		
		textarea.addEventListener('input', (event)=> {
			
			adjustTextArea(textarea);
			
			saveButton.style.display = 'block';
		});
		
	});
}

function adjustTextArea(textarea) {
	textarea.style.height = 'auto';  // Reset height to auto to shrink if needed
	textarea.style.height = textarea.scrollHeight + 'px';  // Set height to the scroll height
}

// adjust all textareas either by content size or on input, onload
updateTextAreas(textareas);

// do the same adjustment with the textareas, when resized
window.addEventListener('resize', ()=> updateTextAreas(textareas));