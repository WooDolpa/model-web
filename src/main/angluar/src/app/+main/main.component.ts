import { Component, OnInit } from '@angular/core';
import {ScrollToService} from "ng2-scroll-to-el";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  formStatus : boolean = false;

  constructor() { }

  ngOnInit() {
  }

  moveEvent(e){
    this.formStatus = e.status;
  }

}
