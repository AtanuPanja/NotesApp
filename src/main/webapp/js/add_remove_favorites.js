// contextPath is already defined in menu.js

document.querySelectorAll('.favoritesBtn>img').forEach(button => {
	button.addEventListener('click', (event) => {
		const target = event.target;
		const id = target.closest('.card').getAttribute("id");
		const src = target.getAttribute("src");
		
		if (src === "images/add-favorite_24px.png") {
			
			fetch(`${contextPath}/add-to-favorites/${id}`)
			.then(()=> {
				window.location.reload();
			});
		}
		else {
			fetch(`${contextPath}/remove-from-favorites/${id}`)
			.then(()=> {
				window.location.reload();
			});
		}
		
		
		
	});
});