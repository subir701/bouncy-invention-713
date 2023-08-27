let mainHtml = document.getElementById("body");

function fetchTicketData() {
    const jwtToken = getCookie();
    const apiUrl = 'http://localhost:8888/admin/ticket/getAllTicket';

    fetch(apiUrl, {
        headers: {
            "Authorization": `Bearer ${jwtToken}`
        }
    })
    .then(res => {
        if (!res.ok) {
            throw new Error(`Http error! Status: ${res.status}`);
        }
        return res.json();
    })
    .then(data => {
        console.log(data); // Log the data array for debugging
        render(data); // Pass the array to the render function
    })
    .catch(error => {
        console.error('Error fetching ticket data:', error);
    });
}

function render(tickets) {
    const cards = tickets.map(ticket => getCard(
        ticket.customer.username,
        ticket.customer.address,
        ticket.customer.mobileNumber,
        ticket.customer.email,
        ticket.activity.activityName,
        ticket.price,
        ticket.visitDate,
        ticket.createdOn,
        ticket.personCount
    ));

    mainHtml.innerHTML = cards.join(""); // Join the array of cards into a single string
}

function getCard(username, address, mobile, email, activity, price, visit, createdOn , personCount) {
    return `
    <tr>
        <td>${username}</td>
        <td>${address}</td>
        <td>${mobile}</td>
        <td>${email}</td>
        <td>${activity}</td>
        <td>${price}</td>
        <td>${visit}</td>
        <td>${createdOn}</td>
        <td>${personCount}</td>
    </tr>`;
}

function getCookie() {
    const value = document.cookie;
    const parts = value.split("=");

    return parts[1];
}

fetchTicketData();