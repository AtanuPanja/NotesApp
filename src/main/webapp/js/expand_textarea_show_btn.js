const textareas = document.querySelectorAll('textarea');
const saveButton = document.getElementById('save');

function handleTextAreas(textareas) {
	textareas.forEach(element => {
		
		adjustTextArea(element);
		
		element.addEventListener('input', ()=> {
			adjustTextArea(element);
			saveButton.style.display = 'block';
		});
	});
}

function adjustTextArea(textarea) {
	textarea.style.height = 'auto';  // Reset height to auto to shrink if needed
	textarea.style.height = textarea.scrollHeight + 'px';  // Set height to the scroll height
}

// adjust all textareas either by content size or on input, onload
handleTextAreas(textareas);

// do the same adjustment with the textareas, when resized
window.addEventListener('resize', ()=> handleTextAreas(textareas));