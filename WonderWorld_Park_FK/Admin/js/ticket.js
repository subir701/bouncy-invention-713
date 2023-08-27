let activities = [];
let mainHtml = document.getElementById("body");

function fetchTicketData() {
    // Replace this with your actual API URL
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
            

            
        })
        .catch(error => {
            console.error('Error fetching ticket data:', error);
        });
}

function getCookie() {
    const value =  document.cookie;
    
    const parts = value.split("=");
    
    return parts[1];
}

fetchTicketData();