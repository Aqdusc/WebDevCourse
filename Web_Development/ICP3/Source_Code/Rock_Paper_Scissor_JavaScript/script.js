//setting variables for rock paper scissor and identifying it by the ID
var rock = document.getElementById("rock");
var paper = document.getElementById("paper");
var scissor = document.getElementById("scissor");
//setting varibales for the moves
var yourMove =  document.getElementById("y-move");
var systemMove =  document.getElementById("s-move");
//Creating an array for the images
var imag = [
    "https://media.istockphoto.com/photos/stone-pebble-gray-picture-id1288973456?b=1&k=20&m=1288973456&s=170667a&w=0&h=GBGgp4yrZv4ooDBws8yHF24sJ3rkEpObYsBWpVNKFT8=",
    "https://aph.nyc3.digitaloceanspaces.com/app/uploads/2019/04/26160704/1-04851-00_BL_Notebook_Paper_Punch_G-600x735.jpg",
    "https://media.officedepot.com/images/f_auto,q_auto,e_sharpen,h_450/products/502369/502369_o04_fiskars_scissors_for_kids/502369_o04_fiskars_scissors_for_kids"
]
//creating objects for score
var scores = {
    you: 0,
    system: 0
};
//temporary variable for keeping random system choice
var sys = 0;
//commands and function to run when rock button is pressed
rock.addEventListener('click',()=>{
    this.yourMove.src = this.imag[0];
    this.sys = Math.floor(Math.random() * 3);
    if(this.sys  == 0){
        //Draw
        this.systemMove.src = this.imag[0];
    }
    else if(this.sys== 1){
        //Lose
        this.scores.system++;
        this.systemMove.src = this.imag[1];
    }
    else if(this.sys == 2){
        //Win
        this.scores.you++;
        this.systemMove.src = this.imag[2];
    }
    this.updateScore();
});
//commands and function to run when paper button is pressed
paper.addEventListener('click',()=>{
    this.yourMove.src = this.imag[1];
    this.sys = Math.floor(Math.random() * 3);
    if(this.sys  == 1){
        //Draw
        this.systemMove.src = this.imag[1];
    }
    else if(this.sys == 2){
        //Lose
        this.scores.system++;
        this.systemMove.src = this.imag[2];
    }
    else if(this.sys == 0){
        //Win
        this.scores.you++;
        this.systemMove.src = this.imag[0];
    }
    this.updateScore();
});
//commands and function to run when scissor button is pressed
scissor.addEventListener('click',()=>{
    this.yourMove.src = this.imag[2];
    this.sys = Math.floor(Math.random() * 3);
    if(this.sys  == 2){
        //Draw
        this.systemMove.src = this.imag[2];
    }
    else if(this.sys == 0){
        //Lose
        this.scores.system++;
        this.systemMove.src = this.imag[0];
    }
    else if(this.sys == 1){
        //Win
        this.scores.you++;
        this.systemMove.src = this.imag[1];
    }
    this.updateScore();
});
//display the updated changed score on the screen
function updateScore(){
    console.log(this.scores)
    document.getElementById("y-score").textContent = this.scores.you;
    document.getElementById("s-score").textContent = this.scores.system;
}