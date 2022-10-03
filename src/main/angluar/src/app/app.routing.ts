/**
 * Created by griga on 7/11/16.
 */


import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from "@angular/core";
import {MainLayoutComponent} from "./shared/layout/app-layouts/main-layout.component";

export const routes: Routes = [
    {
        path: '',
        component: MainLayoutComponent,
        children: [
            {
                path: '', redirectTo: 'main', pathMatch: 'full'
            },
            {
                path: 'main',
                loadChildren: 'app/+main/main.module#MainModule'
            },
        ]
    },

];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {useHash: true});
