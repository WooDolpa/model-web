import { Component, OnInit } from '@angular/core';
import {MainService} from "../main.service";

@Component({
  selector: 'app-sub-one',
  templateUrl: './sub-one.component.html',
  styleUrls: ['./sub-one.component.css']
})
export class SubOneComponent implements OnInit {

  res : any;
  typeRows = [];
  rows = [];

  isResulted : boolean = false;
  formStatus : boolean = false;

  public state: any = {
    tabs : {
      demo1 : 0
    }
  }

  constructor(private mainService : MainService) {}

  ngOnInit(): void {
    this.findSubOneTypeList();
  }

  // 네비게이션 이동 이벤트
  moveEvent(e){
    this.formStatus = e.status;
  }

  findSubOneTypeList () {

    this.mainService.findSubOneTypeList().subscribe(res => {

      this.res = res;

      if(this.res.code === "0") {
        this.typeRows = this.res.data;
        this.findSubOneList(this.typeRows[0].id);
      }else{
        this.typeRows = [];
      }

    });
  }

  showItemList(idx) {
    this.state.tabs.demo1 = idx;
    this.findSubOneList(this.typeRows[idx].id);
  }

  findSubOneList(idx) {

    this.mainService.getSubOneList(idx).subscribe(res => {

      this.res = res;
      if(this.res.code == 0) {
        if(this.res.data.length > 0) {
          this.rows = this.res.data;
          this.isResulted = true;
        }else{
          this.rows = [];
          this.isResulted = false;
        }
      }else{
        this.rows = [];
        this.isResulted = false;
      }
    });
  }

}
