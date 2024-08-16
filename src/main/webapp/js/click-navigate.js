const addNewNote = document.getElementById('add-new-note');
const notes = document.getElementsByClassName('note-content');

const contextPath = addNewNote.getAttribute('data-context-path');

addNewNote.addEventListener('click', ()=> {
	window.location.href = contextPath + '/add-note';
});

Array.from(notes).forEach(element => {
	
	element.addEventListener('click', (event)=> {
		const target = event.target;
		// target.closest returns the element closest to the target
		// i.e., closest to the element which is clicked upon
		// It was needed because nested h3 and p element when clicked,
		// had the id as null. I can't set the same id as div.card
		// on its nested h3 and p elements.
		// I had an option to set data-id on all three elements, which
		// allows duplication. But with the closest method approach, it is
		// not needed.
		const card = target.closest('.card');
		if (card) {
			const id = card.getAttribute('id');
			console.log('clicked on note#'+id);
			window.location.href = contextPath + '/edit-note/'+ id;
		}
		
	});
});