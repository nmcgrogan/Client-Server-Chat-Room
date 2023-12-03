<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone 2</td></tr>
<tr><td> <em>Student: </em> Nicolas McGrogan (nm874)</td></tr>
<tr><td> <em>Generated: </em> 11/13/2023 11:48:49 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone-2/grade/nm874" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone2 from the proposal document:&nbsp; <a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Payload </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Payload Screenshots</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T03.59.54Screenshot%202023-11-13%20at%208.34.11%E2%80%AFPM.png.webp?alt=media&token=d7545b0a-021c-469a-9d1e-6c5375363901"/></td></tr>
<tr><td> <em>Caption:</em> <p>payload code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T01.41.00Screenshot%202023-11-13%20at%208.34.26%E2%80%AFPM.png.webp?alt=media&token=2e6069c6-12da-4ba1-a1c8-721ed306dce9"/></td></tr>
<tr><td> <em>Caption:</em> <p>payloadtype code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T01.41.12Screenshot%202023-11-13%20at%208.35.55%E2%80%AFPM.png.webp?alt=media&token=4740afea-eb5d-4358-ac7d-1a84551460b1"/></td></tr>
<tr><td> <em>Caption:</em> <p>process payload command<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T03.54.24Screenshot%202023-11-13%20at%2010.54.10%E2%80%AFPM.png.webp?alt=media&token=530fe267-16e7-4f50-ab2b-8f94bc1bac93"/></td></tr>
<tr><td> <em>Caption:</em> <p>terminal<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Server-side commands </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the mentioned commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T03.58.28Screenshot%202023-11-13%20at%2010.58.10%E2%80%AFPM.png.webp?alt=media&token=bdacb5fe-ee28-40ec-b63b-fc8ac44f2ac4"/></td></tr>
<tr><td> <em>Caption:</em> <p>code displayed for /roll and /flip command<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Explain the logic/implementation of each commands</td></tr>
<tr><td> <em>Response:</em> <p style="border: 0px solid rgb(217, 217, 227); box-sizing: border-box; margin-bottom: 1.25em;">the /roll command<br>in a chat is typically used to simulate dice rolls. It generally supports<br>two formats: single side dice roll and multiple dice roll (<span style="font-size: 13px;">/roll<br>#" and "/roll #d#"</span>). When the user enters /flip it picks either heads<br>or tails.</p><ol style="border: 0px solid rgb(217, 217, 227); box-sizing: border-box; list-style: none; margin:<br>1.25em 0px; padding: 0px; counter-reset: list-number 0; display: flex; flex-direction: column;"><li style="border: 0px<br>solid rgb(217, 217, 227); box-sizing: border-box; --tw-border-spacing-x: 0; --tw-border-spacing-y: 0; --tw-translate-x: 0; --tw-translate-y:<br>0; --tw-rotate: 0; --tw-skew-x: 0; --tw-skew-y: 0; --tw-scale-x: 1; --tw-scale-y: 1; --tw-pan-x: ;<br>--tw-pan-y: ; --tw-pinch-zoom: ; --tw-scroll-snap-strictness: proximity; --tw-gradient-from-position: ; --tw-gradient-via-position: ; --tw-gradient-to-position: ; --tw-ordinal:<br>; --tw-slashed-zero: ; --tw-numeric-figure: ; --tw-numeric-spacing: ; --tw-numeric-fraction: ; --tw-ring-inset: ; --tw-ring-offset-width: 0px;<br>--tw-ring-offset-color: #fff; --tw-ring-color: rgba(69,89,164,0.5); --tw-ring-offset-shadow: 0 0 transparent; --tw-ring-shadow: 0 0 transparent; --tw-shadow:<br>0 0 transparent; --tw-shadow-colored: 0 0 transparent; --tw-blur: ; --tw-brightness: ; --tw-contrast: ;<br>--tw-grayscale: ; --tw-hue-rotate: ; --tw-invert: ; --tw-saturate: ; --tw-sepia: ; --tw-drop-shadow: ; --tw-backdrop-blur:<br>; --tw-backdrop-brightness: ; --tw-backdrop-contrast: ; --tw-backdrop-grayscale: ; --tw-backdrop-hue-rotate: ; --tw-backdrop-invert: ; --tw-backdrop-opacity: ;<br>--tw-backdrop-saturate: ; --tw-backdrop-sepia: ; margin-bottom: 0px; margin-top: 0px; padding-left: 0.375em; counter-increment: list-number 1;<br>display: block; min-height: 28px; color: rgb(236, 236, 241); caret-color: rgb(236, 236, 241); font-family:<br>Söhne, ui-sans-serif, system-ui, -apple-system, &quot;Segoe UI&quot;, Roboto, Ubuntu, Cantarell, &quot;Noto Sans&quot;, sans-serif, &quot;Helvetica<br>Neue&quot;, Arial, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color<br>Emoji&quot;; font-size: 16px; white-space: pre-wrap;"><p></p></li><br class="Apple-interchange-newline"></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Text Display </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show the code for the various style handling via markdown or special characters</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T04.33.07Screenshot%202023-11-13%20at%2011.32.46%E2%80%AFPM.png.webp?alt=media&token=7c34ad08-9343-4faf-975e-8492939bba57"/></td></tr>
<tr><td> <em>Caption:</em> <p>code for formatting<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show source message and the result output in the terminal (note, you won't actually see the styles until Milestone3)</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fnm874%2F2023-11-14T04.40.36Screenshot%202023-11-13%20at%2011.40.19%E2%80%AFPM.png.webp?alt=media&token=01fa95cd-856c-4162-92b9-4c822d34fac4"/></td></tr>
<tr><td> <em>Caption:</em> <p>source message output<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Explain how you got each style applied</td></tr>
<tr><td> <em>Response:</em> <p>The method processBold uses a regular expression \<em>\</em>(.<em>?)\</em>\<em>. This regex matches any text<br>placed between two asterisks and then replaces it with &lt;b&gt; tags.<div>The processItalics method<br>uses the regular expression _(.</em>?)_ to find text enclosed by underscores and replace<br>it with &lt;i&gt; tags.<br></div><div>&nbsp;The processColor method uses the regex \{color:(#?[0-9a-fA-F]{6})\}(.*?)\{color\} to find text<br>following the pattern {color:#hexcode} and replaces it with a &lt;span style=&#39;color:#hexcode;&#39;&gt;.<br></div><div>The processUnderline method<br>uses the regex <strong>(.*?)</strong> to find text enclosed by double underscores and replaces<br>it with &lt;u&gt; tags.<br></div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Include the pull request for Milestone2 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/nmcgrogan/nm874-it114-003/pull/11">https://github.com/nmcgrogan/nm874-it114-003/pull/11</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone-2/grade/nm874" target="_blank">Grading</a></td></tr></table>