const baseUrl = 'http://localhost:8080';
const restUrl = 'http://localhost:8080/api';
const csrfToken = document.querySelector('[name=_csrf_token]').content;

async function getResponses() {
    const id = document.getElementsByClassName("id")[0].innerHTML;
    const response = await fetch(restUrl + '/subjects/' + id + "/responses");
    if (response.ok) {
        return await response.json();
    } else {
        alert("Error" + response.error());
    }
}

async function loadResponses(){
    const responses = await getResponses();
    responses.forEach(response => {

        !document.getElementById(response.id)&&
        addResponseElement(createResponseElement(response));
    })
}

function createResponseElement(response) {
    let rspElement = document.createElement('div');

    rspElement.innerHTML=
        `
            <div class="row" id="${response.id}">
                <div class="col">${response.author.username}</div>
                <div class="col">
                    <div class="row">
                        <div class="col">${response.dateTime}</div>
                        <div class="col">${response.text}</div>
                    </div>
                </div>
            </div>
        `;
    return rspElement.children[0];
}

function addResponseElement(msgElement) {
    document.getElementsByClassName('responses')[0].append(msgElement);
}

async function respond() {
    const responseForm = document.getElementsByClassName("responseForm")[0];
    await responseForm.addEventListener("submit", async function(e){
        e.preventDefault();
        const data = new FormData(responseForm);
        const responseJSON = JSON.stringify(Object.fromEntries(data));
        console.log(responseJSON)
        await fetch(restUrl+'/responses/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            },
            body: responseJSON,
            cache: 'no-cache',
            mode : 'cors'
        });
        loadResponses();
    });

}

loadResponses();

// setInterval(async function() {
//     await loadResponses();} , 1000);

respond();


const getCurrentPage = () => {
    const loc = (typeof window.location !== 'string') ? window.location.search : window.location;
    const index = loc.indexOf('page=');
    return index === -1 ? 1 : parseInt(loc[index + 5]) + 1;
};

(function loadResponsesPageable() {

    async function getResponses(page, size) {
        const id = document.getElementsByClassName("id")[0].innerHTML;
        const response = await fetch(restUrl + '/subjects/' + id + `/responses?page=${page}&size=${size}`);
        if (response.ok) {
            return await response.json();
        } else {
            alert("Error" + response.error());
        }
    }

    const loadNextResponsesGenerator = (loadNextElement, page) => {
        return async (event) => {
            event.preventDefault();
            event.stopPropagation();

            const defaultPageSize = loadNextElement.getAttribute('data-default-page-size');
            const responses = await getResponses(page, defaultPageSize);

            loadNextElement.hidden = responses.length === 0;
            if (responses.length === 0) {
                return;
            }
            responses.forEach(response => {
                addResponseElement(createResponseElement(response));
            });


            loadNextElement.addEventListener('click', loadNextResponsesGenerator(loadNextElement, page + 1), {once: true});
            window.scrollTo(0, document.body.scrollHeight);
        };
    };
    document.getElementById('loadPrev').hidden = true;
    const loadNextElement = document.getElementById('loadNext');
    if (loadNextElement !== null) {
        loadNextElement.innerText = "Load more";
        loadNextElement.addEventListener('click', loadNextResponsesGenerator(loadNextElement, getCurrentPage()), {once: true});
    }

})();