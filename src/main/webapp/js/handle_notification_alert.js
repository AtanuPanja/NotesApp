const notificationElement = document.querySelector(".notification");
const closeNotificationBtn = document.querySelector(".notification>img");
if (notificationElement != null) {
	notificationElement.style.position = "absolute";
	notificationElement.style.display = "flex";
	notificationElement.style.backgroundColor = "#c0c0c0"; // grey
	notificationElement.style.color = "black";
	notificationElement.style.border = "2px solid black";
	
	// const text = "String";
	const text = (notificationElement.children[0].innerText);
	const lowerCaseText = text.toLowerCase();
	if (lowerCaseText.includes("created") || lowerCaseText.includes("updated") || lowerCaseText.includes("restored")) {
		notificationElement.style.backgroundColor = "#2FB543"; // green
		notificationElement.style.border = "2px solid #216E0C"; // dark green
		notificationElement.style.color = "white";
	}
	else if (lowerCaseText.includes("moved")) {
		notificationElement.style.backgroundColor = "#ECC722"; // yellow
		notificationElement.style.border = "2px solid #CF7F08"; // orange
	}
	else if (lowerCaseText.includes("emptied") || lowerCaseText.includes("deleted")) {
		notificationElement.style.backgroundColor = "#CF3434"; // light red
		notificationElement.style.border = "2px solid #630202"; // dark red
		notificationElement.style.color = "white";
	}
	else {
		notificationElement.children[0].innerText = "Error: " + text;
		notificationElement.style.backgroundColor = "#FAD4D4"; // lighter red // for errors
		notificationElement.style.border = "2px solid red";
		notificationElement.style.color = "red";
		notificationElement.style.position = "unset";
	}

	closeNotificationBtn.addEventListener("click", ()=> {
		notificationElement.style.display = "none";
	});
}
