import { Injectable } from '@angular/core';
import {ApiGatewayService} from "../api-gateway.service";

@Injectable()
export class MainService {

  private mainUrl = 'main';

  constructor(private apiGatewayService : ApiGatewayService) { }

  getCompany(){
    return this.apiGatewayService.get(`${this.mainUrl}/company`);
  }

  getNavigationList(){
    return this.apiGatewayService.get(`${this.mainUrl}/navigation/list`);
  }

  getSliderList(){
    return this.apiGatewayService.get(`${this.mainUrl}/slider/list`);
  }

  getContents(){
    return this.apiGatewayService.get(`${this.mainUrl}/contents`);
  }

  getSubOneTitle(){
    return this.apiGatewayService.get(`${this.mainUrl}/subOne/title`);
  }

  getSubTwoTitle(){
    return this.apiGatewayService.get(`${this.mainUrl}/subTwo/title`);
  }

  getSubTwoList(){
    return this.apiGatewayService.get(`${this.mainUrl}/subTwo/list`);
  }

  insertQna(model){

    let data = {
      contents : model.contents,
      name : model.name,
      phone : model.phone,
      email : model.email
    };

    let jsonBody = JSON.stringify(data);

    return this.apiGatewayService.post(`${this.mainUrl}/qna/add`, jsonBody);
  }

  getSubTwoText(){
    return this.apiGatewayService.get(`${this.mainUrl}/subTwo/text`);
  }

  findCategoryList() {
    return this.apiGatewayService.get(`${this.mainUrl}/category/list`);
  }

  findCategoryItemList(idx) {
    return this.apiGatewayService.get(`${this.mainUrl}/category/item/list?idx=${idx}`);
  }

  findSubTwoList(idx){
    return this.apiGatewayService.get(`${this.mainUrl}/subTwo/list?idx=${idx}`);
  }

  findSubOneTypeList() {
    return this.apiGatewayService.get(`${this.mainUrl}/subOne/type/list`);
  }

  getSubOneList(idx){
    return this.apiGatewayService.get(`${this.mainUrl}/subOne/list?idx=${idx}`);
  }

}
