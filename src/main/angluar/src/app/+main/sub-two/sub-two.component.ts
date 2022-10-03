import {Component, OnInit, ViewChild} from '@angular/core';
import {MainService} from "../main.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-sub-two',
  templateUrl: './sub-two.component.html',
  styleUrls: ['./sub-two.component.css']
})
export class SubTwoComponent implements OnInit {

  res : any;
  categoryRows : any[] = [];
  rows : any[] = [];
  index : number = 0;
  txt : string = '';

  public state: any = {
    tabs : {
      demo1 : 0
    }
  }

  modalRef : BsModalRef;

  model = {
    title : '',
    imgUrl : '',
    content : '',
    url : ''
  };

  formStatus : boolean = false;

  @ViewChild('imgModal') imgModal;
  constructor(private mainService : MainService,
              private modalService : BsModalService) {}

  ngOnInit(): void {
    this.findCategoryList();
  }

  findCategoryList () {

    this.mainService.findCategoryList().subscribe(res => {

      this.res = res;

      if(this.res.code === "0") {
        this.categoryRows = this.res.data;
        this.findSubTwoList(this.categoryRows[0].id);
      }else{
        this.categoryRows = [];
      }

    });

  }

  findSubTwoList (idx) {

    this.mainService.findSubTwoList(idx).subscribe(res => {

      this.res = res;
      if(this.res.code == 0) {
        this.rows = this.res.data;
      }else{
        this.rows = [];
      }
    });
  }

  showItemList(idx) {
    this.state.tabs.demo1 = idx;
    this.findSubTwoList(this.categoryRows[idx].id);
  }

  openModal(row){

    this.model.title = row.galleryTitle;
    this.model.imgUrl = row.galleryImg;
    this.model.content = row.galleryDescription;
    this.model.url = row.galleryUrl;

    this.modalRef = this.modalService.show(this.imgModal);
  }

  moveEvent(e){
    this.formStatus = e.status;
  }


}
