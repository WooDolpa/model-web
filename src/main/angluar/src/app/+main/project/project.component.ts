import {Component, OnInit, ViewChild} from '@angular/core';
import {MainService} from "../main.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  res : any;
  categoryRows : any[] = [];
  rows : any[] = [];
  index : number = 0;

  model = {
    title : '',
    imgUrl : '',
    content : '',
    url : ''
  };

  modalRef : BsModalRef;

  public state: any = {
    tabs : {
      demo1 : 0
    }
  }

  @ViewChild('imgModal') imgModal;
  constructor(private mainService : MainService,
              private modalService : BsModalService) { }

  ngOnInit() {
    this.findCategoryList();
  }

  findCategoryList () {

    this.mainService.findCategoryList().subscribe(res => {

      this.res = res;

      if(this.res.code === "0") {
        this.categoryRows = this.res.data;
        this.findCategoryItemList(this.categoryRows[0].id);
      }else{
        this.categoryRows = [];
      }

    });

  }

  showItemList(idx) {
    this.state.tabs.demo1 = idx;
    this.findCategoryItemList(this.categoryRows[idx].id);
  }

  findCategoryItemList (idx) {

    this.mainService.findSubTwoList(idx).subscribe(res => {

      this.res = res;

      if(this.res.code == 0) {
        this.rows = this.res.data;
      }else{
        this.rows = [];
      }
    });

  }

  openModal(row){

    this.model.title = row.galleryTitle;
    this.model.imgUrl = row.galleryImg;
    this.model.content = row.galleryDescription;
    this.model.url = row.galleryUrl;

    this.modalRef = this.modalService.show(this.imgModal);
  }

}
