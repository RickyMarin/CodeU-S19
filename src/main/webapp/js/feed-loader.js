/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 // Fetch messages and add them to the page.
 function fetchMessages(){
   const url = '/feed';
   fetch(url).then((response) => {
     return response.json();
   }).then((messages) => {
     const messageContainer = document.getElementById('message-container');
     if(messages.length == 0){
      messageContainer.innerHTML = '<p>There are no posts yet.</p>';
     }
     else{
      messageContainer.innerHTML = '';
     }
     messages.forEach((message) => {
      const messageDiv = buildMessageDiv(message);
      messageContainer.appendChild(messageDiv);
     });
   });
 }

 function buildMessageDiv(message){
  const usernameDiv = document.createElement('div');
  usernameDiv.classList.add("left-align");
  usernameDiv.appendChild(document.createTextNode(message.user.split('@', 1)[0] + ' is at ' + message.location + ' - '));

  /*
  * Div for time and sentiment score
  */
  const timeSentDiv = document.createElement('div');
  const date = new Date(message.timestamp);
  timeSentDiv.classList.add('right-align');
  timeSentDiv.appendChild(document.createTextNode(date.getMonth() + ' ' + date.getDate()
   + ', ' + date.getYear() ' [Rate: ' + message.sentimentScore + ']'));

  const headerDiv = document.createElement('div');
  headerDiv.classList.add('message-header');
  headerDiv.appendChild(usernameDiv);
  headerDiv.appendChild(timeSentDiv);

  const bodyDiv = document.createElement('div');
  bodyDiv.classList.add('message-body');
  bodyDiv.appendChild(document.createTextNode(message.text));

  const messageDiv = document.createElement('div');
  messageDiv.classList.add("message-div");
  messageDiv.appendChild(headerDiv);
  messageDiv.appendChild(bodyDiv);

  return messageDiv;
 }
 function addLoginOrLogoutLinkToNavigation() {
   const navigationElement = document.getElementById('navigation');
   if (!navigationElement) {
     console.warn('Navigation element not found!');
     return;
   }

   fetch('/login-status')
       .then((response) => {
         return response.json();
       })
       .then((loginStatus) => {
         if (loginStatus.isLoggedIn) {
           navigationElement.appendChild(createListItem(createLink(
               '/user-page.html?user=' + loginStatus.username, 'Your Page')));

           navigationElement.appendChild(
             createListItem(createLink('/feed.html', 'Message Feed')));
           navigationElement.appendChild(
               createListItem(createLink('/logout', 'Logout')));
         } else {
           navigationElement.appendChild(
               createListItem(createLink('/login', 'Login')));
         }
       });
 }

 /**
  * Creates an li element.
  * @param {Element} childElement
  * @return {Element} li element
  */
 function createListItem(childElement) {
   const listItemElement = document.createElement('li');
   listItemElement.appendChild(childElement);
   return listItemElement;
 }

 /**
  * Creates an anchor element.
  * @param {string} url
  * @param {string} text
  * @return {Element} Anchor element
  */
 function createLink(url, text) {
   const linkElement = document.createElement('a');
   linkElement.appendChild(document.createTextNode(text));
   linkElement.href = url;
   return linkElement;
 }

 // Fetch data and populate the UI of the page.
 function buildUI(){
  addLoginOrLogoutLinkToNavigation();
  fetchMessages();
  initMap();
 }
