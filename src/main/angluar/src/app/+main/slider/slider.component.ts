import {Component, OnInit} from '@angular/core';
import {MainService} from "../main.service";

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css']
})
export class SliderComponent implements OnInit {

  res : any;

  model = {
    interval : 5000,
    noWrap : false,
    slides : []
  };

  test : boolean = false;

  constructor(private mainService : MainService) { }

  ngOnInit() {
    this.getSliderList();
  }

  getSliderList(){

    this.mainService.getSliderList().subscribe(res => {

      this.res = res;

      if(this.res.code === '0'){
        this.model.slides = this.res.data;
      }else{
        this.model.slides = [];
      }

    });

  }

  onclick(){
    this.test = true;
  }

}
