<table><tr><td> <em>Assignment: </em> It114 Milestone1</td></tr>
<tr><td> <em>Student: </em> Nicolas McGrogan (nm874)</td></tr>
<tr><td> <em>Generated: </em> 10/22/2023 11:06:56 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-milestone1/grade/nm874" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create a new branch called Milestone1</li><li>At the root of your repository create a folder called Project if one doesn't exist yet</li><ol><li>You will be updating this folder with new code as you do milestones</li><li>You won't be creating separate folders for milestones; milestones are just branches</li></ol><li>Create a milestone1.md file inside the Project folder</li><li>Git add/commit/push it to Github (yes it'll be blank for now)</li><li>Create a pull request from Milestone1 to main (don't complete/merge it yet, just have it in open status)</li><li>Copy in the latest Socket sample code from the most recent Socket Part example of the lessons</li><ol><li>Recommended Part 5 (clients should be having names at this point and not ids)</li><li><a href="https://github.com/MattToegel/IT114/tree/Module5/Module5">https://github.com/MattToegel/IT114/tree/Module5/Module5</a>&nbsp;<br></li></ol><li>Fix the package references at the top of each file (these are the only edits you should do at this point)</li><li>Git add/commit the baseline</li><li>Ensure the sample is working and fill in the below deliverables</li><ol><li>Note: The client commands likely are different in part 5 with the /name and /connect options instead of just connect</li></ol><li>Get the markdown content or the file and paste it into the milestone1.md file or replace the file with the downloaded version</li><li>Git add/commit/push all changes</li><li>Complete the pull request merge from step 5</li><li>Locally checkout main</li><li>git pull origin main</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Startup </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot showing your server being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-23T02.11.18Screenshot%202023-10-22%20at%2010.10.50%E2%80%AFPM.png.webp?alt=media&token=818abe87-433d-4fb0-81cf-0c94a5059bc2"/></td></tr>
<tr><td> <em>Caption:</em> <p>Server listening to port<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot showing your client being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-23T02.11.24Screenshot%202023-10-22%20at%2010.10.50%E2%80%AFPM.png.webp?alt=media&token=7db04ff0-5f6b-4954-9bd8-3800eccf49c0"/></td></tr>
<tr><td> <em>Caption:</em> <p>Client connected to server<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the connection process</td></tr>
<tr><td> <em>Response:</em> <p>Connect to the server first and then the client has to connect to<br>the server using /connect localhost:(port). After connecting to the server they need to<br>then use /name (enter name) then the clients name will be displayed and<br>will output the client&#39;s input they had for name and that they are<br>connected<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Sending/Receiving </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-23T02.17.27Screenshot%202023-10-22%20at%2010.17.16%E2%80%AFPM.png.webp?alt=media&token=db6a427f-6fd3-454e-b85f-4ff03a1dafee"/></td></tr>
<tr><td> <em>Caption:</em> <p>two clients connected to server<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-23T02.49.03Screenshot%202023-10-22%20at%2010.46.08%E2%80%AFPM.png.webp?alt=media&token=81bdf832-c30a-416d-82e7-4bafd9bce402"/></td></tr>
<tr><td> <em>Caption:</em> <p>connect/disconnect to server<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the messages are sent, broadcasted (sent to all connected clients), and received</td></tr>
<tr><td> <em>Response:</em> <div>Sockets are used by the client and server to establish communication. To send<br>a message, the client publishes it to the socket's output stream. The server<br>monitors incoming connections. When a client connects, the server constantly monitors the associated<br>socket's input stream for incoming messages. When a message is detected, it is<br>read and processed by the server. When the server decides to broadcast a<br>message (either a message received from a client or a message generated by<br>the server), it iterates through all the client sockets that are connected. After<br>connecting to the server, the client constantly monitors the input stream of its<br>socket.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Disconnecting / Terminating </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-23T02.52.26Screenshot%202023-10-22%20at%2010.52.15%E2%80%AFPM.png.webp?alt=media&token=18e1cc80-2f92-4b43-a978-66e18d42bf18"/></td></tr>
<tr><td> <em>Caption:</em> <p>disconnecting from server<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the various disconnects/terminations are handled</td></tr>
<tr><td> <em>Response:</em> <div>The client notifies the server of its intention to terminate the connection by<br>sending a particular "disconnect" or "logout" message. When the client shuts its socket,<br>the connection to the server is effectively terminated. On the server side: When<br>the server receives a disconnect message or detects a closed socket, it releases<br>resources connected with that client. In some circumstances, such as chat rooms, the<br>server may also notify other clients of the disconnect. The server sends the<br>client a "disconnect" or "termination" message. The linked socket is closed by the<br>server. When the client receives a disconnect message or detects a closed socket,<br>it performs the appropriate cleanup and notifies the user.</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add the pull request for this branch</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/nmcgrogan/nm874-it114-003/pull/10">https://github.com/nmcgrogan/nm874-it114-003/pull/10</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Talk about any issues or learnings during this assignment</td></tr>
<tr><td> <em>Response:</em> <p>I didn&#39;t have any issues other than creating my files not in part<br>5 folder<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-milestone1/grade/nm874" target="_blank">Grading</a></td></tr></table>
