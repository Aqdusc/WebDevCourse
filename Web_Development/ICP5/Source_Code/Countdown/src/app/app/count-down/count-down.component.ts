import { Component } from '@angular/core';

@Component({
  selector: 'app-count-down',
  templateUrl: './count-down.component.html',
  styleUrls: ['./count-down.component.css']
})


export class CountDownComponent {
  // Declaring variables and setting a default time
  title = 'Count Down';
  dateTime: Date = new Date("february 25 2022");
  day: any;
  hrs: any;
  mins: any;
  secs: any;
  // Subtracting the user inputed time with the current time
  x = setInterval(() => {
    let now = new Date().getTime();
    let distance = new Date(this.dateTime).getTime() - now;
    this.day = Math.floor(distance / (1000 * 60 * 60 * 24));
    this.hrs = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    this.mins = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    this.secs = Math.floor((distance % (1000 * 60)) / (1000));
  });

  constructor() {}
}
