const homeBtn = document.getElementById('home');
const trashBtn = document.getElementById('trash');

const contextPath = homeBtn.getAttribute('data-context-path');

homeBtn.addEventListener('click', ()=> {
	window.location.href = contextPath + '/home';
});

trashBtn.addEventListener('click', ()=> {
	window.location.href = contextPath + '/trash';
});
