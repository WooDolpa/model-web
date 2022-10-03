import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {mainRouting} from "./main.routing";
import {MainComponent} from "./main.component";
import {AlertModule, CarouselModule, TabsModule} from "ngx-bootstrap";
import {NavigationComponent} from './navigation/navigation.component';
import {MainService} from "./main.service";
import {SliderComponent} from './slider/slider.component';
import {ContentsComponent} from './contents/contents.component';
import {CustomComponent} from "./custom/custom.component";
import {SmartadminModule} from "../shared/smartadmin.module";
import { SubOneComponent } from './sub-one/sub-one.component';
import { SubTwoComponent } from './sub-two/sub-two.component';
import {ScrollToModule} from "ng2-scroll-to-el";
import { ProjectComponent } from './project/project.component';

@NgModule({
    imports: [
        CommonModule,
        mainRouting,
        SmartadminModule,
        CarouselModule.forRoot(),
        ScrollToModule.forRoot(),
        TabsModule,
        AlertModule
    ],
  declarations: [MainComponent, NavigationComponent, SliderComponent, ContentsComponent, CustomComponent, SubOneComponent, SubTwoComponent, ProjectComponent],
  providers: [MainService]
})
export class MainModule { }
