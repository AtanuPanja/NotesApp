const menuBtn = document.querySelector('.menu-btn');
const menuItemList = document.querySelector('.menu-item-list')
const header = document.querySelector('header');
const menuIcon = document.querySelector('.menu-btn > .menu-icon');
const menuLabel = document.querySelector('.menu-btn > .menu-label');

const homeBtn = document.getElementById('home');
const trashBtn = document.getElementById('trash');

const contextPath = homeBtn.getAttribute('data-context-path');

homeBtn.addEventListener('click', ()=> {
	window.location.href = contextPath + '/home';
});

trashBtn.addEventListener('click', ()=> {
	window.location.href = contextPath + '/trash';
});


// menu button click, results in opening the menu, where home and bin options are shown
menuBtn.addEventListener('click', (event)=> {
	const target = event.target;
	console.log(target);
	console.log(target.contains(menuIcon));
	console.log(target.contains(menuLabel));
	// when the inner menu icon or menu label are clicked, the click doesn't work
	// for that we use event.stopPropagation
	event.stopPropagation();
	menuItemList.classList.toggle("hide");
	menuBtn.classList.toggle("active");
	
});

// clicking outside the menuBtn and menuItemList elements
document.addEventListener('click', (event)=> {
	const target = event.target;
	if ((!target.contains(menuBtn) && !target.contains(menuItemList)) || target.contains(header)) {
		if (menuBtn.classList.contains("active"))
			menuBtn.classList.remove("active");
	
		if (!menuItemList.classList.contains("hide"))
			menuItemList.classList.add("hide");
	}
});
