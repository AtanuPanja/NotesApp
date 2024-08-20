const addNewNote = document.getElementById('add-new-note');
const notes = document.getElementsByClassName('noteContent');

// contextPath has been declared in menu.js
addNewNote.addEventListener('click', ()=> {
	window.location.href = contextPath + '/add-note';
});

Array.from(notes).forEach(element => {
	
	element.addEventListener('click', (event)=> {
		const target = event.target;
		// get the id from the closest element with .card (closest will 
		// be the parent)
		const card = target.closest('.card');
		if (card) {
			const id = card.getAttribute('id');
			window.location.href = contextPath + '/edit-note/'+ id;
		}
		
	});
});