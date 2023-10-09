<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Nicolas McGrogan (nm874)</td></tr>
<tr><td> <em>Generated: </em> 10/3/2023 1:36:22 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-number-guesser/grade/nm874" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-03T17.03.45Screenshot%202023-10-03%20at%201.03.05%20PM.png.webp?alt=media&token=18f72fc1-b007-45e7-b080-68def55eeaca"/></td></tr>
<tr><td> <em>Caption:</em> <p>ran my higher/lower code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-03T17.08.20Screenshot%202023-10-03%20at%201.08.11%20PM.png.webp?alt=media&token=df03022c-9652-43ae-b81c-d9597c3f5a1a"/></td></tr>
<tr><td> <em>Caption:</em> <p>showing my code I revised in the processing guess function<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>If the player&#39;s guess is incorrect, the system determines whether it is higher<br>or lower than the secret number. It then offers useful commentary. &quot;Too low!&quot;<br>is printed if the guess is less than the hidden number. If the<br>guess is higher, it writes &quot;Too high! Try a higher number.&quot; Consider a<br>lower number.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-03T17.19.28Screenshot%202023-10-03%20at%201.14.12%20PM.png.webp?alt=media&token=34792030-0c43-4849-9f56-869a0ca871ed"/></td></tr>
<tr><td> <em>Caption:</em> <p>coded difficulty selector<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-03T17.19.32Screenshot%202023-10-03%20at%201.17.20%20PM.png.webp?alt=media&token=bb3b4983-9532-4cfe-b8ca-efebada18021"/></td></tr>
<tr><td> <em>Caption:</em> <p>added method to start function<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-10-03T17.20.28Screenshot%202023-10-03%20at%201.19.00%20PM.png.webp?alt=media&token=73914ec3-7c51-492c-9618-4c558e73d3e4"/></td></tr>
<tr><td> <em>Caption:</em> <p>ran my difficulty code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <div>In this code, I've added a new method called selectDifficulty which prompts the<br>user to choose a difficulty level (Easy, Medium, or Hard). Based on their<br>choice, it adjusts the maxStrikes accordingly.</div><div><br></div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/nmcgrogan/nm874-it114-003/pull/new/NumberGuesser4">https://github.com/nmcgrogan/nm874-it114-003/pull/new/NumberGuesser4</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I learned how to implement a higher-lower feature in an already existing game.<br>I learned how to add a difficulty selector for the player to choose.<br>I was struggling at first because I was compiling the wrong code and<br>it wasn&#39;t changing my outputs.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-number-guesser/grade/nm874" target="_blank">Grading</a></td></tr></table>