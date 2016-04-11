/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function w3_open() {
            document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
            document.getElementsByClassName("w3-overlay")[0].style.display = "block";
            }
            
function w3_close() {
            document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
            document.getElementsByClassName("w3-overlay")[0].style.display = "none";
            }
            
window.onscroll = function() {myFunction()};

            
function myFunction() {
            if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
                document.getElementById("myTop").classList.add("w3-card-4");
                document.getElementById("myIntro").classList.add("w3-show-inline-block");
            } else {
                document.getElementById("myIntro").classList.remove("w3-show-inline-block");
                document.getElementById("myTop").classList.remove("w3-card-4");
            }
            }
function openLink(evt, stavkaIme) {
            var i, x, tablinks;
            x = document.getElementsByClassName("stavka");
            for (i = 0; i < x.length; i++) {
               x[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablink");
            for (i = 0; i < x.length; i++) {
               tablinks[i].classList.remove("w3-red");
            }
            document.getElementById(cityName).style.display = "block";
            evt.currentTarget.classList.add("w3-red");
            }
            
function GreskaProzor(){   
	var myTextField = document.getElementById("Greska");
	if(myTextField.value != ""){		
             document.getElementById("modal").style.display="block";
         }

    
}

