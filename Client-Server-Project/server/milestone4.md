<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone4</td></tr>
<tr><td> <em>Student: </em> Nicolas McGrogan (nm874)</td></tr>
<tr><td> <em>Generated: </em> 12/14/2023 4:06:06 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone4/grade/nm874" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can export chat history of their current session (client-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot of related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-11T17.42.59Screenshot%202023-12-11%20at%2012.42.44%E2%80%AFPM.png.webp?alt=media&token=e1499bfc-1f61-4414-a655-10dfcc3a13ae"/></td></tr>
<tr><td> <em>Caption:</em> <p>chat history<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot of exported data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-11T19.39.27Screenshot%202023-12-11%20at%202.39.17%E2%80%AFPM.png.webp?alt=media&token=034f2e94-2c5c-4555-af55-091fc407d685"/></td></tr>
<tr><td> <em>Caption:</em> <p>chat history<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>To keep all chat messages during the current session, a data structure (ArrayList<br>chatHistory) was employed. This list is updated whenever a message is delivered or<br>received. The user interface (UI) now has a &quot;Export Chat&quot; button. When the<br>user clicks on this button, it initiates the export process. The export button<br>now has an action listener linked to it. When the button is pressed,<br>the exportChatHistory method is called. The exportChatHistory method displays a file chooser dialog<br>(JFileChooser) to the user, allowing them to specify where the chat history file<br>should be saved. The function saveChatToFile is called after the user selects a<br>file and approves the activity.&nbsp;The saveChatToFile function takes as parameters the specified file<br>and the chatHistory ArrayList. Using a BufferedWriter, it writes each message from the<br>chat history to the file. This method handles any IOExceptions that may occur<br>and guarantees that the conversation history is recorded to the file in a<br>human-readable fashion.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client's mute list will persist across sessions (server-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot of how the mute list is stored</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-12T05.03.02Screenshot%202023-12-12%20at%2012.01.53%E2%80%AFAM.png.webp?alt=media&token=442bad36-39b3-45ed-8c0c-d0d15b672d0d"/></td></tr>
<tr><td> <em>Caption:</em> <p>stored mute List<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the code saving/loading mute list</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-12T05.06.49Screenshot%202023-12-12%20at%2012.06.41%E2%80%AFAM.png.webp?alt=media&token=dbae55b4-2bb9-4c50-b2bb-880955f7bc42"/></td></tr>
<tr><td> <em>Caption:</em> <p>saving mute list code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-12T05.08.16Screenshot%202023-12-12%20at%2012.07.59%E2%80%AFAM.png.webp?alt=media&token=b0a3d366-95dc-4a00-b057-42f9f6513de0"/></td></tr>
<tr><td> <em>Caption:</em> <p>load mute list code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>The saveMuteList method first determines whether or not a directory for storing mute<br>lists exists. If there isn&#39;t one, it makes one. Using the client&#39;s name,<br>the function then constructs a file path for the mute list specific to<br>the client.The mute list is turned into a string, which is a collection<br>of client IDs (mutedClients). A comma separates each ID. The prepared data is<br>written to the chosen file using a FileWriter. This is done within a<br>try-with-resources block to ensure that the file is closed successfully after writing.The file<br>path for the client&#39;s mute list is created by the loadMuteList function.It determines<br>whether or not the file exists. If it does, the procedure begins reading<br>from it. The function reads data from a file using a BufferedReader.&nbsp;The data<br>should be a comma-separated list of client IDs. The read string is divided<br>into client IDs, which are subsequently added to the mutedClients list.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client's will receive a message when they get muted/unmuted by another user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot showing the related chat messages</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-12T23.49.21Screenshot%202023-12-12%20at%206.49.10%E2%80%AFPM.png.webp?alt=media&token=975d2cc9-d5f6-46ad-9e89-6b8d98e12645"/></td></tr>
<tr><td> <em>Caption:</em> <p>muted/unmuted by<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the related code snippets</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-12-12T23.50.23Screenshot%202023-12-12%20at%206.50.15%E2%80%AFPM.png.webp?alt=media&token=070571a1-81f7-4f29-81c3-09bc4ec824e5"/></td></tr>
<tr><td> <em>Caption:</em> <p>mute/unmute by code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>The updateMuteList method handles both mute and unmute instructions, updating the list of<br>muted clients as needed and maintaining clear communication between all parties concerned. When<br>the command is received, the procedure takes the target username and uses findClientIdByUsername<br>to locate the appropriate client ID. The ServerThread for the target client is<br>then retrieved. Depending on whether the action is to mute or unmute, the<br>target client&#39;s ID is added or removed from the mutedClients list. After changing<br>the mute list, the starting client receives a confirmation message, and the target<br>client receives a notification telling them that they have been muted or unmuted<br>by a specified user. This implementation ensures that all parties are properly informed,<br>ensuring transparency in client-server interactions.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> User list should update per the status of each user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot for Muted users by the client should appear grayed out</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot for Last person to send a message gets highlighted</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>It kept spiraling into errors I was unable to get this section. I<br>changed up the code and received the user was muted by unknown and<br>then I had to revert back.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone4/grade/nm874" target="_blank">Grading</a></td></tr></table>