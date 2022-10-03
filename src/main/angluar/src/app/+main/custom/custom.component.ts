import {Component, ElementRef, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {MainService} from "../main.service";
import {NotificationService} from "../../shared/utils/notification.service";
import {ScrollToService} from "ng2-scroll-to-el";

@Component({
  selector: 'app-custom',
  templateUrl: './custom.component.html',
  styleUrls: ['./custom.component.css']
})
export class CustomComponent implements OnInit, OnChanges {

  res : any;

  model = {
    contents : '',
    name : '',
    phone : '',
    email : ''
  };

  company : string = '';

  @Input() status : number;
  @ViewChild("askForm") askForm : ElementRef;
  constructor(private mainService : MainService,
              private notificationService : NotificationService,
              private scrollToService : ScrollToService) { }

  ngOnInit() {
    this.getCompany();
    this.scrollEvent();
  }

  ngOnChanges(){
    this.scrollEvent();
  }

  scrollEvent(){

    if(this.status && this.status > 0){
      this.scrollToService.scrollTo(this.askForm.nativeElement).subscribe(data => {

      }, error => {
        console.log("error :",error);
      }, () => {

      });
    }else{
      window.scroll(0,0);
    }

  }

  submitEvent(){

    this.mainService.insertQna(this.model).subscribe(res => {

      this.res = res;
      if(this.res.code === '0'){

        this.reset();

        this.notificationService.smallBox({
          title: "성공적으로 전송되었습니다.",
          content: "<i class='fa fa-clock-o'></i> <i>4초뒤 자동으로 사라집니다..</i>",
          color: "#659265",
          iconSmall: "fa fa-check fa-2x fadeInRight animated",
          timeout: 4000
        });

      }else if(this.res.code == -3){

        this.notificationService.smartMessageBox({
          title : "오류",
          content : "상담내용을 입력해주세요.",
          buttons : '[확인]'
        }, (ButtonPressed) => {
          if(ButtonPressed === '확인'){}
        });

      }else if(this.res.code == -4){

        this.notificationService.smartMessageBox({
          title : "오류",
          content : "올바르지 않은 이름입니다.\n다시 입력해주세요.",
          buttons : '[확인]'
        }, (ButtonPressed) => {
          if(ButtonPressed === '확인'){}
        });

      }else if(this.res.code == -5){

        this.notificationService.smartMessageBox({
          title : "오류",
          content : "올바르지 않은 휴대폰 번호입니다.\n다시 입력해주세요.",
          buttons : '[확인]'
        }, (ButtonPressed) => {
          if(ButtonPressed === '확인'){}
        });

      }else if(this.res.code == -6){

        this.notificationService.smartMessageBox({
          title : "오류",
          content : "올바르지 않은 이메일입니다.\n다시 입력해주세요.",
          buttons : '[확인]'
        }, (ButtonPressed) => {
          if(ButtonPressed === '확인'){}
        });

      }else{

        this.notificationService.smartMessageBox({
          title : "오류",
          content : "오류가 발생하였습니다.\n관리자에게 문의해주세요.",
          buttons : '[확인]'
        }, (ButtonPressed) => {
          if(ButtonPressed === '확인'){}
        });

      }
    });

  }

  getCompany(){
    this.mainService.getCompany().subscribe(res => {

      this.res = res;

      if(this.res.code === '0'){
        this.company = this.res.data.cpName;
      }else{
        this.company = "";
      }
    });

  }

  reset(){

    this.model.contents = '';
    this.model.name = '';
    this.model.phone = '';
    this.model.email = '';

  }

}
