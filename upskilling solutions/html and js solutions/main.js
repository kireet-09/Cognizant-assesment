// ======================================
// Exercise 1 - JS Setup
// ======================================

console.log("Welcome to the Community Portal");

window.addEventListener("load", () => {
    alert("Community Portal Loaded Successfully");
});

// ======================================
// Exercise 2 - Data Types & Operators
// ======================================

const eventName = "Music Festival";
const eventDate = "2026-08-20";

let availableSeats = 50;

console.log(
`Event: ${eventName}
Date: ${eventDate}
Seats: ${availableSeats}`
);

// ======================================
// Exercise 3 - Conditionals & Loops
// ======================================

const events = [

{
id:1,
name:"Music Festival",
category:"Music",
date:"2026-08-20",
seats:50
},

{
id:2,
name:"Marathon",
category:"Sports",
date:"2026-09-15",
seats:0
},

{
id:3,
name:"Food Fair",
category:"Food",
date:"2026-10-10",
seats:30
}

];

events.forEach(event => {

if(event.seats > 0){

console.log("Available:",event.name);

}

});

function registerSeat(eventObj){

try{

if(eventObj.seats <= 0){

throw new Error("No seats available");

}

eventObj.seats--;

console.log("Registered Successfully");

}
catch(error){

console.error(error.message);

}

}

// ======================================
// Exercise 4 - Functions & Closures
// ======================================

function addEvent(event){

events.push(event);

}

function registerUser(eventId){

const event = events.find(e => e.id === eventId);

if(event){

registerSeat(event);

}

}

function filterEventsByCategory(category){

return events.filter(
e => e.category === category
);

}

// Closure

function createRegistrationCounter(){

let total = 0;

return function(){

total++;

return total;

}

}

const musicCounter =
createRegistrationCounter();

// ======================================
// Exercise 5 - Objects & Prototypes
// ======================================

class Event{

constructor(
name,
category,
date,
seats
){

this.name=name;
this.category=category;
this.date=date;
this.seats=seats;

}

}

Event.prototype.checkAvailability =
function(){

return this.seats > 0;

};

const workshop =
new Event(
"Baking Workshop",
"Workshop",
"2026-11-01",
20
);

console.log(
Object.entries(workshop)
);

// ======================================
// Exercise 6 - Arrays & Methods
// ======================================

events.push({

id:4,
name:"Baking Workshop",
category:"Workshop",
date:"2026-11-01",
seats:20

});

const musicEvents =
events.filter(
e => e.category === "Music"
);

const eventCards =
events.map(
e => `Event: ${e.name}`
);

console.log(eventCards);

// ======================================
// Exercise 7 - DOM Manipulation
// ======================================

const eventContainer =
document.querySelector("#eventsContainer");

function renderEvents(){

if(!eventContainer) return;

eventContainer.innerHTML="";

events.forEach(event => {

const card =
document.createElement("div");

card.className="event-card";

card.innerHTML=`

<h3>${event.name}</h3>

<p>Category:
${event.category}</p>

<p>Seats:
${event.seats}</p>

<button onclick=
"registerForEvent(${event.id})">

Register

</button>

`;

eventContainer.appendChild(card);

});

}

function registerForEvent(id){

const event =
events.find(e => e.id === id);

if(event && event.seats > 0){

event.seats--;

renderEvents();

}

}

// ======================================
// Exercise 8 - Event Handling
// ======================================

const categoryFilter =
document.querySelector("#categoryFilter");

if(categoryFilter){

categoryFilter.onchange = function(){

const filtered =
filterEventsByCategory(
this.value
);

console.log(filtered);

};

}

const searchBox =
document.querySelector("#searchBox");

if(searchBox){

searchBox.addEventListener(
"keydown",

e => {

console.log(
"Searching:",
e.target.value
);

});

}

// ======================================
// Exercise 9 - Async JS
// ======================================

function fetchEventsThen(){

fetch(
"https://jsonplaceholder.typicode.com/posts"
)

.then(res => res.json())

.then(data => {

console.log(
"Fetched Events",
data.slice(0,5)
);

})

.catch(err => {

console.error(err);

});

}

async function fetchEventsAsync(){

try{

showLoading();

const response =
await fetch(
"https://jsonplaceholder.typicode.com/posts"
);

const data =
await response.json();

console.log(
data.slice(0,5)
);

}
catch(error){

console.error(error);

}
finally{

hideLoading();

}

}

// ======================================
// Exercise 10 - Modern JS
// ======================================

function createEvent(
name="Untitled Event",
category="General"
){

return {
name,
category
};

}

const firstEvent =
events[0];

const {

name,
category,
date

} = firstEvent;

console.log(
name,
category,
date
);

const clonedEvents =
[...events];

// ======================================
// Exercise 11 - Forms
// ======================================

const registrationForm =
document.querySelector("#registrationForm");

if(registrationForm){

registrationForm.addEventListener(
"submit",

function(event){

event.preventDefault();

const name =
this.elements["name"].value;

const email =
this.elements["email"].value;

const selectedEvent =
this.elements["event"].value;

let errors=[];

if(name==="")
errors.push("Name Required");

if(email==="")
errors.push("Email Required");

const errorBox =
document.querySelector("#formErrors");

if(errors.length > 0){

errorBox.innerHTML=
errors.join("<br>");

return;

}

alert(
`${name}
registered for
${selectedEvent}`
);

});

}

// ======================================
// Exercise 12 - AJAX & Fetch API
// ======================================

async function submitRegistration(data){

try{

const response =
await fetch(
"https://jsonplaceholder.typicode.com/posts",
{

method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:
JSON.stringify(data)

}

);

const result =
await response.json();

setTimeout(()=>{

alert(
"Registration Submitted"
);

},1500);

console.log(result);

}
catch(error){

alert("Submission Failed");

}

}

// ======================================
// Exercise 13 - Debugging
// ======================================

console.log(
"Debugging Enabled"
);

function debugRegistration(user){

console.log(
"User:",
user
);

debugger;

return true;

}

// ======================================
// Exercise 14 - jQuery
// ======================================

/*

$(document).ready(function(){

$("#registerBtn").click(function(){

$(".event-card").fadeOut();

});

});

Benefits of React/Vue:

1. Component Reusability
2. Better State Management
3. Faster UI Updates
4. Large Scale Application Support

*/

// ======================================
// Local Storage
// ======================================

function savePreference(){

const category =
document.querySelector(
"#categoryFilter"
)?.value;

localStorage.setItem(
"preferredCategory",
category
);

}

function loadPreference(){

const saved =
localStorage.getItem(
"preferredCategory"
);

if(saved &&
document.querySelector(
"#categoryFilter"
)){

document.querySelector(
"#categoryFilter"
).value=saved;

}

}

function clearPreferences(){

localStorage.clear();
sessionStorage.clear();

alert(
"Preferences Cleared"
);

}

// ======================================
// Loading Spinner Helpers
// ======================================

function showLoading(){

const spinner =
document.querySelector("#loading");

if(spinner)
spinner.style.display="block";

}

function hideLoading(){

const spinner =
document.querySelector("#loading");

if(spinner)
spinner.style.display="none";

}

// ======================================
// Initialize
// ======================================

loadPreference();

renderEvents();