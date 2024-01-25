function formToObject(formSelector) {
	const form = document.querySelector(formSelector);

	/* Lấy dữ liệu input */
	const obj = {};

	const inputs = form.querySelectorAll('input');
	for (const input of inputs) {
		const name = input.getAttribute('name');
		const value = input.value;
		obj[name] = value;
	}

	const selects = form.querySelectorAll("select")
	for (const select of selects) {
		const name = select.getAttribute('name');
		const value = select.value;
		obj[name] = value;
	}
	return obj;
}

function post(path, body) {
	var myHeaders = new Headers();
	myHeaders.append('Content-Type', 'application/json');
	return fetch(path, {
		method: 'POST',
		body: JSON.stringify(body),
		headers: myHeaders
	}).then(response => response.json())
}

function postProductFormData(path,formData){
	var myHeaders = new Headers();
	return fetch(path, {
		method:'POST',
		body: formData,
		headers: myHeaders
	}).then(response => response.json());
}
function deleteImage(path) {
    var myHeaders = new Headers();
    return fetch(path, {
        method: 'DELETE',
        headers: myHeaders
    });
}

function put(path, body) {
	var myHeaders = new Headers();
	myHeaders.append('Content-Type', 'application/json');
	return fetch(path, {
		method: 'PUT',
		body: JSON.stringify(body),
		headers: myHeaders
	}).then(response => response.json())
}
function pat(path, body={}) {
	var myHeaders = new Headers();
	myHeaders.append('Content-Type', 'application/json');
	return fetch(path, {
		method: 'PATCH',
		body: JSON.stringify(body),
		headers: myHeaders
	}).then(response => response.json())
}

function showPagination({
	totalItems,
	limit,
	currentPage,
	onPageClick
	}) {
		_$("#demo").pagination({
			items: totalItems,
			itemsOnPage: limit,
			currentPage,
			prevText: "&laquo;",
			nextText: "&raquo;",
			onPageClick: onPageClick
		});
}
function showPaginationInUser({
							totalItems,
							limit,
							currentPage,
							onPageClick
						}) {
	_$("#pagi").pagination({
		items: totalItems,
		itemsOnPage: limit,
		currentPage,
		prevText: "&laquo;",
		nextText: "&raquo;",
		onPageClick: onPageClick
	});
}
function callDelete(path, body = {}) {
	var myHeaders = new Headers();
	myHeaders.append('Content-Type', 'application/json');
	return fetch(path, {
		method: 'DELETE',
		body: JSON.stringify(body),
		headers: myHeaders
	})
}

function get(path, params = {}) {
	var myHeaders = new Headers();
	myHeaders.append('Content-Type', 'application/json');
	const paramUrl = new URLSearchParams(params);
	return fetch(`${path}?${paramUrl}`, {
		method: 'GET',
		headers: myHeaders,
	}).then(response => response.json())
}

