import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NavigationComponent } from "./navigation/navigation.component";

import { AppComponent } from './app.component';
import { EditorComponent } from './editor/editor.component';
import { IndexComponent } from "./index/index.component";
import {PostComponent} from "./post/post.component";

import { StompConfig, StompService } from "@stomp/ng2-stompjs";
import * as SockJS from "sockjs-client";

const stompConfig: StompConfig = {
  url: () => new SockJS('/buoy'),
  headers: {},
  heartbeat_in: 0,
  heartbeat_out: 0,
  reconnect_delay: 5000,
  debug: false
};

// Route order matters
const appRoutes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'posts/:id', component: PostComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    EditorComponent,
    NavigationComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false }
    )
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
