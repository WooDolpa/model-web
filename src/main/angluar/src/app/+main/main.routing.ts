import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MainComponent} from "./main.component";
import {SubOneComponent} from "./sub-one/sub-one.component";
import {SubTwoComponent} from "./sub-two/sub-two.component";


export const mainRoutes: Routes = [
    {
        path: '',
        component: MainComponent
    },
    {
        path: 'sub1',
        component: SubOneComponent
    },
    {
        path: 'sub2',
        component: SubTwoComponent
    }
];

export const mainRouting: ModuleWithProviders = RouterModule.forChild(mainRoutes);
