let activities = [];
let mainHtml = document.getElementById("act");

function fetchActivity() {
    const jwtToken = getCookie(); // Corrected JWT token retrieval getCookie("jwtToken")

    fetch('http://localhost:8888/admin/activity/all?pageNumber=0&pageSize=6', {
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
        

        let probObj = data.map(item => ({
            activityId:item.activityId,
            activityName: item.activityName,
            capacity: item.personCapacity,
            distance: item.distance,
            price: item.price,
            url: item.url,
            isDeleted:item.isDeleted
        }));

        activities = probObj;
        render(activities);
    })
    .catch(error => {
        console.error("Fetch error:", error);
    });
}

function render(card) {
    let temp;
    temp = card.map(item => {
        
        return getCard(
            item.activityName,
            item.capacity,
            item.distance,
            item.price,
            item.url,
            item.isDeleted,
            item.activityId
        );
    });

    mainHtml.innerHTML = temp.join(" ");
}

function getCard(activityName, capacity, distance, price, url,isDeleted,activityId) {
    let card = `
    <tr>
        <td>${activityName}</td>
        <td>${capacity}</td>
        <td>${distance}</td>
        <td>${price}</td>
        <td>${url}</td>
        <td>${isDeleted}</td>
        <td><button onclick="deleteActivity(${activityId})">Delete</button></td>
        <td><button onclick="updateActivity(${activityId})">Update</button></td>
    </tr>`;

    return card;
}

function deleteActivity(activityId) {
    const jwtToken =getCookie();
    fetch(`http://localhost:8888/admin/activity/delete/${activityId}`, {
        method: 'DELETE',
        headers: {
            "authorization": `Bearer ${jwtToken}` // Replace jwtToken with your actual JWT token
        }
    })
    
    .then(response => {
       
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        console.log(`Activity with ID ${activityId} deleted successfully.`);
        fetchActivity();
    })
    .catch(error => {
        console.error("Delete error:", error);
    });
}

function updateActivity(activityId) {
    sessionStorage.clear();
    sessionStorage.setItem('id', activityId);
    window.location.href= "updateActivity.html";
}

function getCookie() {
    const value =  document.cookie;
    
    const parts = value.split("=");
    
    return parts[1];
}

fetchActivity();