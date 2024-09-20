// contextPath is already defined in menu.js

document.querySelectorAll('.noteDelete').forEach(button => {
	button.addEventListener('click', (event) => {
		const target = event.target;
		const id = target.closest('.card').getAttribute("id");
		const action = button.getAttribute("data-action");
		
		// action: move-to-trash, delete-note, restore-note
		fetch(`${contextPath}/${action}/${id}`,
			{method: 'POST'}
		).then(()=> {
			window.location.reload();
		});
		;
	});
});

const emptyBinBtn = document.querySelector("#emptyBin");
// empty bin action is different and part of trash.jsp
if (emptyBinBtn != null) {
	emptyBinBtn.addEventListener("click", ()=> {
		fetch(`${contextPath}/empty-trash`,
			{'method': 'POST'}
		)
		.then(()=> {
			window.location.reload();
		});
	});
}
