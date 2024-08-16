const textareas = document.querySelectorAll('textarea');
const saveButton = document.getElementById('save');

textareas.forEach(element => {

	element.style.height = 'auto';  // Reset height to auto to shrink if needed
	element.style.height = element.scrollHeight + 'px';  // Set height to the scroll height

	element.addEventListener('input', function() {
		this.style.height = 'auto';  // Reset height to auto to shrink if needed
		this.style.height = this.scrollHeight + 'px';  // Set height to the scroll height

		saveButton.style.display = 'block';
	});
});