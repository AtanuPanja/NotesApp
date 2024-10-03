// save the darkmode to localStorage
// this will share the theme between multiple pages
// and keep the theme intact on reload
const bodyElement = document.querySelector("body");
const toggleDarkThemeBtn = document.querySelector("#toggle-dark-theme");

function changeMode(darkMode) {
	if (darkMode === "enabled") {
		bodyElement.classList.add("darkmode");
		if (toggleDarkThemeBtn) {
			toggleDarkThemeBtn.innerHTML = "Disable Dark Theme";
		}
	}
	else if (darkMode === "disabled") {
		bodyElement.classList.remove("darkmode");
		if (toggleDarkThemeBtn) {
			toggleDarkThemeBtn.innerHTML = "Enable Dark Theme";
		}
		
	}
}

// get the mode whenever the page loads
changeMode(localStorage.getItem("darkMode"));

// check the user system preference (prefers-color-scheme: dark)
// initially set the theme to dark for dark preference
// accordingly set light for light preference
// check only if the darkMode attribute is not set in localStorage
if (localStorage.getItem("darkMode") === null) {
	const osDefaultDark = window.matchMedia("(prefers-color-scheme: dark)").matches;

	if (osDefaultDark) {
		localStorage.setItem("darkMode", "enabled");
		changeMode(localStorage.getItem("darkMode"));
	}
	else {
		localStorage.setItem("darkMode", "disabled");
		changeMode(localStorage.getItem("darkMode"));
	}
}


// then, whenever the button is clicked the theme will be toggled.

// add and edit form pages, don't contain toggle theme button, so it returns null
if (toggleDarkThemeBtn) {
	toggleDarkThemeBtn.addEventListener("click", ()=> {
		
		if (localStorage.getItem("darkMode") === "enabled") {
			localStorage.setItem("darkMode", "disabled");
		}
		else if (localStorage.getItem("darkMode") === "disabled")  {
			localStorage.setItem("darkMode", "enabled");
		}
		
		changeMode(localStorage.getItem("darkMode"));
	});
}
