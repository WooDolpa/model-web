import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MainService} from "../main.service";
import {NotificationService} from "../../shared/utils/notification.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  res : any;
  rows = [];
  company : string = "";
  logo : string = "";
  num : number = 0;

  @Output() moveFormEvent = new EventEmitter();

  constructor(private mainService : MainService,
              private notificationService : NotificationService) { }

  ngOnInit() {
    this.getCompany();
    this.getList();
  }

  getCompany(){
    this.mainService.getCompany().subscribe(res => {

      this.res = res;

      if(this.res.code === '0'){
        this.company = this.res.data.cpName;
        this.logo = this.res.data.cpImgUrl;
      }else{
        this.company = "";
        this.logo = "";
      }
    });

  }

  getList(){
    this.mainService.getNavigationList().subscribe(res => {

      this.res = res;

      if(this.res.code === '0'){
        this.rows = this.res.data;
      }else{
        this.rows = [];
      }

    });
  }

  moveForm(){
    this.num++;
    this.moveFormEvent.emit({
      status : this.num
    });
  }

  notice(){
    this.notificationService.smartMessageBox({
      title : "공지",
      content : "준비중입니다...",
      buttons : '[확인]'
    }, (ButtonPressed) => {
      if(ButtonPressed === '확인'){}
    });
  }

}
