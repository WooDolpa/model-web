import { Component, OnInit } from '@angular/core';
import {MainService} from "../main.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-contents',
  templateUrl: './contents.component.html',
  styleUrls: ['./contents.component.css']
})
export class ContentsComponent implements OnInit {

  res : any;
  htmlTxt : any;

  constructor(private mainService : MainService,
              private sanitzed : DomSanitizer) { }

  ngOnInit() {
    this.getContents();
  }

  getContents(){
    this.mainService.getContents().subscribe(res => {

      this.res = res;

      if(this.res.code === '0'){
        this.htmlTxt = this.sanitzed.bypassSecurityTrustHtml(this.res.data.ctTxt);
      }else{
        this.htmlTxt = null;
      }

    });
  }

}
