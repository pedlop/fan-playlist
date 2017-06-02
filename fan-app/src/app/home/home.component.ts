import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  public isCollapsed:boolean = false;

  public abreInfo : boolean = false;
  public abreInfo2 : boolean = false;
  public abreInfo3 : boolean = false;
  public abreInfo4 : boolean = false;

  public collapsed(event:any):void {
    console.log(event);
  }
 
  public expanded(event:any):void {
    console.log(event);
  }
}
